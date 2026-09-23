import subprocess
import os
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
            cur.execute(""" 
                SELECT id
                FROM problems
                WHERE url LIKE %s
            """, (f"%/{filename}/%",))
            result = cur.fetchone()

            language = problem.split(".")[1] # language str
            problem_id = result[0] #foreign key int

            #cur.execute("""
            #    INSERT INTO submission(problem_id, submitted_at, language, next_review, review_count)
            #    VALUES (%s, %s, %s, %s, %s)
            #""",(problem_id, commit_date, language, next_review))


            print(commit_date)

            

if __name__ == '__main__':
    main()