package com.orioninc.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
public class KafkaTopicsProperties {
    private String subscriptionsTopic;
    private String intervalsTopic;
    private String metricCountTopic;
}
