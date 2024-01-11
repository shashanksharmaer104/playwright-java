package com.automation.day2;

import com.microsoft.playwright.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class InteractWithInputs {
    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false).setChannel("msedge")
        );
        Page page = browser.newPage();

        /*page.navigate("https://www.lambdatest.com/selenium-playground/simple-form-demo");
        page.locator("input#user-message").type("Hey user");
        page.click("#showInput");

        String message = page.locator("#message").textContent();
        System.out.println(message);

        assertThat(page.locator("#message")).hasText("Hey user");

        // type vs fill
        page.navigate("https://www.lambdatest.com/selenium-playground/generate-file-to-download-demo");
        //page.locator("#textbox").type("Data entered in the below textarea will be download with file name 'Lambdainfo.txt'.");
        page.locator("#textbox").fill("Data entered in the below textarea will be download with file name 'Lambdainfo.txt'.");*/

        // Get input value of textbox from UI
        page.navigate("https://letcode.in/edit");
        String value = page.locator("#getMe").inputValue();
        System.out.println(value);

        // Get placeholder value of textbox from UI
        Locator locator = page.locator("#fullName");
        System.out.println("Placeholder value: " + locator.getAttribute("placeholder"));
        assertThat(locator).hasAttribute("placeholder", "Enter first & last name");

        // Get text of label from UI
        Locator label = page.locator("(//label[@for='name'])[1]");
        System.out.println("Label text: " + label.textContent());

        // Clear textbox value from UI
        page.locator("#clearMe").clear();

        // Verify checbox assertion for check and uncheck functionality
        page.navigate("https://www.lambdatest.com/selenium-playground/checkbox-demo");
        Locator checkbox = page.locator("#isAgeSelected");
        assertThat(checkbox).not().isChecked();
        checkbox.check(); // click on checkbox
        assertThat(checkbox).isChecked();

        playwright.close();
    }
}
