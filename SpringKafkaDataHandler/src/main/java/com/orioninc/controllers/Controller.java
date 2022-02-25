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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@Api("default Controller")
public class Controller {
    private final SendService sendService;
    private final MetricsCountService metricsCountService;
    private final ValidationService validationService;

    ObjectMapper mapper = new ObjectMapper();

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("process user's subscription event")
    public String index(@RequestBody Subscription subscription) {
        validationService.validate(subscription);

        return sendService.sendToSubscriptionsTopic(subscription) + ", count: " + metricsCountService.getCount();
    }

    @ExceptionHandler({ValidationException.class, HttpMediaTypeNotSupportedException.class})
    public String validationException(Exception exception) {
        return exception.getMessage();
    }
}
