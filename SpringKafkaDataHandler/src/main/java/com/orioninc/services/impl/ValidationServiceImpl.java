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
            throw new ValidationException(ErrorEntityEnum.SUBSCRIPTION_NULL);
        } else if(subscription.getUser() == null) {
            throw new ValidationException(ErrorEntityEnum.USER_NULL);
        } else if(subscription.getUser().getFirstName() == null) {
            throw new ValidationException(ErrorEntityEnum.USER_FIRSTNAME_NULL);
        } else if(subscription.getUser().getLastName() == null) {
            throw new ValidationException(ErrorEntityEnum.USER_LASTNAME_NULL);
        } else if(subscription.getUser().getAge() < 18 ||subscription.getUser().getAge() > 100) {
            throw new ValidationException(ErrorEntityEnum.USER_AGE_INVALID);
        } else if(subscription.getUser().getFirstName().matches("\s+") || subscription.getUser().getFirstName().equals("")) {
            throw new ValidationException(ErrorEntityEnum.USER_FIRSTNAME_EMPTY);
        } else if(subscription.getUser().getLastName().matches("\s+") || subscription.getUser().getLastName().equals("")) {
            throw new ValidationException(ErrorEntityEnum.USER_LASTNAME_EMPTY);
        } else if(subscription.getSubscriptionType() == null) {
            throw new ValidationException(ErrorEntityEnum.SUBSCRIPTION_TYPE_NULL);
        } else if(subscription.getWeekNumber() <= 0 || subscription.getWeekNumber() > 50) {
            throw new ValidationException(ErrorEntityEnum.SUBSCRIPTION_WEEK_COUNT_INVALID);
        }
    }
}
