package com.retronic.remoting.validation;

import com.google.gson.JsonElement;

import java.io.Serializable;

public interface IValidationFailure extends Serializable {

    JsonElement getFailure();
}
