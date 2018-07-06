package com.retronic.remoting.validation.types;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.retronic.remoting.validation.IValidationFailure;

public class ConstraintViolationFailure implements IValidationFailure {

    private String name;
    private transient Object value;
    private String failure;

    public ConstraintViolationFailure(String name, Object value, String failure) {
        this.name = name;
        this.value = value;
        this.failure = failure;
    }

    @Override
    public JsonElement getFailure() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", name);
        jsonObject.addProperty("value", value != null ? value.toString() : "null");
        jsonObject.addProperty("failure", failure != null ? failure : "null");
        return jsonObject;
    }
}
