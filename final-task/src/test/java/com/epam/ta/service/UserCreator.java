package com.epam.ta.service;

import com.epam.ta.model.User;
import com.epam.ta.service.TestDataReader;

public class UserCreator {

    public static final String USER_LOGIN_PROPERTY = "user.login";
    public static final String USER_PASSWORD_PROPERTY = "user.password";

    public static User withCredentialsFromProperty() {
        return new User(
                TestDataReader.getTestData(USER_LOGIN_PROPERTY),
                TestDataReader.getTestData(USER_PASSWORD_PROPERTY)
        );
    }

    public static User withEmptyUsername() {
        return new User("", TestDataReader.getTestData(USER_PASSWORD_PROPERTY));
    }

    public static User withEmptyPassword() {
        return new User(TestDataReader.getTestData(USER_LOGIN_PROPERTY), "");
    }
}