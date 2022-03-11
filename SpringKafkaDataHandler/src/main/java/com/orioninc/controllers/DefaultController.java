package com.orioninc.controllers;

import com.orioninc.api.SubscriptionsApi;
import com.orioninc.exceptions.ValidationException;
import com.orioninc.models.Subscription;
import com.orioninc.services.ValidationService;
import com.orioninc.services.impl.SendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class DefaultController implements SubscriptionsApi {
    private final SendService sendService;
    private final ValidationService validationService;

    @Override
    public ResponseEntity<String> subscriptionsPost(Subscription subscription) {
        validationService.validate(subscription);

        sendService.sendToSubscriptionsTopic(subscription);
        return new ResponseEntity<>("Successful", HttpStatus.OK);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> validationException(ValidationException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> notReadableException(HttpMessageNotReadableException exception) {
        return new ResponseEntity<>("Json parsing error", HttpStatus.BAD_REQUEST);
    }
}
