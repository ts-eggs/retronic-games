package com.retronic.remoting.validation;

public abstract class AbstractRemoteValidator<V extends ValidationObject> {

    public void validateREST(V validationObject) {
        ValidationResult validationResult = validate(validationObject);
        if (!validationResult.isOk()) {
            throw new RemoteValidationException(validationResult);
        }
    }

    protected abstract ValidationResult validate(V validationObject);
}
