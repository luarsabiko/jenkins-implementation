package com.epam.ta.util;

public class StringUtils {

    private StringUtils() {}

    public static String toButtonId(String productName) {
        return "add-to-cart-" + productName.toLowerCase().replace(" ", "-");
    }
}