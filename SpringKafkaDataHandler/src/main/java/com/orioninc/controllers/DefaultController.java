package com.orioninc.controllers;

import com.orioninc.api.AsdeApi;
import com.orioninc.api.SubscriptionsApi;
import com.orioninc.exceptions.ValidationException;
import com.orioninc.models.Subscription;
import com.orioninc.services.ValidationService;
import com.orioninc.services.impl.MetricsCountService;
import com.orioninc.services.impl.SendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class DefaultController implements SubscriptionsApi, AsdeApi {
    private final SendService sendService;
    private final MetricsCountService metricsCountService;
    private final ValidationService validationService;

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> validationException(ValidationException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> notReadableException(HttpMessageNotReadableException exception) {
        return new ResponseEntity<>("Json parsing error", HttpStatus.BAD_REQUEST);
    }

    /*@Override
    public ResponseEntity<String> subscriptionsPost(Subscription subscription) {
        //validationService.validate(subscription);

        //sendService.sendToSubscriptionsTopic(subscription);
        //return new ResponseEntity<>("Successful", HttpStatus.OK);
        return null;
    }*/

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return null;
    }

    @Override
    public ResponseEntity<String> subscriptpppppionsPost(Subscription body) {
        return null;
    }

    @Override
    public ResponseEntity<Void> asdeeePost(String body) {
        return null;
    }
}
