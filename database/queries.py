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

def get_last_url():
    conn = get_connection()
    cur = conn.cursor()

    cur.execute("""
        SELECT url
        FROM problems
        ORDER BY id DESC
        LIMIT 1;
    """)

    last_url = cur.fetchone()[0]

    cur.close()
    conn.close()
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
    cur.close()
    conn.close()

def remove(id):
    conn = get_connection()
    cur = conn.cursor()

    cur.execute("""
        DELETE FROM problems
        WHERE id =%s 
    """,(id,))

    conn.commit()
    
    cur.close()
    conn.close()


if __name__ == "__main__":
    main()