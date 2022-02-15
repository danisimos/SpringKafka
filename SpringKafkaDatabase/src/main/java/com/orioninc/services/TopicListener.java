package com.orioninc.services;

import com.orioninc.models.Interval;
import com.orioninc.models.ProcessedIntervalSubscriptions;

public interface TopicListener {
    void listenProcessedIntervalSubscriptions(ProcessedIntervalSubscriptions data, Interval interval, String topicName);
}
