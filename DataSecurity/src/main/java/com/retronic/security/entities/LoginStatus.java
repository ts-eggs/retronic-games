package com.retronic.security.entities;

import java.io.Serializable;

public class LoginStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    private final boolean success;
    private final boolean loggedIn;
    private final String username;
    private final String errorMessage;

    public LoginStatus(boolean success, boolean loggedIn, String username, String errorMessage) {
        this.success = success;
        this.loggedIn = loggedIn;
        this.username = username;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public String getUsername() {
        return username;
    }

    public boolean isSuccess() {
        return success;
    }
}
