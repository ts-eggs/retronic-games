package com.retronic.remoting.validation;

import java.util.Collections;

public class RemoteValidationException extends RuntimeException {

    private final transient ValidationResult validationResult;

    public RemoteValidationException(ValidationResult validationResult) {
        super(validationResult.getValidationFailures().get(0).getClass().getSimpleName());
        this.validationResult = validationResult;
    }

    public RemoteValidationException(IValidationFailure validationFailure) {
        super(validationFailure.getClass().getSimpleName());
        this.validationResult = new ValidationResult();
        this.validationResult.setValidationFailures(Collections.singletonList(validationFailure));
    }

    public ValidationResult getValidationResult() {
        return validationResult;
    }
}
