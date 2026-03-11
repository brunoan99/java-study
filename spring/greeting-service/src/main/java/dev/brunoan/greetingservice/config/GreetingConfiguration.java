package dev.brunoan.greetingservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "greeting-service")
public record GreetingConfiguration(String template, String defaultName) {

}
