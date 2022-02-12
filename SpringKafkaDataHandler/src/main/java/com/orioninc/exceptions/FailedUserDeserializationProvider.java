package com.orioninc.exceptions;

import com.orioninc.models.User;
import org.springframework.kafka.support.serializer.FailedDeserializationInfo;

import java.util.function.Function;

public class FailedUserDeserializationProvider implements Function<FailedDeserializationInfo, User> {
    @Override
    public User apply(FailedDeserializationInfo failedDeserializationInfo) {
        System.out.println("deserialization failed");
        return new User();
    }
}
