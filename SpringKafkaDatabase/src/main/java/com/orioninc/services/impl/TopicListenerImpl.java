package com.orioninc.services.impl;

import com.orioninc.models.Interval;
import com.orioninc.models.ProcessedIntervalSubscriptions;
import com.orioninc.services.TopicListener;
import com.orioninc.services.ProcessedIntervalSubscriptionsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class TopicListenerImpl implements TopicListener {
    @Autowired
    ProcessedIntervalSubscriptionsService processedIntervalSubscriptionsService;

    private static final Logger logger = Logger.getLogger(TopicListenerImpl.class);

    @KafkaListener(topics = "#{'${kafka.topic}'}", groupId = "group1", containerFactory = "kafkaListenerContainerFactory")
    public void listenProcessedIntervalSubscriptions(ProcessedIntervalSubscriptions subscriptions,
                                                     @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY)Interval interval,
                                                     @Header(KafkaHeaders.RECEIVED_TOPIC) String topicName) {
        logger.info("Received from: " + topicName + subscriptions);

        processedIntervalSubscriptionsService.saveProcessedIntervalSubscriptions(subscriptions);

        logger.info("Saved in database");
    }
}
