package com.retronic.rest.jersey.dto;

public class BadObjectExceptionDTO {

    String reason;

    public BadObjectExceptionDTO(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
