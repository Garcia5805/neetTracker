from playwright.sync_api import sync_playwright
import time

def main():
    with sync_playwright() as p:

        browser = p.firefox.launch(headless=False)
        page = browser.new_page()
        page.goto("https://neetcode.io/problems/concatenation-of-array/question")
        
        p.selectors.set_test_id_attribute("data-tooltip")


        start = time.time()
        while time.time() - start < 10:     
            page.wait_for_timeout(1000)  
            h1 = page.locator("h1.problem-title")
            text = h1.inner_text()

            diff_loc = page.locator('[class^="difficulty-pill"]')
            diff_text = diff_loc.inner_text()

            print(text + " " + diff_text)

            locator = page.get_by_test_id("Next Question")
            locator.hover()
            locator.click()

        browser.close()


if __name__ == '__main__':
    main()