package com.automation.day2;

import com.microsoft.playwright.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LaunchBrowser {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.webkit().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        Page page = browser.newPage();

        page.navigate("https://ecommerce-playground.lambdatest.io/");

        Locator myAccount = page.locator("//a[contains(.,' My account')][@role='button']");
        myAccount.hover();

        Locator login = page.locator("//a[contains(.,'Login')][1]");
        login.click();

        assertThat(page).hasTitle("Account Login");

        page.getByPlaceholder("E-Mail Address").type("shashanksharma@gmail.com");
        page.getByPlaceholder("Password").type("shashanksharma");
        page.locator("//*[@value='Login']").click();

        assertThat(page).hasTitle("My Account");

        page.close();
        browser.close();
        playwright.close();
    }
}
