package com.retronic.remoting.utils;

import javax.ws.rs.core.Response;

public class ResponseUtil {

    private ResponseUtil() {
        //implicit constructor to hide public one
    }

    public static Response created(Object dtos) {
        return Response.status(Response.Status.CREATED).entity(dtos).build();
    }

    public static Response created() {
        return Response.status(Response.Status.CREATED).build();
    }

    public static Response updated(Object dtos) {
        return Response.status(Response.Status.OK).entity(dtos).build();
    }

    public static Response updated() {
        return Response.status(Response.Status.OK).build();
    }

    public static Response deleted(Object dtos) {
        return Response.status(Response.Status.NO_CONTENT).entity(dtos).build();
    }

    public static Response deleted() {
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    public static Response ok(Object dtos) {
        return Response.status(Response.Status.OK).entity(dtos).build();
    }

    public static Response ok() {
        return Response.status(Response.Status.OK).build();
    }

    public static Response badRequest() {
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    public static Response notFound(String message) {
        return Response.status(Response.Status.NOT_FOUND).entity(message).build();
    }

    public static Response noContent(String message) {
        return Response.status(Response.Status.NO_CONTENT).entity(message).build();
    }

    public static Response error(String message) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(message).build();
    }

    public static Response badGateway(String message) {
        return Response.status(Response.Status.BAD_GATEWAY).entity(message).build();
    }

    public static Response serviceUnavailable(String message) {
        return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(message).build();
    }

    public static Response badGateway() {
        return Response.status(Response.Status.BAD_GATEWAY).build();
    }

    public static Response notAuthorized() {
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    public static Response notFound() {
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
