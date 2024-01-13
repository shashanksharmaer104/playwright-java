package com.automation.day6;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class RecordVideo {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false));
        BrowserContext context = browser.newContext(
                new Browser.NewContextOptions()
                        .setRecordVideoDir(Paths.get("./recordedVideo/"))
                        .setRecordVideoSize(1280, 720)
        );
        Page page = context.newPage();

        page.navigate("https://ecommerce-playground.lambdatest.io/");
        Locator myAccount = page.locator("//a[contains(.,' My account')][@role='button']");
        myAccount.hover();
        page.getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName("Login")).click();
        page.getByPlaceholder("E-Mail Address").fill("shashanksharma@gmail.com");
        page.getByPlaceholder("Password").fill("shashanksharma");
        page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Login")).click();
        page.getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName("Edit your account")).click();
        page.getByPlaceholder("Last Name").click();
        page.getByPlaceholder("Last Name").fill("Test");
        page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Continue")).click();
        Locator successMessage = page.getByText("Success: Your account has");
        assertThat(successMessage).isVisible();

        myAccount.hover();
        page.getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName("Logout").setExact(true)).click();
        Locator logoutMessage = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Account Logout"));
        assertThat(logoutMessage).isVisible();

        // close entire session
        page.close();
        context.close();
        browser.close();
        playwright.close();
    }
}

