from playwright.sync_api import sync_playwright
import time

def main():
    with sync_playwright() as p:

        browser = p.firefox.launch(headless=False)
        page = browser.new_page()
        page.goto("https://neetcode.io/problems/concatenation-of-array/question?list=allNC")
        
        p.selectors.set_test_id_attribute("data-tooltip")


        start = time.time()
        while time.time() - start < 20:   

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

                page_url = page.url

                    
                print(name + " " + diff_text + " " + top_text + " " + page_url)

                locator = page.get_by_test_id("Next Question")
                locator.hover()
                locator.click()

        browser.close()


if __name__ == '__main__':
    main()