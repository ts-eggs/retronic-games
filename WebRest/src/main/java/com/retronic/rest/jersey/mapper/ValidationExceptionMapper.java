package com.retronic.rest.jersey.mapper;

import com.retronic.remoting.utils.ResponseUtil;
import com.retronic.remoting.validation.ValidationResult;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Set;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

    @Override
    public Response toResponse(ValidationException exception) {
        if (exception instanceof ConstraintViolationException) {
            Set<ConstraintViolation<?>> violations = ((ConstraintViolationException) exception).getConstraintViolations();
            ValidationResult validationResult = ConstraintViolationsValidationResultMapper.map(violations);
            return ResponseUtil.validationError(validationResult);
        }

        return Response.status(400).entity(exception).build();
    }
}
