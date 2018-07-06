package com.retronic.rest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ImportResource({"classpath*:spring-rest.xml"})
@EnableScheduling
@EnableAsync
public class RemotingWebConfig {
}
