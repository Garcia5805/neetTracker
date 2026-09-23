from database.db import get_connection
from datetime import datetime, timedelta

def test1():
    conn = get_connection()
    cur = conn.cursor()

    cur.execute("SELECT COUNT(*) FROM problems;")

    count = cur.fetchone()[0]

    print(f"Problems in database: {count}")

    cur.close()
    conn.close()

def main():
    date = "2026-08-13"
    DATE = datetime.strptime(date, "%Y-%m-%d").date() # turn into datetime format

    added = 4

    DATE = DATE + timedelta(days=added) # add 4 days to date
    print(DATE.strftime("%m/%d/%Y")) #change format
    print(type(DATE))
 


if __name__ == '__main__':
    main()