package com.orioninc.services;

import com.orioninc.models.Subscription;
import com.orioninc.models.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class MetricsCountService {
    private static final Logger logger = Logger.getLogger(MetricsCountService.class);
    private int count = 0;

    public void process(User user, Subscription subscription) {
        count++;
        logger.info(user + ", " + subscription + ", count: " + count);
    }

    public int getCount() {
        return count;
    }
}