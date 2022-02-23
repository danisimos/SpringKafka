package com.orioninc.models;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel
public class Subscription {
    private User user;
    private SubscriptionType subscriptionType;
    private int weekNumber;
}
