package com.automation.day10;

import com.microsoft.playwright.*;

public class HandlingAlerts {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        Page page = browser.newPage();
        page.navigate("https://www.lambdatest.com/selenium-playground/javascript-alert-box-demo");

        // Handle first alert
        Locator buttons = page.locator("text='Click Me'");
        page.onceDialog(al -> {
            System.out.println("First: " + al.message());
            al.accept();
        });
        buttons.first().click();

        // Handle second alert
        page.onceDialog(al -> {
            System.out.println("Second: " + al.message());
            al.dismiss();
        });
        buttons.nth(1).click();
        System.out.println(page.locator("#confirm-demo").textContent());

        page.waitForTimeout(3000);
        playwright.close();

    }
}
