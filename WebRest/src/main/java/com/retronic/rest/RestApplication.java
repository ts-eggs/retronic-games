package com.retronic.rest;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.filter.HttpMethodOverrideFilter;
import org.springframework.web.filter.RequestContextFilter;

public class RestApplication extends ResourceConfig {

    public RestApplication() {
        register(RequestContextFilter.class);
        register(JacksonFeature.class);
        register(HttpMethodOverrideFilter.class);
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        packages("com.retronic.remoting", "com.retronic.rest.jersey");
    }
}