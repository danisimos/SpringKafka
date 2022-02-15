package com.orioninc.exceptions;

import com.orioninc.models.Subscription;
import org.springframework.kafka.support.serializer.FailedDeserializationInfo;

import java.util.function.Function;

public class FailedSubscriptionDeserializationProvider implements Function<FailedDeserializationInfo, Subscription> {
    @Override
    public Subscription apply(FailedDeserializationInfo failedDeserializationInfo) {
        System.out.println("deserialization failed");
        return new Subscription();
    }
}
