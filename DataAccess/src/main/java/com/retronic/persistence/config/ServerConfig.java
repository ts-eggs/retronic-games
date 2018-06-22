package com.retronic.persistence.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
public class ServerConfig {

    public static final String ENVIRONMENT_LOCAL = "LOCAL";
    public static final String ENVIRONMENT_DEVELOPMENT = "DEV";

    @Value("${rgc.environment}")
    private String environment;

    @Value("${rgc.datapath}")
    private String dataPath;

    @Value("${rgc.domain}")
    private String domain;

    @Value("${rgc.host}")
    private String host;

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getDataPath() {
        return dataPath;
    }

    public void setDataPath(String dataPath) {
        this.dataPath = dataPath;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getVersion() {
        InputStream inputStream = ServerConfig.class.getClassLoader().getResourceAsStream("pom.properties");
        Properties props = new Properties();

        if (inputStream == null) {
            return ENVIRONMENT_LOCAL;
        }

        try {
            props.load(inputStream);
        } catch (IOException e) {
            // error is not needed to be handled
        }

        return props.getProperty("version");
    }

    public String getLocalInstanceUrl() {
        String url = "http://localhost";
        String catalinaBase = System.getProperty("catalina.base");

        if (catalinaBase != null && catalinaBase.length() > 0) {
            String port = catalinaBase.substring(catalinaBase.length() - 3);
            url += ":43" + port;
        }

        return url;
    }

    public boolean isDev() {
        return ENVIRONMENT_DEVELOPMENT.equalsIgnoreCase(environment);
    }

    public boolean isLocal() {
        return ENVIRONMENT_LOCAL.equalsIgnoreCase(environment);
    }
}
