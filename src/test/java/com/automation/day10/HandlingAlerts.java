package com.automation.day10;

import com.microsoft.playwright.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class HandlingAlerts {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        BrowserContext browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        ).newContext(new Browser.NewContextOptions()
				  .setHttpCredentials("admin", "admin"));
        Page page = browser.newPage();
        page.navigate("https://www.lambdatest.com/selenium-playground/javascript-alert-box-demo");

        // Handle first alert
        Locator buttons = page.locator("text='Click Me'");
        page.onceDialog(al -> { // defining behaviour for alert action
            System.out.println("First alert message: " + al.message());
            al.accept();
        });
        buttons.first().click();

        // Handle second alert
        page.onceDialog(al -> { // defining behaviour for alert action
            System.out.println("Second alert message: " + al.message());
            al.dismiss();
        });
        buttons.nth(1).click();
        System.out.println(page.locator("#confirm-demo").textContent());

        // Handle third alert
        page.onceDialog(al -> { // defining behaviour for alert action
            System.out.println("Third alert message: " + al.message());
            System.out.println("Default textbox value: " + al.defaultValue());
            al.accept("Shashank Sharma");
        });
        buttons.last().click();
        System.out.println(page.locator("#prompt-demo").textContent());

        // wait for 3 secs
        page.waitForTimeout(3000);

        // Now handle JS authentication
        page.navigate("https://the-internet.herokuapp.com/basic_auth");
        assertThat(page.locator("h3")).hasText("Basic Auth");
        page.waitForTimeout(2000);

        playwright.close();
    }
}
