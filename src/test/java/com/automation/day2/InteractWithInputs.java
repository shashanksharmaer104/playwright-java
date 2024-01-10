package com.automation.day2;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class InteractWithInputs {
    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        Page page = browser.newPage();

        page.navigate("https://www.lambdatest.com/selenium-playground/simple-form-demo");
        page.locator("input#user-message").type("Hey user");
        page.click("#showInput");

        String message = page.locator("#message").textContent();
        System.out.println(message);

        assertThat(page.locator("#message")).hasText("Hey user");

        playwright.close();
    }
}
