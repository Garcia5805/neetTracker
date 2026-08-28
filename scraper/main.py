from playwright.sync_api import sync_playwright
import time
from database.db import get_connection
from database.queries import get_last_url
from database.queries import problem_exists
from database.queries import topic_exists
from database.queries import get_topic_id




def main():

    conn = get_connection()
    cur = conn.cursor()

    with sync_playwright() as p:
        browser = p.firefox.launch(headless=False)
        page = browser.new_page()

            
        page.goto(get_last_url)
        
        p.selectors.set_test_id_attribute("data-tooltip")


        start = time.time()
        while time.time() - start < 10:   
            
            page.wait_for_timeout(1500)
            pro = page.get_by_role("button", name="Get Pro Access")
            pro_visible = pro.is_visible()

            if(pro_visible):
                print("Skip")
                locator = page.get_by_test_id("Next Question")
                locator.hover()
                page.wait_for_timeout(500)
                locator.click()
                continue
            else:

                h1 = page.locator("h1.problem-title")
                name = h1.inner_text()

                diff_loc = page.locator('[class^="difficulty-pill"]')
                diff_text = diff_loc.inner_text()

                top_loc = page.locator(".hint-accordion", has_text="Topics")
                top_loc.click()
                top_loc = top_loc.locator('[class^="company-tags-container"]')
                top_text = top_loc.inner_text()

                top_list = top_text.split(" ")



                url = page.url

                

                if problem_exists(cur, url):
                    continue

                cur.execute("""
                    INSERT INTO problems (name, difficulty, url) 
                    VALUES (%s, %s, %s)
                    RETURNING id;
                """,(name, diff_text, url))  
                problem_id = cur.fetchone()[0]

                for topic in top_list: 
                    if not topic_exists(cur, topic):
                        cur.execute("""
                            INSERT INTO topics (topic)
                            VALUES (%s);
                        """, (topic,))

                    topic_id = get_topic_id(cur, topic)

                    cur.execute("""
                        INSERT INTO problem_topics (problem_id, topic_id)
                        VALUES (%s, %s);
                    """, (problem_id, topic_id))
                    

                



                locator = page.get_by_test_id("Next Question")
                locator.hover()
                locator.click()
        conn.commit()

        browser.close()
    
    cur.close()
    conn.close()


if __name__ == '__main__':
    main()