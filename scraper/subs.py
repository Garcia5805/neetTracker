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
    repo_root = subprocess.run(
        ["git", "rev-parse", "--show-toplevel"],
        capture_output=True,
        text=True,
        check=True
    ).stdout.strip()

    for filename in os.listdir(path):
        file_path = os.path.join(path, filename)

        for problem in os.listdir(file_path):
            problem_path = os.path.join(file_path, problem)

            git_path = os.path.relpath(problem_path, repo_root)

            result = subprocess.run(
                [
                    "git",
                    "log",
                    "origin/main",
                    "-1",
                    "--format=%ad",
                    "--date=short",
                    "--",
                    git_path
                ],
                cwd=repo_root,
                capture_output=True,
                text=True,
                check=True
            )
            print("stdout:", repr(result.stdout))
            print("stderr:", repr(result.stderr))
            print("return code:", result.returncode)

            commit_date = result.stdout.strip()
            if not commit_date:
                    print(f"No commit for {problem_path}")
                    print("-------")
                    continue
            print("-------")

            date = datetime.strptime(commit_date, "%Y-%m-%d").date()
            problem_id = get_problem_id(cur, filename)
            language = problem.split(".")[1]
            add_submission(cur, problem_id, date, language)

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