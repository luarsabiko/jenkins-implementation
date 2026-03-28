package com.epam.ta.test;

import com.epam.ta.page.CartPage;
import com.epam.ta.page.LoginPage;
import com.epam.ta.page.CheckoutOverviewPage;
import com.epam.ta.model.User;
import com.epam.ta.service.TestDataReader;
import com.epam.ta.service.UserCreator;
import com.epam.ta.service.CheckoutDataCreator;
import com.epam.ta.model.CheckoutData;


import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@Epic("Checkout Flow")
public class CheckoutTests extends CommonConditions {

    private final User user = UserCreator.withCredentialsFromProperty();
    private final CheckoutData checkoutData = CheckoutDataCreator.withDataFromProperty();
    private final String correctSuccessMessage = TestDataReader.getTestData("checkout.success.message");

    @Test
    @Parameters("productName")
    @Story("UC-1: Checkout with one item")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Login as standard_user, add a single product to cart, proceed to checkout and validate success message")
    public void checkoutFlowOneItem(String productName) {
        CartPage cartPage = loginAndAddToCart(productName);

        assertThat(cartPage.areItemsInCart(productName), is(true));

        String successMessage = cartPage
                .proceedToCheckout()
                .fillInfoAndContinue(checkoutData)
                .finishCheckout()
                .getSuccessMessage();

        assertThat(successMessage, is(equalTo(correctSuccessMessage)));
    }

    @Test
    @Parameters({"productName", "productName2"})
    @Story("UC-2: Checkout with several items")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Login as standard_user, add two products to cart, validate prices and proceed to checkout")
    public void checkoutFlowSeveralItems(String productName, String productName2) {
        CartPage cartPage = loginAndAddToCart(productName, productName2);

        assertThat(cartPage.areItemsInCart(productName, productName2), is(true));

        CheckoutOverviewPage overviewPage = cartPage.proceedToCheckout()
                .fillInfoAndContinue(checkoutData);

        assertThat(overviewPage.getSubtotal(), is(equalTo(overviewPage.sumItemPrices())));

        String successMessage = overviewPage.finishCheckout()
                .getSuccessMessage();

        assertThat(successMessage, is(equalTo(correctSuccessMessage)));
    }

    @Step("Login as {user} and add products to cart")
    private CartPage loginAndAddToCart(String... productNames) {
        var inventoryPage = new LoginPage(driver)
                .openPage()
                .login(user);

        for (String productName : productNames) {
            inventoryPage.addProductToCart(productName);
        }

        return inventoryPage.goToCart();
    }
}