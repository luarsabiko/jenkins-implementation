package com.epam.ta.steps;

import com.epam.ta.driver.DriverSingleton;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.Allure;

@Before
public void setUp() {
        String browser = System.getProperty("browser", "unknown");

        Allure.label("browser", browser);

        Allure.getLifecycle().updateTestCase(testCase ->
        testCase.setName(testCase.getName() + " [" + browser + "]")
        );

        DriverSingleton.getDriver();
        }

    @After
    public void tearDown() {
        DriverSingleton.closeDriver();
    }
}