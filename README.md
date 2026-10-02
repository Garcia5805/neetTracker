# NeetTracker

NeetTracker is a personal LeetCode/NeetCode tracking tool designed to help organize problem-solving history and recommend which problems should be reviewed next.

Instead of using fixed spaced-repetition deadlines, NeetTracker uses a problem's solve history to determine review priority. Problems that have not been solved recently and have fewer previous submissions can be given higher priority for review.

## Features

- Imports NeetCode submission history from Git commit data
- Stores individual problem submissions
- Tracks submission dates and programming languages
- Stores problems and their associated topics
- Uses PostgreSQL for relational data storage
- Uses Supabase for remote database access
- Preserves full submission history
- Supports future priority-based problem recommendations

## Review System

NeetTracker originally used fixed spaced-repetition intervals such as:

```text
1 → 3 → 7 → 14 → 30 → 60 → 120 days
```

However, coding problems require significantly more time than traditional flashcard reviews. Fixed review dates can therefore create an unrealistic backlog of overdue problems.

The review system is being redesigned around **priority-based recommendations** instead.

Rather than asking:

> When is this problem due?

NeetTracker aims to answer:

> Which problem would be most valuable for me to review next?

Problem priority can be determined using information such as:

- Time since the problem was last solved
- Number of previous solves
- Problem difficulty
- Topic
- Future performance metrics

An initial ranking formula may look similar to:

```python
priority = days_since_last_solve / solve_count
```

Problems with higher priority scores would be recommended first.

The ranking algorithm will continue to evolve as more submission data is collected.

## Database Structure

### `problems`

Stores information about each problem.

```text
id
name
url
difficulty
```

### `topics`

Stores available problem topics.

```text
id
name
```

### `problem_topic`

Creates the many-to-many relationship between problems and topics.

```text
problem_id
topic_id
```

### `submissions`

Stores the full history of problem submissions.

```text
id
problem_id
submitted_at
language
```

The `submissions` table acts as the primary source of solve-history data.

Statistics such as:

- First solve date
- Most recent solve date
- Number of solves
- Time since last solve

can be calculated directly from submission history instead of being stored as fixed review state.

Example:

```sql
SELECT
    problem_id,
    MIN(submitted_at) AS first_solved_at,
    MAX(submitted_at) AS last_solved_at,
    COUNT(*) AS solve_count
FROM submissions
GROUP BY problem_id;
```

## Git History Import

NeetTracker reads commit history from a NeetCode-generated Git repository to determine when individual submissions were made.

Example:

```bash
git log origin/main -1 --format=%ad --date=short -- path/to/submission.java
```

The resulting commit date is stored alongside the corresponding problem submission in the database.

## Tech Stack

### Languages

- Python
- SQL

### Database

- PostgreSQL
- Supabase

### Tools

- Git
- GitHub
- Playwright

## Project Structure

The project is organized around several main responsibilities:

```text
NeetCode Repository
        |
        v
Git Commit History
        |
        v
Python Import Scripts
        |
        v
PostgreSQL / Supabase
        |
        v
Submission Statistics
        |
        v
Recommendation Algorithm
```

The database records what has happened, while the recommendation system determines what problems should be reviewed next.

This separation allows the recommendation algorithm to change without requiring major changes to stored submission data.

## Current Development

Current work is focused on:

- Simplifying the database around submission history
- Removing the previous fixed review-scheduling system
- Calculating review statistics from stored submissions
- Building an initial problem-ranking algorithm

## Planned Features

Future development may include:

- Ranked review recommendations
- Topic-based filtering
- Difficulty-based filtering
- Review history analytics
- Problem-solving trends over time
- Topic performance breakdowns
- Weak-topic identification
- Improved recommendation scoring
- Additional performance metrics such as hints or failed attempts

## Goal

The goal of NeetTracker is to make LeetCode review more practical.

Rather than maintaining a strict schedule of problems that become "overdue," NeetTracker will prioritize problems based on their actual solve history and help answer a simpler question:

**What should I practice next?**
