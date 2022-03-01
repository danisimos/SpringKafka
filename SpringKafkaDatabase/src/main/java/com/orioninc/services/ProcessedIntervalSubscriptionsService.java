package com.orioninc.services;

import com.orioninc.models.ProcessedIntervalSubscriptions;

import java.util.List;

public interface ProcessedIntervalSubscriptionsService {
    ProcessedIntervalSubscriptions saveProcessedIntervalSubscriptions(ProcessedIntervalSubscriptions data);
    List<ProcessedIntervalSubscriptions> getAllProcessedIntervalsSubscriptions();
    List<ProcessedIntervalSubscriptions> getByInterval(String from, String to);
}
