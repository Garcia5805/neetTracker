import subprocess
import os

from dotenv import load_dotenv

def main():
    load_dotenv()
    path = os.getenv("SUB_PATH")
    for filename in os.listdir(path):
        file_path = path + "/"+filename
        print(filename)
        for problem in os.listdir(file_path):
            problem_path = file_path+"/"+problem
            result = subprocess.run(
                ["git", "log", "-1", "--format=%ad", "--date=short", "--", problem_path],
                capture_output=True,
                text=True,
                check=True
            )
            commit_date = result.stdout.strip()
            print(problem + " " + commit_date)            
            

if __name__ == '__main__':
    main()