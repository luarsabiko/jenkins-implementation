package com.epam.ta.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.epam.ta.model.CheckoutData;

public class CheckoutInfoPage extends AbstractPage {

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
    public CheckoutOverviewPage fillInfoAndContinue(CheckoutData checkoutData) {
        inputFirstName.sendKeys(checkoutData.getFirstName());
        inputLastName.sendKeys(checkoutData.getLastName());
        inputZipCode.sendKeys(checkoutData.getZipCode());
        buttonContinue.click();
        return new CheckoutOverviewPage(driver);
    }
}