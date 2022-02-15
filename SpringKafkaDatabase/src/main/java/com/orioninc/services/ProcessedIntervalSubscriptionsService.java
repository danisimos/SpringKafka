package com.orioninc.services;

import com.orioninc.models.ProcessedIntervalSubscriptions;

import java.util.List;

public interface ProcessedIntervalSubscriptionsService {
    ProcessedIntervalSubscriptions saveProcessedIntervalSubscriptions(ProcessedIntervalSubscriptions data);
    List<ProcessedIntervalSubscriptions> getAllProcessedIntervalsSubscriptions();
}
