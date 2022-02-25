package com.orioninc.services.impl;

import com.orioninc.exceptions.ErrorEntityEnum;
import com.orioninc.exceptions.ValidationException;
import com.orioninc.models.Subscription;
import com.orioninc.services.ValidationService;
import org.springframework.stereotype.Service;

@Service
public class ValidationServiceImpl implements ValidationService {
    @Override
    public void validate(Subscription subscription) {
        if(subscription == null) {
            throw new ValidationException(ErrorEntityEnum.NULL_SUBSCRIPTION);
        } else if(subscription.getUser() == null) {
            throw new ValidationException(ErrorEntityEnum.NULL_USER);
        }
    }
}
