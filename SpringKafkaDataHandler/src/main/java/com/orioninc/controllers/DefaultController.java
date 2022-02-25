package com.orioninc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orioninc.exceptions.ValidationException;
import com.orioninc.models.Subscription;
import com.orioninc.services.MetricsCountService;
import com.orioninc.services.SendService;
import com.orioninc.services.ValidationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api("default Controller")
public class DefaultController {
    private final SendService sendService;
    private final MetricsCountService metricsCountService;
    private final ValidationService validationService;

    ObjectMapper mapper = new ObjectMapper();

    @PostMapping(value = "/")
    @ApiOperation(value = "process user's subscription event", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String index(@RequestBody(required = false) Subscription subscription) {
        validationService.validate(subscription);

        return sendService.sendToSubscriptionsTopic(subscription) + ", count: " + metricsCountService.getCount();
    }

    @ExceptionHandler(ValidationException.class)
    public String validationException(ValidationException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String notReadableException(HttpMessageNotReadableException exception) {
        System.out.println(exception.getMessage());
        return "Json parsing error";
    }
}
