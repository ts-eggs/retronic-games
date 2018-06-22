package com.retronic.persistence.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class EnvironmentConfig {

    @Bean
    PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
        List<Resource> resources = new ArrayList<>();
        resources.add(new ClassPathResource("serverConfig.properties"));
        resources.add(new FileSystemResource("C:/serverconfig/localRetronic.properties"));
        String configurationUrl = System.getProperty("configuration.url");

        if (configurationUrl != null) {
            resources.add(new FileSystemResource(configurationUrl));
        }

        configurer.setLocations(resources.toArray(new Resource[]{}));
        configurer.setIgnoreResourceNotFound(true);
        configurer.setIgnoreUnresolvablePlaceholders(true);
        return configurer;
    }
}
