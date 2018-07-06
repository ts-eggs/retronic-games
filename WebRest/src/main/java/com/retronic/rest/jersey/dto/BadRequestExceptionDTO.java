package com.retronic.rest.jersey.dto;

public class BadRequestExceptionDTO {

    String reason;

    public BadRequestExceptionDTO(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
