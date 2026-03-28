package com.epam.ta.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.ta.model.User;

import io.qameta.allure.Step;
public class LoginPage extends AbstractPage {

	private final String BASE_URL = "https://www.saucedemo.com/";

	@FindBy(css = "#user-name")
	private WebElement inputUsername;

	@FindBy(css = "#password")
	private WebElement inputPassword;

	@FindBy(css = "#login-button")
	private WebElement buttonLogin;

	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@Override
	@Step("Navigating to login page")
	public LoginPage openPage() {
		driver.navigate().to(BASE_URL);
		return this;
	}
	@Step("Logging in as {user}")
	public InventoryPage login(User user) {
		inputUsername.sendKeys(user.getUsername());
		inputPassword.sendKeys(user.getPassword());
		buttonLogin.click();
		return new InventoryPage(driver);
	}
}