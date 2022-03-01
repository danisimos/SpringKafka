package com.orioninc.services.impl;

import com.orioninc.models.Subscription;
import com.orioninc.models.User;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MetricsCountService {
    private final SendService sendService;

    private static final Logger logger = Logger.getLogger(MetricsCountService.class);
    private int subscriptionsCount = 1;

    public void process(User user, Subscription subscription) {
        subscriptionsCount++;
        logger.info(user + ", " + subscription + ", subscriptions count: " + subscriptionsCount);
        sendService.sentToMetricCountTopic(subscription, subscriptionsCount);
    }

    public int getCount() {
        return subscriptionsCount;
    }
}