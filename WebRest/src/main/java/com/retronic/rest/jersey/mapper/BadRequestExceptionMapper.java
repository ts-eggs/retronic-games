package com.retronic.rest.jersey.mapper;

import com.retronic.rest.jersey.dto.BadRequestExceptionDTO;
import com.retronic.security.exceptions.BadRequestException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.USER + 1)
public class BadRequestExceptionMapper implements ExceptionMapper<BadRequestException> {

    @Override
    public Response toResponse(BadRequestException e) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(new BadRequestExceptionDTO(e.getReason()))
                .build();
    }
}
