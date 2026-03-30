package com.epam.ta.steps;

import com.epam.ta.driver.DriverSingleton;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;

public class Hooks {

    @Before
    public void setUp(Scenario scenario) {
        String browser = System.getProperty("browser", "unknown");
        String environment = System.getProperty("environment", "unknown");

        // Visible in Allure
        Allure.parameter("browser", browser);
        Allure.parameter("environment", environment);

        scenario.log("Running on: " + browser);

        DriverSingleton.getDriver();
    }

    @After
    public void tearDown() {
        DriverSingleton.closeDriver();
    }
}