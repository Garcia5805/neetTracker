import subprocess
import os
from datetime import datetime, timedelta
from database.db import get_connection

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
            date = datetime.strptime(commit_date).date() #datetime.date

            cur.execute(""" 
                SELECT id
                FROM problems
                WHERE url LIKE %s
            """, (f"%/{filename}/%",))
            result = cur.fetchone()

            language = problem.split(".")[1] # language str
            problem_id = result[0] #foreign key int

            cur.execute("""
                INSERT INTO submissions(problem_id, submitted_at, language)
                VALUES (%s, %s, %s)
            """,(problem_id, date, language))

            added = 1
            next_review = date + timedelta(days=added)
            review_stage = 0
            last_language = None

            cur.execute("""
                INSERT INTO problem_progress(problem_id, first_solved_at, last_reviewed_at, next_review_at, review_stage, last_language)
                VALUES (%s, %s, %s, %s, %s, %s)
            """,(problem_id, date,  ))

            print(date)

            

if __name__ == '__main__':
    main()