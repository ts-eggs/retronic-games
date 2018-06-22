package com.retronic.security.exceptions;

public class BadRequestException extends RuntimeException {

    private final String reason;

    public BadRequestException(final String reason) {
        super();
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
