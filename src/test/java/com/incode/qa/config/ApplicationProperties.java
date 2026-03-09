package com.incode.qa.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "app")
public class ApplicationProperties {

    private String browser;
    private boolean headless;
    private int waitTimeSeconds;
    private String baseUrl;
    private String email;
    private String password;
}
