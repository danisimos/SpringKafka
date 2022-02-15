package com.orioninc.services;

import com.orioninc.models.Interval;
import com.orioninc.models.Subscription;
import com.orioninc.models.User;
import com.orioninc.models.ProcessedIntervalSubscriptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class SendService {
    @Autowired
    KafkaTemplate<User, Subscription> kafkaTemplateSubscription;

    @Autowired
    KafkaTemplate<Interval, ProcessedIntervalSubscriptions> kafkaTemplateProcessedIntervalSubscriptions;

    public String send(User user, Subscription subscription) {
        ListenableFuture<SendResult<User, Subscription>> listenableFuture = kafkaTemplateSubscription.sendDefault(user, subscription);

        listenableFuture.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Failed send new subscription to " + kafkaTemplateSubscription.getDefaultTopic());
            }

            @Override
            public void onSuccess(SendResult<User, Subscription> result) {
                System.out.println("Subscription sent to " + kafkaTemplateSubscription.getDefaultTopic());
            }
        });

        return user.toString() + subscription.toString();
    }

    public void send(Interval interval, ProcessedIntervalSubscriptions processedIntervalSubscriptions) {
        ListenableFuture<SendResult<Interval, ProcessedIntervalSubscriptions>> listenableFuture = kafkaTemplateProcessedIntervalSubscriptions.sendDefault(interval, processedIntervalSubscriptions);

        listenableFuture.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Failed to send processed data");
            }

            @Override
            public void onSuccess(SendResult<Interval, ProcessedIntervalSubscriptions> result) {
                System.out.println("Success sent processed data");
            }
        });
    }
}
