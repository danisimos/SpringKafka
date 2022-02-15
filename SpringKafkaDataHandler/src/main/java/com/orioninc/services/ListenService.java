package com.orioninc.services;

import com.orioninc.models.User;
import com.orioninc.models.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class ListenService {
    @Autowired
    UserSchedulingService userSchedulingService;


    @KafkaListener(topics = "#{'${kafka.topics.first}'}", groupId = "group1")
    public void listenJsonUsers(Subscription subscription,
                                @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long timestamp,
                                @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) User user,
                                @Header(KafkaHeaders.RECEIVED_TOPIC) String topicName) {
        subscription.setHandledTimestamp(timestamp);
        System.out.println("Received from: " + topicName + user + " " + subscription);

        userSchedulingService.process(user, subscription);
    }

}
