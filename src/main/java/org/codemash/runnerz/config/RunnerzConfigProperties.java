package org.codemash.runnerz.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("runnerz")
public record RunnerzConfigProperties(String welcomeMessage, Integer maxRows) {
}
