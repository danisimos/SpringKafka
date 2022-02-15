package com.orioninc.services;

import com.orioninc.models.User;
import com.orioninc.models.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class ListenService {
    @Autowired
    SubscriptionsSchedulingService subscriptionsSchedulingService;


    @KafkaListener(topics = "${kafka.topics.first}", groupId = "group1")
    public void listenJsonUsers(Subscription subscription,
                                @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) User user,
                                @Header(KafkaHeaders.RECEIVED_TOPIC) String topicName) {
        System.out.println("Received from: " + topicName + user + " " + subscription);

        subscriptionsSchedulingService.process(user, subscription);
    }

}
