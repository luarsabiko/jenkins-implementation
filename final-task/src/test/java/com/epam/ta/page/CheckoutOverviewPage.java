package com.epam.ta.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckoutOverviewPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(CheckoutOverviewPage.class);

    private final String BASE_URL = "https://www.saucedemo.com/checkout-step-two.html";

    @FindBy(css = ".summary_subtotal_label")
    private WebElement subtotalLabel;

    @FindBy(css = ".inventory_item_price")
    private List<WebElement> itemPrices;

    @FindBy(css = "#finish")
    private WebElement buttonFinish;

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOf(buttonFinish));
    }

    @Override
    public CheckoutOverviewPage openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }

    public double getSubtotal() {
        double subtotal = Double.parseDouble(subtotalLabel.getText().replace("Item total: $", ""));
        log.info("Subtotal from page: {}", subtotal);
        return subtotal;
    }

    public double sumItemPrices() {
        double sum = itemPrices.stream()
                .mapToDouble(item -> Double.parseDouble(item.getText().replace("$", "")))
                .sum();
        log.info("Sum of item prices: {}", sum);
        return sum;
    }
    @Step("Finishing checkout")
    public CheckoutCompletePage finishCheckout() {
        log.info("Finishing checkout");
        buttonFinish.click();
        return new CheckoutCompletePage(driver);
    }
}