package com.retronic.rest.jersey.mapper;

import com.retronic.remoting.validation.ValidationResult;
import com.retronic.remoting.validation.types.ConstraintViolationFailure;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.internal.engine.path.PathImpl;

import javax.validation.ConstraintViolation;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ConstraintViolationsValidationResultMapper {

    private static Map<Class, String> annotationStringMap;

    static {
        annotationStringMap = new HashMap<>();
        annotationStringMap.put(NotNull.class, "null not allowed");
        annotationStringMap.put(NotEmpty.class, "list is empty");
        annotationStringMap.put(Pattern.class, "Pattern mismatch. %s");
    }

    private ConstraintViolationsValidationResultMapper() {
    }

    public static ValidationResult map(Set<ConstraintViolation<?>> violations) {
        ValidationResult validationResult = ValidationResult.getNewInstance();
        for (ConstraintViolation violation : violations) {
            ConstraintViolationFailure failure = map(violation);
            if (failure != null) {
                validationResult.add(failure);
            }
        }
        return validationResult;
    }

    private static ConstraintViolationFailure map(ConstraintViolation violation) {
        try {
            Class constraintClass = violation.getConstraintDescriptor().getAnnotation().annotationType();
            if (annotationStringMap.containsKey(constraintClass)) {
                PathImpl path = (PathImpl) violation.getPropertyPath();
                if (path != null) {
                    String name = path.getLeafNode().getName();
                    String errorMessage = annotationStringMap.get(constraintClass);
                    errorMessage = extractErrorMessage(violation, errorMessage);
                    return new ConstraintViolationFailure(name, buildToString(violation.getInvalidValue()), errorMessage);
                }
            } else {
                return new ConstraintViolationFailure(constraintClass.getSimpleName(), buildToString(violation.getInvalidValue()), "unknown error");
            }
        } catch (Exception e) {
            // do nothing
        }
        return null;
    }

    private static String extractErrorMessage(ConstraintViolation violation, String errorMessage) {
        if (errorMessage.contains("%s")) {
            Object value = violation.getConstraintDescriptor().getAttributes().get("value");
            if (value == null) {
                value = violation.getMessage();
            }

            if (value != null) {
                errorMessage = String.format(errorMessage, value.toString());
            }
        }
        return errorMessage;
    }

    private static String buildToString(Object o) {
        if (o == null) {
            return null;
        }
        if (o instanceof Object[]) {
            StringBuilder sb = new StringBuilder();
            for (Object o1 : (Object[]) o) {
                sb.append(o1.toString());
            }
            return sb.toString();
        }
        return o.toString();
    }
}
