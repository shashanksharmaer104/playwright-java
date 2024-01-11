package com.automation.day2;

import com.microsoft.playwright.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LaunchBrowser {
    public static void main(String[] args) {
        // Launch browser
        Playwright playwright = Playwright.create();
        Browser browser = playwright.webkit().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        Page page = browser.newPage();

        // Navigate to URL
        page.navigate("https://ecommerce-playground.lambdatest.io/");

        // Find and hover on link
        Locator myAccount = page.locator("//a[contains(.,' My account')][@role='button']");
        myAccount.hover();

        // Click on 'Login' link
        Locator login = page.locator("//a[contains(.,'Login')][1]");
        login.click();

        // Assert page title
        assertThat(page).hasTitle("Account Login");

        // Enter login details and click login button
        page.getByPlaceholder("E-Mail Address").type("shashanksharma@gmail.com");
        page.getByPlaceholder("Password").type("shashanksharma");
        page.locator("//*[@value='Login']").click();

        // Assert page title
        assertThat(page).hasTitle("My Account");

        // close sessions
        page.close();
        browser.close();
        playwright.close();
    }
}
