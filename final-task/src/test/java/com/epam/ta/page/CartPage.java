package com.epam.ta.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class CartPage extends AbstractPage {

    private final String BASE_URL = "https://www.saucedemo.com/cart.html";

    @FindBy(css = ".cart_item_label .inventory_item_name")
    private List<WebElement> cartItemNames;

    @FindBy(css = "#checkout")
    private WebElement buttonCheckout;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfAllElements(cartItemNames));
    }

    @Override
    public CartPage openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }

    public boolean areItemsInCart(String... productNames) {
        for (String productName : productNames) {
            boolean found = false;
            for (WebElement item : cartItemNames) {
                if (item.getText().equalsIgnoreCase(productName)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    public CheckoutInfoPage proceedToCheckout() {
        buttonCheckout.click();
        return new CheckoutInfoPage(driver);
    }
}