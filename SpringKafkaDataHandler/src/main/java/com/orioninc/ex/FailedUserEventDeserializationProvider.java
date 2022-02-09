package com.orioninc.ex;

import com.orioninc.models.User;
import com.orioninc.models.UserEvent;
import org.springframework.kafka.support.serializer.FailedDeserializationInfo;

import java.util.function.Function;

public class FailedUserEventDeserializationProvider implements Function<FailedDeserializationInfo, UserEvent> {
    @Override
    public UserEvent apply(FailedDeserializationInfo failedDeserializationInfo) {
        System.out.println("deserialization failed");
        return new UserEvent();
    }
}
