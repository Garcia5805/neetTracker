from database.db import get_connection

def template():
    conn = get_connection()
    cur = conn.cursor()



    cur.close()
    conn.close()

def main():
    conn = get_connection()
    cur = conn.cursor()

    cur.execute("SELECT * FROM problems;")

    for record in cur:
        print(record)

    cur.close()
    conn.close()

def get_last_url(cur):
    cur.execute("""
        SELECT url
        FROM problems
        ORDER BY id DESC
        LIMIT 1;
    """)

    last_url = cur.fetchone()[0]

    return last_url

def problem_exists(cur, url):

    cur.execute("""
        SELECT EXISTS(
            SELECT 1 
            FROM problems
            WHERE url =%s
        );
    """,(url,))

    return cur.fetchone()[0]


def remove(id):
    conn = get_connection()
    cur = conn.cursor()

    cur.execute("""
        DELETE FROM problems
        WHERE id =%s;
    """,(id,))

    conn.commit()
    
    cur.close()
    conn.close()

def topic_exists(cur, topic):

    cur.execute("""
        SELECT EXISTS(
            SELECT 1 
            FROM topics
            WHERE topic =%s
        );
    """,(topic,))
    return cur.fetchone()[0]

def add_submission(cur, problem_id, date, language):
    cur.execute("""
        INSERT INTO submissions(problem_id, submitted_at, language)
        VALUES (%s, %s, %s)
    """,(problem_id, date, language))

def has_progress(cur, problem_id):
    cur.execute("""
        SELECT EXISTS(
            SELECT 1
            FROM problem_progress
            WHERE problem_id =%s
        );
    """,(problem_id,))
    return cur.fetchone()[0]

def create_progress(cur, problem_id, date, next_review, review_stage):
    cur.execute("""
        INSERT INTO problem_progress(problem_id, first_solved_at, last_reviewed, next_review_at, review_stage)
        VALUES(%s, %s, %s, %s, %s)
    """,(problem_id, date, None, next_review, review_stage,))

def get_review_stage(cur, problem_id):
    cur.execute("""
        SELECT review_stage
        FROM problem_progress
        WHERE problem_id =%s
    """,(problem_id,))
    return cur.fetchone()[0]

def update_progress(cur, problem_id, date, next_review, review_stage):
    cur.execute("""
        UPDATE problem_progress
        SET last_reviewed =%s,
            next_review_at =%s,
            review_stage =%s
        WHERE problem_id =%s
    """,(date, next_review, review_stage, problem_id,))

def get_topic_id(cur, topic):
    cur.execute("""
        SELECT id 
        FROM topics
        WHERE topic = %s;
    """, (topic,))
    return cur.fetchone()[0]

def get_problem_id(cur, url):
    cur.execute(""" 
        SELECT id
        FROM problems
        WHERE url LIKE %s
    """, (f"%/{url}/%",))
    return cur.fetchone()[0]

def get_problem_topics():
    conn = get_connection()
    cur = conn.cursor()

    cur.execute("""
        
    """)

    cur.close()
    conn.close()



if __name__ == "__main__":
    main()