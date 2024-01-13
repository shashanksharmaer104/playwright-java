package com.automation.day7;

import com.microsoft.playwright.*;

public class LearnFrames {
    public static void main(String[] args) {
        // Launch browser
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        Page page = browser.newPage();

        // Navigate to URL
        page.navigate("https://the-internet.herokuapp.com/iframe");

        FrameLocator frameLocator = page.frameLocator("#mce_0_ifr");
        Locator body = frameLocator.locator("#tinymce");
        body.fill("Test content !!!!!");

        playwright.close();
    }
}
