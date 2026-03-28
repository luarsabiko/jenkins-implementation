package com.epam.ta.steps;

import com.epam.ta.driver.DriverSingleton;
import com.epam.ta.page.CartPage;
import com.epam.ta.page.CheckoutOverviewPage;
import com.epam.ta.page.LoginPage;
import com.epam.ta.page.InventoryPage;

import com.epam.ta.service.UserCreator;
import com.epam.ta.service.CheckoutDataCreator;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


public class CheckoutSteps {

    private WebDriver driver = DriverSingleton.getDriver();
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutOverviewPage overviewPage;

    @Given("I am logged in as standard user")
    public void iAmLoggedInAsStandardUser() {
        inventoryPage = new LoginPage(driver)
                    .openPage()
                    .login(UserCreator.withCredentialsFromProperty());
    }

    @When("I add {string} to the cart")
    public void iAddProductToCart(String productName) {
        inventoryPage.addProductToCart(productName);
    }

    @Then("the cart should contain {string}")
    public void theCartShouldContain(String productName) {
        cartPage = inventoryPage.goToCart();
        assertThat(cartPage.areItemsInCart(productName), is(true));
    }

    @Then("the cart should contain {string} and {string}")
    public void theCartShouldContainBoth(String product1, String product2) {
        cartPage = inventoryPage.goToCart();
        assertThat(cartPage.areItemsInCart(product1, product2), is(true));
    }

    @When("I proceed to checkout")
    public void iProceedToCheckout() {
        overviewPage = cartPage
                .proceedToCheckout()
                .fillInfoAndContinue(CheckoutDataCreator.withDataFromProperty());
    }

    @Then("the subtotal should equal the sum of item prices")
    public void theSubtotalShouldEqualSumOfItemPrices() {
        assertThat(overviewPage.getSubtotal(), is(equalTo(overviewPage.sumItemPrices())));
    }

    @Then("I should see the success message {string}")
    public void iShouldSeeSuccessMessage(String expectedMessage) {
        String successMessage = overviewPage
                .finishCheckout()
                .getSuccessMessage();
        assertThat(successMessage, is(equalTo(expectedMessage)));
    }
}