package com.orioninc.exceptions;

import com.orioninc.models.Interval;
import org.springframework.kafka.support.serializer.FailedDeserializationInfo;

import java.util.function.Function;

public class FailedIntervalDeserializationProvider implements Function<FailedDeserializationInfo, Interval> {
    @Override
    public Interval apply(FailedDeserializationInfo failedDeserializationInfo) {
        System.out.println("Deserialization failed");
        String s = new String(failedDeserializationInfo.getData());
        System.out.println(s);
        System.out.println(failedDeserializationInfo.isForKey());
        return new Interval();
    }
}
