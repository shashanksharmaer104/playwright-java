package com.automation.day8;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LearnWindowHandling {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        Page page = browser.newPage();
        page.navigate("https://www.lambdatest.com/selenium-playground/window-popup-modal-demo");

        Page popup = page.waitForPopup(() -> {
            page.getByText("Follow On Twitter").click();
        });
        popup.waitForLoadState();
        System.out.println("Popup title: " + popup.title());

        // this assertion can be used for waiting purpose as well (to load full page title)
        assertThat(popup).hasTitle("LambdaTest (@lambdatesting) / X");
        System.out.println("Popup title after assertion: " + popup.title());

        popup.getByText("Log in").click();
        popup.close();

        // Handle Multi popups on a single click
        Page popups = page.waitForPopup(new Page.WaitForPopupOptions().setPredicate
                        (pc -> pc.context().pages().size() == 3), () -> { // wait for at least 3 pages to open
            page.getByText("Follow All").click();
        });
        List<Page> pages = popups.context().pages();
        System.out.println("Opened Pages size: " + pages.size());
        pages.forEach(p -> {
            p.waitForLoadState();
            System.out.println(p.title());
        });

        //page.close();
        playwright.close();
    }
}
