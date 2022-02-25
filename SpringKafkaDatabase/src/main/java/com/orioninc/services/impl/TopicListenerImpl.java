package com.orioninc.services.impl;

import com.orioninc.models.Interval;
import com.orioninc.models.ProcessedIntervalSubscriptions;
import com.orioninc.models.Subscription;
import com.orioninc.services.SubscriptionsService;
import com.orioninc.services.TopicListener;
import com.orioninc.services.ProcessedIntervalSubscriptionsService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TopicListenerImpl implements TopicListener {
    private final ProcessedIntervalSubscriptionsService processedIntervalSubscriptionsService;
    private final SubscriptionsService subscriptionsService;

    private static final Logger logger = Logger.getLogger(TopicListenerImpl.class);

    @KafkaListener(topics = "${kafka.topics.intervals-topic}",
            groupId = "group1",
            containerFactory = "kafkaListenerContainerFactoryProcessedIntervalSubscriptions")
    public void listenProcessedIntervalSubscriptions(ProcessedIntervalSubscriptions subscriptions,
                                                     @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY)Interval interval,
                                                     @Header(KafkaHeaders.RECEIVED_TOPIC) String topicName) {
        logger.info("Received from: " + topicName + subscriptions);

        processedIntervalSubscriptionsService.saveProcessedIntervalSubscriptions(subscriptions);

        logger.info("Saved in database");
    }

    @KafkaListener(topics = "${kafka.topics.metric_count_topic}",
            groupId = "group2",
            containerFactory = "kafkaListenerContainerFactoryMetricCount")
    public void listenMetricCount(@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String count, Subscription subscription) {
        subscriptionsService.saveSubscription(subscription);
    }
}
