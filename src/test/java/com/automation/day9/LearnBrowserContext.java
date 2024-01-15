package com.automation.day9;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LearnBrowserContext {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        BrowserContext context = browser.newContext();
        Page page = context.newPage();
        page.navigate("https://ecommerce-playground.lambdatest.io/index.php?route=account/login");
        page.getByPlaceholder("E-Mail Address").fill("shashanksharma@gmail.com");
        page.getByPlaceholder("Password").fill("shashanksharma");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
        Locator editInfoLink = page.getByText(" Edit your account information");
        assertThat(editInfoLink).isVisible();

        // To open new tab within same window (sharing the session)
        Page newTab = page.context().newPage();
        newTab.navigate("https://ecommerce-playground.lambdatest.io/index.php?route=account/account");
        Locator newTabEditInfoLink = newTab.getByText(" Edit your account information");
        assertThat(newTabEditInfoLink).isVisible();
        playwright.close();
    }
}
