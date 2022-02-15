package com.orioninc.services;

import com.orioninc.models.Interval;
import com.orioninc.models.ProcessedIntervalSubscriptions;
import com.orioninc.models.User;
import com.orioninc.models.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class IntervalSubscriptionsProcessingService {
    @Autowired
    SendService sendService;

    public void process(Map<User, List<Subscription>> usersEvents, long timestampFrom, long timestampTo) {
        Interval interval = new Interval(timestampFrom, timestampTo);
        int averageWeekNumber = (int) usersEvents
                .values()
                .stream()
                .flatMap(Collection::stream)
                .map(Subscription::getWeekNumber)
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0);

        ProcessedIntervalSubscriptions processedIntervalSubscriptions = ProcessedIntervalSubscriptions
                .builder()
                .averageWeekCount(averageWeekNumber)
                .interval(interval)
                .build();

        sendService.send(interval, processedIntervalSubscriptions);
    }
}
