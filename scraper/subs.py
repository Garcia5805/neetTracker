import subprocess
import os
from database.db import get_connection


from dotenv import load_dotenv

def main():

    conn = get_connection()
    cur = conn.cursor()

    url = "https://neetcode.io/problems/"

    load_dotenv()
    path = os.getenv("SUB_PATH")

    for filename in os.listdir(path):

        file_path = path + "/"+filename
        
        problem_url =url+filename
        print(problem_url)

        for problem in os.listdir(file_path):
            problem_path = file_path+"/"+problem
            result = subprocess.run(
                ["git", "log", "-1", "--format=%ad", "--date=short", "--", problem_path],
                capture_output=True,
                text=True,
                check=True
            )
            commit_date = result.stdout.strip()
            cur.execute("""
                SELECT id
                FROM problems
                WHERE url LIKE %s
            """, (f"%/{filename}/%",))
            result = cur.fetchone()
            
            problem_id = result[0] #foreign key 
            print(problem_id) 

            

if __name__ == '__main__':
    main()