package com.orioninc.services;

import com.orioninc.models.User;
import com.orioninc.models.Subscription;
import com.orioninc.properties.ApplicationProperties;
import org.apache.kafka.common.protocol.types.Field;
import org.joda.time.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;


@Service
public class SubscriptionsSchedulingService {
    private final IntervalSubscriptionsProcessingService intervalSubscriptionsProcessingService;
    private final ApplicationProperties applicationProperties;

    private final Map<User, List<Subscription>> usersEvents = Collections.synchronizedMap(new HashMap<>());

    private final LocalTime from;
    private final LocalTime to;
    private final int intervalMinutes;

    public SubscriptionsSchedulingService(IntervalSubscriptionsProcessingService intervalSubscriptionsProcessingService, ApplicationProperties applicationProperties) {
        this.intervalSubscriptionsProcessingService = intervalSubscriptionsProcessingService;
        this.applicationProperties = applicationProperties;

        this.intervalMinutes = Integer.parseInt(applicationProperties.getUsersProcessIntervalMinutes());

        String[] fromToProperty = applicationProperties.getUsersProcessIntervalFromTo().split("-");

        this.from = new LocalTime(Integer.parseInt(fromToProperty[0].split(":")[0]),
                Integer.parseInt(fromToProperty[0].split(":")[1]));
        this.to = new LocalTime(Integer.parseInt(fromToProperty[1].split(":")[0]),
                Integer.parseInt(fromToProperty[1].split(":")[1]));
    }

    private long intervalStartTimestamp = 0;
    public void interval() {
        long intervalEndTimestamp = System.currentTimeMillis();

        if(intervalStartTimestamp == 0) {
            intervalStartTimestamp = System.currentTimeMillis();
            return;
        } else {
            Duration duration = new Duration(intervalStartTimestamp, intervalEndTimestamp);
            if(duration.getStandardMinutes() > intervalMinutes) {
                intervalStartTimestamp = System.currentTimeMillis();
                return;
            }
        }

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
            if(LocalTime.now().isBefore(from)) {
                nextExecution = from;
            } else if(LocalTime.now().isAfter(from) && LocalTime.now().isBefore(to)) {
                interval();
                nextExecution = from;
                while(LocalTime.now().isAfter(nextExecution)) {
                    nextExecution = nextExecution.plusMinutes(intervalMinutes);
                }
            } else {
                return from.toDateTimeToday().plusDays(1).toDate();
            }

            return nextExecution.toDateTimeToday().toDate();
        }

        nextExecution = LocalTime.fromDateFields(lastCompletionTime).plusMinutes(intervalMinutes);
        if(nextExecution.isAfter(to)) {
            if(LocalTime.now().isBefore(to)) {
                nextExecution = to;
            } else {
                return from.toDateTimeToday().plusDays(1).toDate();
            }
        }

        return nextExecution.toDateTimeToday().toDate();
    }
}
