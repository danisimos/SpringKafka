package com.orioninc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orioninc.exceptions.ValidationException;
import com.orioninc.models.Subscription;
import com.orioninc.services.ValidationService;
import com.orioninc.services.impl.MetricsCountService;
import com.orioninc.services.impl.SendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
@Api("default Controller")
public class DefaultController {
    private final SendService sendService;
    private final MetricsCountService metricsCountService;
    private final ValidationService validationService;

    ObjectMapper mapper = new ObjectMapper();

    @GetMapping("subscriptions")
    public String getSubscriptionsPage() {
        return "subscriptions";
    }

    @GetMapping("intervals")
    public String getIntervalsPage() {
        return "intervals";
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> index(@RequestBody(required = false) Subscription subscription) {
        System.out.println(subscription);
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
