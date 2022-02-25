package com.orioninc.services.impl;

import com.orioninc.models.Subscription;
import com.orioninc.repositories.SubscriptionsRepository;
import com.orioninc.repositories.UsersRepository;
import com.orioninc.services.SubscriptionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SubscriptionsServiceImpl implements SubscriptionsService {
    private final SubscriptionsRepository subscriptionsRepository;
    private final UsersRepository usersRepository;

    @Override
    public Subscription saveSubscription(Subscription subscription) {
        usersRepository.save(subscription.getUser());

        return subscriptionsRepository.save(subscription);
    }
}
