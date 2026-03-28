package com.epam.ta.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.ta.model.CheckoutData;

public class CheckoutInfoPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(CheckoutInfoPage.class);

    private final String BASE_URL = "https://www.saucedemo.com/checkout-step-one.html";

    @FindBy(css = "#first-name")
    private WebElement inputFirstName;

    @FindBy(css = "#last-name")
    private WebElement inputLastName;

    @FindBy(css = "#postal-code")
    private WebElement inputZipCode;

    @FindBy(css = "#continue")
    private WebElement buttonContinue;

    public CheckoutInfoPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public CheckoutInfoPage openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }
    @Step("Filling checkout information")
    public CheckoutOverviewPage fillInfoAndContinue(CheckoutData checkoutData) {
        log.info("Filling checkout info for: {} {}", checkoutData.getFirstName(), checkoutData.getLastName());
        inputFirstName.sendKeys(checkoutData.getFirstName());
        inputLastName.sendKeys(checkoutData.getLastName());
        inputZipCode.sendKeys(checkoutData.getZipCode());
        buttonContinue.click();
        return new CheckoutOverviewPage(driver);
    }
}