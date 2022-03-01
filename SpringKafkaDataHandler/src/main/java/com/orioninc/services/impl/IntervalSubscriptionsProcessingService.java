package com.orioninc.services.impl;

import com.orioninc.models.Interval;
import com.orioninc.models.ProcessedIntervalSubscriptions;
import com.orioninc.models.Subscription;
import com.orioninc.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class IntervalSubscriptionsProcessingService {
    @Autowired
    SendService sendService;

    public void process(Map<User, List<Subscription>> usersEvents, long timestampFrom, long timestampTo) {
        Interval interval = new Interval(timestampFrom, timestampTo);

        usersEvents
                .entrySet()
                .stream()
                .map(e -> Map.entry(e.getKey(), e.getValue()
                        .stream()
                        .map(Subscription::getWeekNumber)
                        .mapToInt(Integer::intValue)
                        .average()
                        .orElse(0)))
                .map(e -> ProcessedIntervalSubscriptions
                        .builder()
                        .user(e.getKey())
                        .interval(interval)
                        .averageWeekCount(e.getValue().intValue())
                        .build())
                .forEach(p -> sendService.sendToIntervalsTopic(interval, p));
    }
}
