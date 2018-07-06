package com.retronic.remoting.error;

public class NotFoundDTO {

    private String message;

    public NotFoundDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
