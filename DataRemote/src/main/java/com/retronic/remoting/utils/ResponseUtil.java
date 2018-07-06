package com.retronic.remoting.utils;

import com.google.gson.JsonArray;
import com.retronic.remoting.error.Error;
import com.retronic.remoting.error.ErrorMessage;
import com.retronic.remoting.error.IError;
import com.retronic.remoting.validation.IValidationFailure;
import com.retronic.remoting.validation.ValidationResult;

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

    public static Response badRequest(IError error) {
        return badRequest(error, null, null);
    }

    public static Response badRequest(IError error, String additionalInfo) {
        return badRequest(error, additionalInfo, null);
    }

    public static Response badRequest(IError error, String additionalInfo, Object additionalJson) {
        ErrorMessage message = new ErrorMessage(error.name(), error.getMessage(), error.getTranslationPath());
        message.setAdditionalInfo(additionalInfo);
        message.setAdditionalJson(additionalJson);
        return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
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

    public static Response validationError(IError error) {
        return ResponseUtil.badRequest(error, null, null);
    }

    public static Response validationError(ValidationResult validationResult) {
        JsonArray jsonArray = new JsonArray();

        for (int i = 0; i < validationResult.getValidationFailures().size(); i++) {
            IValidationFailure validationFailure = validationResult.getValidationFailures().get(i);
            jsonArray.add(validationFailure.getFailure());
        }
        return ResponseUtil.badRequest(Error.VALIDATION_ERROR, null, jsonArray.toString());
    }
}
