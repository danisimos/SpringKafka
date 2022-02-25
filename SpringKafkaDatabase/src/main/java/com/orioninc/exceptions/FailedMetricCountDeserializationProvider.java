package com.orioninc.exceptions;

import com.orioninc.models.Subscription;
import org.springframework.kafka.support.serializer.FailedDeserializationInfo;

import java.util.function.Function;

public class FailedMetricCountDeserializationProvider implements Function<FailedDeserializationInfo, Subscription> {
    @Override
    public Subscription apply(FailedDeserializationInfo failedDeserializationInfo) {
        System.out.println("Deserialization failed");
        String s = new String(failedDeserializationInfo.getData());
        System.out.println(s);
        System.out.println(failedDeserializationInfo.isForKey());
        return new Subscription();
    }
}
