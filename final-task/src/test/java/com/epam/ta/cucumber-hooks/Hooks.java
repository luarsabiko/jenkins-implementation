package com.epam.ta.steps;

import com.epam.ta.driver.DriverSingleton;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void setUp() {
        DriverSingleton.getDriver();
    }

    @After
    public void tearDown() {
        DriverSingleton.closeDriver();
    }
}