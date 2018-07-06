package com.retronic.remoting.validation;

public class AbstractValidationObject<T> implements ValidationObject<T> {

    private T dto;
    private boolean isCreate;

    public AbstractValidationObject(T dto, boolean isCreate) {
        this.dto = dto;
        this.isCreate = isCreate;
    }

    public T getDto() {
        return this.dto;
    }

    public boolean isCreate() {
        return isCreate;
    }
}
