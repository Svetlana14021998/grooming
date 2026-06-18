package org.example.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
@Getter
public class AppConfig {

    @Value("${app.phone}")
    private String phone;

    @Value("${app.email}")
    private String email;

    @Value("${app.work-hours}")
    private String workHours;
}
