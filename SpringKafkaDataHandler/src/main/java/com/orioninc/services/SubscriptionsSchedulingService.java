package com.orioninc.services;

import com.orioninc.models.User;
import com.orioninc.models.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class SubscriptionsSchedulingService {
    @Autowired
    private IntervalSubscriptionsProcessingService intervalSubscriptionsProcessingService;

    private Map<User, List<Subscription>> usersEvents = Collections.synchronizedMap(new HashMap<>());

    private long startIntervalTimestamp = 0;

    @Scheduled(cron = "${app.users-process-interval}")
    public void interval() {
        if(startIntervalTimestamp == 0) {
            startIntervalTimestamp = System.currentTimeMillis();
            return;
        }

        long endIntervalTimestamp = System.currentTimeMillis();

        //intervalSubscriptionsProcessingService.process(usersEvents, startIntervalTimestamp, endIntervalTimestamp);
        //usersEvents.clear();
        System.out.println(new Timestamp(startIntervalTimestamp).toString() + "         " + new Timestamp(endIntervalTimestamp).toString());

        startIntervalTimestamp = 0;
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
