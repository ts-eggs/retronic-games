package com.retronic.rest.jersey.mapper;

import com.retronic.rest.jersey.dto.BadObjectExceptionDTO;
import com.retronic.security.exceptions.BadObjectException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.USER + 1)
public class BadObjectExceptionMapper implements ExceptionMapper<BadObjectException> {

    @Override
    public Response toResponse(BadObjectException e) {
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(new BadObjectExceptionDTO(e.getReason()))
                .build();
    }
}
