package com.orioninc.repositories;

import com.orioninc.models.Interval;
import com.orioninc.models.Subscription;
import com.orioninc.models.SubscriptionType;

import java.util.*;

public interface SubscriptionsRepository extends CrudRepository<Subscription, Integer>{
    List<Subscription> getSubscriptionsByType(SubscriptionType subscriptionType);
}
