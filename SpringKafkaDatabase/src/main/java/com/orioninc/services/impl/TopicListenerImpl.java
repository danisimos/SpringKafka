package com.orioninc.services.impl;

import com.orioninc.models.Interval;
import com.orioninc.models.ProcessedIntervalSubscriptions;
import com.orioninc.services.TopicListener;
import com.orioninc.services.ProcessedIntervalSubscriptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class TopicListenerImpl implements TopicListener {
    @Autowired
    ProcessedIntervalSubscriptionsService processedIntervalSubscriptionsService;

    @KafkaListener(topics = "#{'${kafka.topic}'}", groupId = "group1", containerFactory = "kafkaListenerContainerFactory")
    public void listenProcessedIntervalSubscriptions(ProcessedIntervalSubscriptions subscriptions,
                                                     @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY)Interval interval,
                                                     @Header(KafkaHeaders.RECEIVED_TOPIC) String topicName) {
        System.out.println("Received from: " + topicName + subscriptions);

        processedIntervalSubscriptionsService.saveProcessedIntervalSubscriptions(subscriptions);

        System.out.println("Saved in database");
    }
}
