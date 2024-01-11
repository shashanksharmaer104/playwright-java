package com.automation.day4;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class InteractWithDropdowns {
    public static void main(String[] args) {

        String selectDropdownURL = "https://www.lambdatest.com/selenium-playground/select-dropdown-demo";
        String jqueryDropdownURL = "https://www.lambdatest.com/selenium-playground/jquery-dropdown-search-demo";

        // Launch browser
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false).setChannel("msedge")
        );
        Page page = browser.newPage();

        //
    }
}
