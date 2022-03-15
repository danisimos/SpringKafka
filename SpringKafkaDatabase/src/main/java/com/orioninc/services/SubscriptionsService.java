package com.orioninc.services;

import com.orioninc.models.Subscription;

import java.util.*;

public interface SubscriptionsService {
    Subscription saveSubscription(Subscription subscription);
    List<Subscription> getAll();
}
