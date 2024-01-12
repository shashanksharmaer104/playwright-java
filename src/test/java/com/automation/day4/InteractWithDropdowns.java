package com.automation.day4;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class InteractWithDropdowns {
    public static void main(String[] args) {

        String selectDropdownURL = "https://www.lambdatest.com/selenium-playground/select-dropdown-demo";
        String jqueryDropdownURL = "https://www.lambdatest.com/selenium-playground/jquery-dropdown-search-demo";

        // Launch browser and navigate
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        Page page = browser.newPage();
        page.navigate(selectDropdownURL);

        Locator locator = page.locator("#select-demo"); // find dropdown
        // select by value
        locator.selectOption("Tuesday");
        Locator result = page.locator("p.selected-value");
        assertThat(result).containsText("Tuesday");

        // select bt label
        locator.selectOption(new SelectOption().setValue("Sunday"));
        System.out.println("Result: " + result.textContent());
        assertThat(result).containsText("Sunday");

        // select by index
        locator.selectOption(new SelectOption().setIndex(2));
        System.out.println("Result: " + result.textContent());
        assertThat(result).containsText("Monday");

        // select multiple
        Locator multiDrop = page.locator("#multi-select");
        multiDrop.selectOption(new String[]{"New Jersey", "New York"});

        // get count of options and print all options of dropdown
        Locator options = multiDrop.locator("option");
        System.out.println("Option count: " + options.count());

        List<String> optionsString = options.allInnerTexts();
        optionsString.forEach(System.out::println);

        // Jquery dropdrown
        page.navigate(jqueryDropdownURL);
        page.locator("//span[@aria-labelledby='select2-country-container']").click();

        Locator countryList = page.locator("//span[@class='select2-results']//ul//li", new Page.LocatorOptions().setHasText("India"));
        countryList.click();

        Locator dropNew = page.locator("#files");
        dropNew.selectOption("Python");

        playwright.close();
    }
}
