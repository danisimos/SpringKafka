package com.orioninc.exceptions;

import com.orioninc.models.ProcessedIntervalSubscriptions;
import org.springframework.kafka.support.serializer.FailedDeserializationInfo;

import java.util.function.Function;

public class FailedProcessedIntervalSubscriptionsDeserializationProvider implements Function<FailedDeserializationInfo, ProcessedIntervalSubscriptions> {
    @Override
    public ProcessedIntervalSubscriptions apply(FailedDeserializationInfo failedDeserializationInfo) {
        System.out.println("Deserialization failed");
        return new ProcessedIntervalSubscriptions();
    }
}
