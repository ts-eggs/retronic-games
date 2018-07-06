package com.retronic.remoting.validation;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {

    private List<IValidationFailure> validationFailures;

    public List<IValidationFailure> getValidationFailures() {
        return validationFailures;
    }

    public void setValidationFailures(List<IValidationFailure> validationFailures) {
        this.validationFailures = validationFailures;
    }

    public boolean isOk() {
        return validationFailures.isEmpty();
    }

    public void add(IValidationFailure validationFailure) {
        this.validationFailures.add(validationFailure);
    }

    public static ValidationResult getNewInstance() {
        ValidationResult validationResult = new ValidationResult();
        List<IValidationFailure> validationFailures = new ArrayList<>();
        validationResult.setValidationFailures(validationFailures);
        return validationResult;
    }
}
