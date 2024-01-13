package com.automation.day7;

import com.microsoft.playwright.*;

import java.util.List;

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

        // Working with single frame in a page
        FrameLocator frameLocator = page.frameLocator("#mce_0_ifr");
        Locator body = frameLocator.locator("#tinymce");
        body.fill("Test content !!!!!");

        // Working with multiple frames in a page
        page.navigate("https://letcode.in/frame");
        List<Frame> listFrames = page.frames();
        System.out.println("No. of frames: " + listFrames.size());
        //listFrames.forEach(frame -> System.out.println(frame.url()));

        // Working with nested(multiple) frames in a page
        FrameLocator parentFrame = page.frameLocator("#firstFr");
        parentFrame.getByPlaceholder("Enter name").fill("test user");
        FrameLocator childFrame = parentFrame.frameLocator("iframe.has-background-white");
        childFrame.getByPlaceholder("Enter email").fill("test@mail.com");

        // Close session
        playwright.close();
    }
}
