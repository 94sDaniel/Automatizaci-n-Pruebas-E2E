package com.orangehrm.automation.commons;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class CredentialProvider {

    private static final String DEFAULT_USER = "admin";
    private static final String DEFAULT_PASSWORD = "admin123";

    private CredentialProvider() {
    }

    public static Credentials adminCredentials() {

        Config config = ConfigFactory.load();

        String username;
        String password;

        if (config.hasPath("orangehrm.admin.username")) {
            username = config.getString("orangehrm.admin.username");
        } else {
            username = DEFAULT_USER;
        }

        if (config.hasPath("orangehrm.admin.password")) {
            password = config.getString("orangehrm.admin.password");
        } else {
            password = DEFAULT_PASSWORD;
        }

        return new Credentials(username, password);
    }
}