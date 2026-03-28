package com.epam.ta.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverSingleton {

    private static WebDriver driver;

    private DriverSingleton() {}

    public static WebDriver getDriver() {
        if (null == driver) {
            switch (System.getProperty("browser")) {
                case "firefox": {
                    FirefoxOptions options = new FirefoxOptions();
                    driver = new FirefoxDriver(options);
                    break;
                }
                default: {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--disable-save-password-bubble");
                    options.setExperimentalOption("prefs", java.util.Map.of(
                            "profile.password_manager_leak_detection", false
                    ));
                    driver = new ChromeDriver(options);
                    break;
                }
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}