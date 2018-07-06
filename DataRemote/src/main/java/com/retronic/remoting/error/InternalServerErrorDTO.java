package com.retronic.remoting.error;

public class InternalServerErrorDTO {

    private String message;

    public InternalServerErrorDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
