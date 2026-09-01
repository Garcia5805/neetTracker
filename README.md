# neetTracker

**neetTracker** is a Python-based web scraper and PostgreSQL database project for collecting and organizing NeetCode problems.

The goal of the project is to build a foundation for tracking solved problems and eventually using that information to support **spaced repetition** and DSA review.

## Features

* Scrapes problem information from NeetCode
* Collects:

  * Problem name
  * Difficulty
  * Problem URL
  * Topics
* Stores problems in PostgreSQL
* Prevents duplicate problems from being inserted
* Stores topics separately and connects them to problems through a many-to-many relationship
* Designed to eventually track submissions and recommend problems for review

## Tech Stack

* **Python** — Main programming language
* **Playwright** — Web scraping/browser automation
* **PostgreSQL** — Database
* **psycopg** — Python/PostgreSQL connection
* **Git/GitHub** — Version control

## Database Structure

The database currently consists of three tables:

```text
problems
---------
id
name
difficulty
url

topics
------
id
topic

problem_topics
--------------
problem_id
topic_id
```

### Relationships

A problem can have multiple topics, and a topic can belong to multiple problems.

```text
problems
   │
   │
   ▼
problem_topics
   ▲
   │
   │
topics
```

`problem_topics` acts as the junction table between `problems` and `topics`.

## Project Structure

```text
neetTracker/
│
├── database/
│   ├── __init__.py
│   ├── db.py
│   └── queries.py
│
├── scraper/
│   ├── __init__.py
│   └── main.py
│
├── .env
├── .gitignore
├── requirements.txt
└── README.md
```

> The exact structure may change as the project develops.

## Setup

### 1. Clone the repository

```bash
git clone <repository-url>
cd neetTracker
```

### 2. Create a virtual environment

```bash
python3 -m venv .venv
```

Activate it:

```bash
source .venv/bin/activate
```

### 3. Install dependencies

```bash
pip install -r requirements.txt
```

If Playwright has not been installed/configured yet:

```bash
playwright install
```

### 4. Configure PostgreSQL

Create a PostgreSQL database named:

```text
neetTracker
```

The project uses PostgreSQL to store scraped problem and topic information.

### 5. Configure environment variables

Create a `.env` file containing your PostgreSQL connection information.

Example:

```env
DB_NAME=neetTracker
DB_USER=postgres
DB_PASSWORD=your_password
DB_HOST=localhost
DB_PORT=5432
```

**Do not commit ****`.env`**** to GitHub.**

Make sure `.gitignore` contains:

```text
.env
.venv/
__pycache__/
```

## Running the Scraper

Activate the virtual environment:

```bash
source .venv/bin/activate
```

Then run the scraper:

```bash
python scraper/main.py
```

The scraper will navigate through the problems, collect the relevant information, and insert it into PostgreSQL.

Existing problems are checked before insertion to prevent duplicates.

## Database Commands

Connect to the database from Terminal:

```bash
/Library/PostgreSQL/18/bin/psql -U postgres -d neetTracker
```

List tables:

```sql
\dt
```

View problems:

```sql
SELECT * FROM problems;
```

View topics:

```sql
SELECT * FROM topics;
```

View problem-topic relationships:

```sql
SELECT * FROM problem_topics;
```

### Reset the Database

To remove all data and reset the ID counters:

```sql
TRUNCATE TABLE problem_topics, problems, topics RESTART IDENTITY CASCADE;
```

## Current Goal

The current version of neetTracker focuses on building a reliable database of NeetCode problems and their associated topics.

Future versions will expand the project into a system for tracking solved problems and determining when previously solved problems should be reviewed.

## Future Plans

* [ ] Track when a problem was solved
* [ ] Track multiple submissions/reviews
* [ ] Implement spaced-repetition logic
* [ ] Recommend problems that are due for review
* [ ] Build a web interface
* [ ] Add user statistics and progress tracking
* [ ] Visualize DSA progress by topic and difficulty
* [ ] Add authentication/user accounts
* [ ] Deploy the application

## Status

🚧 **In development**

The project is currently focused on the scraping and database foundation. More functionality will be added as development continues.
