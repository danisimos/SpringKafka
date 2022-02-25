package com.orioninc.services;

import com.orioninc.models.Interval;
import com.orioninc.models.Subscription;
import com.orioninc.models.User;
import com.orioninc.models.ProcessedIntervalSubscriptions;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@RequiredArgsConstructor
public class SendService {
    private final KafkaTemplate<User, Subscription> kafkaTemplateSubscription;
    private final KafkaTemplate<Interval, ProcessedIntervalSubscriptions> kafkaTemplateProcessedIntervalSubscriptions;
    private final KafkaTemplate<String, Subscription> kafkaTemplateMetricCount;

    private static final Logger logger = Logger.getLogger(SendService.class);

    public String sendToSubscriptionsTopic(Subscription subscription) {
        ListenableFuture<SendResult<User, Subscription>> listenableFuture = kafkaTemplateSubscription.sendDefault(subscription.getUser(), subscription);

        listenableFuture.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable ex) {
                logger.info("Failed send new subscription to " + kafkaTemplateSubscription.getDefaultTopic());
            }

            @Override
            public void onSuccess(SendResult<User, Subscription> result) {
                logger.info("Subscription sent to " + kafkaTemplateSubscription.getDefaultTopic());
            }
        });

        return subscription.getUser().toString() + subscription.toString();
    }

    public void sendToIntervalsTopic(Interval interval, ProcessedIntervalSubscriptions processedIntervalSubscriptions) {
        ListenableFuture<SendResult<Interval, ProcessedIntervalSubscriptions>> listenableFuture = kafkaTemplateProcessedIntervalSubscriptions.sendDefault(interval, processedIntervalSubscriptions);

        listenableFuture.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable ex) {
                logger.info("Failed to send processed data");
            }

            @Override
            public void onSuccess(SendResult<Interval, ProcessedIntervalSubscriptions> result) {
                logger.info("Success sent processed data");
            }
        });
    }

    public void sentToMetricCountTopic(Subscription subscription, int subscriptionsCount) {
        ListenableFuture<SendResult<String, Subscription>> listenableFuture = kafkaTemplateMetricCount.sendDefault(Integer.toString(subscriptionsCount), subscription);

        listenableFuture.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable ex) {
                logger.info("Failed to send to metric count topic");
            }

            @Override
            public void onSuccess(SendResult<String, Subscription> result) {
                logger.info("Success sent to metric count topic");
            }
        });
    }
}
