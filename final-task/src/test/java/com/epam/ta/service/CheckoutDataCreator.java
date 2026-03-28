package com.epam.ta.service;

import com.epam.ta.model.CheckoutData;

public class CheckoutDataCreator {

    public static final String CHECKOUT_FIRSTNAME_PROPERTY = "checkout.firstname";
    public static final String CHECKOUT_LASTNAME_PROPERTY = "checkout.lastname";
    public static final String CHECKOUT_ZIPCODE_PROPERTY = "checkout.zipcode";

    public static CheckoutData withDataFromProperty() {
        return new CheckoutData(
                TestDataReader.getTestData(CHECKOUT_FIRSTNAME_PROPERTY),
                TestDataReader.getTestData(CHECKOUT_LASTNAME_PROPERTY),
                TestDataReader.getTestData(CHECKOUT_ZIPCODE_PROPERTY)
        );
    }
}