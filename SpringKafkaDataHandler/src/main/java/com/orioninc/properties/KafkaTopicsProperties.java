package com.orioninc.properties;

import lombok.Data;

@Data
public class KafkaTopicsProperties {
    private String subscriptionsTopic;
    private String intervalsTopic;
    private String metricCountTopic;
}
