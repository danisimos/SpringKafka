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
    private SubscriptionType subscriptionType;
    private int weekNumber;
    private long handledTimestamp;
}
