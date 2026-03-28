package com.epam.ta.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCompletePage extends AbstractPage {

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

    public String getSuccessMessage() {
        return headerComplete.getText();
    }
}