package com.orioninc.services.impl;

import com.orioninc.models.User;
import com.orioninc.services.TopicListener;
import com.orioninc.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class TopicListenerImpl implements TopicListener {
    @Autowired
    UsersService usersService;

    @KafkaListener(topics = "#{'${kafka.topic}'}", groupId = "group1", containerFactory = "kafkaListenerContainerFactory")
    public void listenJsonUsers(User user,
                                @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                                @Header(KafkaHeaders.RECEIVED_TOPIC) String topicName) {
        System.out.println("received from: " + topicName + user);

        usersService.saveUser(user);

        System.out.println("saved in database");
    }
}
