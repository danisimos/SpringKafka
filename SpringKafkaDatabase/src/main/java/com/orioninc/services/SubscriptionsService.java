package com.orioninc.services;

import com.orioninc.models.ProcessedIntervalSubscriptions;
import com.orioninc.models.Subscription;

public interface SubscriptionsService {
    Subscription saveSubscription(Subscription subscription);
}
