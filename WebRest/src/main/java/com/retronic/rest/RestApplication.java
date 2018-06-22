package com.retronic.rest;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.web.filter.RequestContextFilter;

public class RestApplication extends ResourceConfig {

    public RestApplication() {
        register(RequestContextFilter.class);
        register(JacksonFeature.class);
        packages("com.retronic.remoting");
    }
}