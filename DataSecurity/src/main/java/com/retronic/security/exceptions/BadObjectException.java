package com.retronic.security.exceptions;

public class BadObjectException extends RuntimeException {

    private final String reason;

    public BadObjectException(final String reason) {
        super();
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
