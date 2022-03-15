package com.orioninc.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subscription {
    private User user;
    private SubscriptionType subscriptionType;
    private int weekNumber;
    private long timestamp;
}
