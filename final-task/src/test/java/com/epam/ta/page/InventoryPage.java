package com.epam.ta.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import com.epam.ta.util.StringUtils;

import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class InventoryPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(InventoryPage.class);

    private final String BASE_URL = "https://www.saucedemo.com/inventory.html";

    @FindBy(css = ".inventory_container")
    private WebElement inventoryContainer;

    @FindBy(css = ".shopping_cart_link")
    private WebElement cartLink;

    public InventoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOf(inventoryContainer));
    }

    @Override
    public InventoryPage openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }
    @Step("Adding product to cart: {productName}")
    public InventoryPage addProductToCart(String productName) {
        log.info("Adding product to cart: {}", productName);
        driver.findElement(By.id(StringUtils.toButtonId(productName))).click();
        return this;
    }
    @Step("Navigating to cart")
    public CartPage goToCart() {
        log.info("Navigating to cart");
        cartLink.click();
        return new CartPage(driver);
    }
}