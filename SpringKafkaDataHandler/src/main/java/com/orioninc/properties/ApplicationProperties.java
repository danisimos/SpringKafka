package com.orioninc.properties;

import lombok.Data;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
@Data
public class ApplicationProperties {
    private String usersProcessIntervalMinutes;
    private String usersProcessIntervalFromTo;
}
