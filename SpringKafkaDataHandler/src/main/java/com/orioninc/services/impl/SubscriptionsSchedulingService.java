package com.orioninc.services.impl;

import com.orioninc.models.Subscription;
import com.orioninc.models.User;
import com.orioninc.properties.ApplicationProperties;
import org.joda.time.LocalTime;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class SubscriptionsSchedulingService {
    private final IntervalSubscriptionsProcessingService intervalSubscriptionsProcessingService;
    private final ApplicationProperties applicationProperties;

    private final Map<User, List<Subscription>> usersEvents = Collections.synchronizedMap(new HashMap<>());

    private final int intervalMinutes;

    public SubscriptionsSchedulingService(IntervalSubscriptionsProcessingService intervalSubscriptionsProcessingService,
                                          ApplicationProperties applicationProperties) {
        this.intervalSubscriptionsProcessingService = intervalSubscriptionsProcessingService;
        this.applicationProperties = applicationProperties;
        this.intervalMinutes = Integer.parseInt(applicationProperties.getUsersProcessIntervalMinutes());
    }

    private long intervalStartTimestamp;
    public void interval() {
        long intervalEndTimestamp = System.currentTimeMillis();

        intervalSubscriptionsProcessingService.process(usersEvents, intervalStartTimestamp, intervalEndTimestamp);
        usersEvents.clear();

        intervalStartTimestamp = intervalEndTimestamp;
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

    public Date getNextExecutionDate(Date lastCompletionTime) {
        LocalTime nextExecution;

        if(lastCompletionTime == null) {
            interval();
            nextExecution = new LocalTime(LocalTime.now().getHourOfDay(), 0);
            while(LocalTime.now().isAfter(nextExecution)) {
                nextExecution = nextExecution.plusMinutes(intervalMinutes);
            }

            intervalStartTimestamp = nextExecution.minusMinutes(intervalMinutes).toDateTimeToday().toDate().getTime();
            return nextExecution.toDateTimeToday().toDate();
        }

        nextExecution = LocalTime.fromDateFields(lastCompletionTime).plusMinutes(intervalMinutes);

        return nextExecution.toDateTimeToday().toDate();
    }
}
