package com.retronic.rest.jersey.mapper;

import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.USER)
public class RuntimeExceptionMapper implements ExceptionMapper<Throwable> {

    @Context
    HttpServletRequest req;

    @Override
    public Response toResponse(Throwable e) {
        if (isDebugMode(req)) {
            return Response.status(500).entity(ExceptionUtils.getStackTrace(e)).build();
        }
        return Response.status(500).build();
    }

    private boolean isDebugMode(HttpServletRequest req) {
        if (req != null) {
            String verbose = req.getHeader("VERBOSE");
            return "true".equalsIgnoreCase(verbose);
        }
        return false;
    }
}
