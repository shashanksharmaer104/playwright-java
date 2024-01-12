package com.automation.day5;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.ScreenshotCaret;

import java.lang.reflect.Array;
import java.nio.file.Paths;
import java.util.Arrays;

public class LearnScreenshots {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        Page page = browser.newPage();
        page.navigate("https://www.lambdatest.com/selenium-playground/simple-form-demo");

        // take page screenshot
        Page.ScreenshotOptions pageScreenshotOptions= new Page.ScreenshotOptions();
        page.screenshot(pageScreenshotOptions.setPath(Paths.get("./screenshots/src.png")));

        //take full page screenshot
        page.screenshot(pageScreenshotOptions.setFullPage(true).setPath(Paths.get("./screenshots/fullSrc.png")));

        // locator screenshot
        Locator.ScreenshotOptions locScreenshotOptions= new Locator.ScreenshotOptions();
        Locator bookBtn = page.locator("//a[text()='Get Started Free']");
        bookBtn.screenshot(locScreenshotOptions.setPath(Paths.get("./screenshots/locator.png")));

        Locator header = page.locator("#header");
        header.screenshot(locScreenshotOptions.setPath(Paths.get("./screenshots/header.png")));

        // screenshot with masked locator
        Locator input = page.locator("//input[@id='user-message']");
        input.type("Something");
        input.scrollIntoViewIfNeeded();
        page.screenshot(pageScreenshotOptions
                .setFullPage(false)
                        .setMask(Arrays.asList(input)) // add elements as list
                        .setMaskColor("black") // set masking colour
                .setPath(Paths.get("./screenshots/masked.png")));

        // Caret SHOW/HIDE
        input.clear();
        input.click();
        page.screenshot(new Page.ScreenshotOptions()
                .setFullPage(false)
                        .setCaret(ScreenshotCaret.HIDE)
                .setPath(Paths.get("./screenshots/carotHide.png")));
        page.screenshot(new Page.ScreenshotOptions()
                .setFullPage(false)
                .setCaret(ScreenshotCaret.INITIAL)
                .setPath(Paths.get("./screenshots/carotShow.png")));



        page.close();
        playwright.close();
    }
}
