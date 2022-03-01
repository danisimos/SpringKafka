package com.orioninc.services.impl;

import com.orioninc.models.Subscription;
import com.orioninc.models.User;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListenService {
    private final SubscriptionsSchedulingService subscriptionsSchedulingService;
    private final MetricsCountService metricsCountService;

    private static final Logger logger = Logger.getLogger(ListenService.class);

    @KafkaListener(topics = "${kafka.topics.subscriptions-topic}", groupId = "group1")
    public void listenJsonUsers(Subscription subscription,
                                @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) User user,
                                @Header(KafkaHeaders.RECEIVED_TOPIC) String topicName) {
        logger.info("Received from: " + topicName + user + " " + subscription);

        subscriptionsSchedulingService.process(user, subscription);
        metricsCountService.process(user, subscription);
    }

}
