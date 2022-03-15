package com.orioninc.services.impl;

import com.orioninc.models.Subscription;
import com.orioninc.models.User;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
@RequiredArgsConstructor
public class MetricsCountService {
    private final SendService sendService;

    private static final Logger logger = Logger.getLogger(MetricsCountService.class);

    public void process(User user, Subscription subscription, long timestamp) {
        logger.info(user + ", " + subscription + ", Date: " + new Date(timestamp));
        sendService.sentToMetricCountTopic(subscription, timestamp);
    }
}