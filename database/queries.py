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
        WHERE url = %s;
    """, (url))
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