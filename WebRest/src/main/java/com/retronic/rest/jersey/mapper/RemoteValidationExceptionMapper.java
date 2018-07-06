package com.retronic.rest.jersey.mapper;

import com.retronic.remoting.utils.ResponseUtil;
import com.retronic.remoting.validation.RemoteValidationException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.USER + 1)
public class RemoteValidationExceptionMapper implements ExceptionMapper<RemoteValidationException> {

    @Override
    public Response toResponse(RemoteValidationException e) {
        return ResponseUtil.validationError(e.getValidationResult());
    }
}
