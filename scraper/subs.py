import subprocess
import os
from datetime import datetime, timedelta
from database.db import get_connection

from database.queries import get_problem_id, has_progress, create_progress, update_progress, get_review_stage, add_submission

from dotenv import load_dotenv

def main():

    conn = get_connection()
    cur = conn.cursor()


    load_dotenv()
    path = os.getenv("SUB_PATH")

    for filename in os.listdir(path):

        file_path = path + "/"+filename
        
        for problem in os.listdir(file_path):
            problem_path = file_path+"/"+problem
            result = subprocess.run(
                ["git", "log", "-1", "--format=%ad", "--date=short", "--", problem_path],
                capture_output=True,
                text=True,
                check=True
            )
            commit_date = result.stdout.strip() # str

            date = datetime.strptime(commit_date, "%Y-%m-%d").date()
            
            problem_id = get_problem_id(cur,filename)

            language = problem.split(".")[1] # language str

            add_submission(cur, problem_id, date, language)

            
            last_language = None

            # If problem_progress doesn't already have a row for problem_id make it with:
            #               problem_id
            #               first_solved_at
            #               next_review
            #               review_stage
            # Leave last_reviewed, and last_language as none/null

            review_interval = [1, 3, 7, 14, 30, 60, 120]
            if not has_progress(cur, problem_id):
                review_stage = 0
                next_review = date + timedelta(days=review_interval[review_stage])

            # If it does have one, just update whatever is necessary so:
            #               last_reviewed_at
            #               next_review_at
            #               review_stage
                create_progress(cur, problem_id, date, next_review, review_stage)
                print("worked")
                
            else:
                review_stage = get_review_stage(cur, problem_id)
                next_review = calc_next_review(date, review_stage, review_interval) # date it was solved + interval
                update_progress(cur, problem_id, date, next_review, review_stage+1)
                print("worked")


    conn.commit()
    cur.close()
    conn.close()
    print("done")
            



def calc_next_review(date, review_stage, review_interval, ):
    index = min(review_stage // 3, len(review_interval) - 1)
    next_review = date + timedelta(days=review_interval[index])
    return next_review

if __name__ == '__main__':
    main()