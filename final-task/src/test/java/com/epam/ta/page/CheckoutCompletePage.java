package com.epam.ta.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckoutCompletePage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(CheckoutCompletePage.class);

    private final String BASE_URL = "https://www.saucedemo.com/checkout-complete.html";

    @FindBy(css = ".complete-header")
    private WebElement headerComplete;

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public CheckoutCompletePage openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }
    @Step("Getting success message")
    public String getSuccessMessage() {
        String message = headerComplete.getText();
        log.info("Success message: {}", message);
        return message;
    }
}