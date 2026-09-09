from database.db import get_connection

conn = get_connection()
cur = conn.cursor()

cur.execute("SELECT COUNT(*) FROM problems;")

count = cur.fetchone()[0]

print(f"Problems in database: {count}")

cur.close()
conn.close()