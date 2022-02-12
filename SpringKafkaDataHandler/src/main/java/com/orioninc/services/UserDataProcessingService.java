package com.orioninc.services;

import com.orioninc.models.Interval;
import com.orioninc.models.ProcessedIntervalData;
import com.orioninc.models.User;
import com.orioninc.models.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Period;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserDataProcessingService {
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

        ProcessedIntervalData processedIntervalData = new ProcessedIntervalData(averageWeekNumber);

        sendService.send(interval, processedIntervalData);
        System.out.println(interval);
        System.out.println(processedIntervalData);
    }
}
