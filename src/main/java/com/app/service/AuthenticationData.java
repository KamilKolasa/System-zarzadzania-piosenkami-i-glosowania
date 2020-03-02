package com.app.service;

public final class AuthenticationData {

    private AuthenticationData() {}

    private static String authenticatedUser;

    public static void setAuthenticatedUser(String username) {
        authenticatedUser = username;
    }

    public static String getAuthenticatedUser() {
        return authenticatedUser;
    }

}
