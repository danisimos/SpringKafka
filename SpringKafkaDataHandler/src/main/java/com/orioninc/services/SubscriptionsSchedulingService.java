package com.orioninc.services;

import com.orioninc.models.User;
import com.orioninc.models.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SubscriptionsSchedulingService {
    @Autowired
    private IntervalSubscriptionsProcessingService intervalSubscriptionsProcessingService;

    private Map<User, List<Subscription>> usersEvents = Collections.synchronizedMap(new HashMap<>());

    private long startIntervalTimestamp;

    @Scheduled(cron = "${app.users-process-interval}")
    public void interval() {
        long endIntervalTimestamp = System.currentTimeMillis();

        intervalSubscriptionsProcessingService.process(usersEvents, startIntervalTimestamp, endIntervalTimestamp);
        usersEvents.clear();

        startIntervalTimestamp = endIntervalTimestamp;
    }

    public void process(User user, Subscription subscription) {
        if(!usersEvents.containsKey(user)) {
            List<Subscription> subscriptions = new ArrayList<>();
            subscriptions.add(subscription);
            usersEvents.put(user, subscriptions);
        } else {
            usersEvents.get(user).add(subscription);
        }
    }
}
