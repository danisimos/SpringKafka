package com.orioninc.services;

import com.orioninc.models.User;
import com.orioninc.models.UserEvent;
import com.orioninc.properties.KafkaProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.HashMap;
import java.util.Map;

@Service
public class ListenService {
    @Autowired
    UserService userService;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplateString;

    public String send(String key, String value) {
        ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplateString.sendDefault(key, value);
        System.out.println("done");

        listenableFuture.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("failed to send to " + kafkaTemplateString.getDefaultTopic());
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("sent to " + kafkaTemplateString.getDefaultTopic());
            }
        });

        return value;
    }

    @KafkaListener(topics = "#{'${kafka.topics.first}'}",
            groupId = "json",
            containerFactory = "jsonUsersKafkaListenerContainerFactory")
    public void listenJsonUsers(UserEvent userEvent,
                                @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long timestamp,
                                @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) User user,
                                @Header(KafkaHeaders.RECEIVED_TOPIC) String topicName) {
        System.out.println("received from: " + topicName + user + " " + userEvent);
        user.setHandledTimestamp(timestamp);

        userService.process(user, userEvent);
    }

}
