package com.orioninc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orioninc.models.Subscription;
import com.orioninc.services.MetricsCountService;
import com.orioninc.services.SendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@Api("default Controller")
public class Controller {
    private final SendService sendService;
    private final MetricsCountService metricsCountService;

    ObjectMapper mapper = new ObjectMapper();

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("process user's subscription event")
    public String index(@RequestBody Subscription subscription) {
        //Subscription subscription = mapper.convertValue(objectNode.get("subscription"), Subscription.class);

        if(subscription == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User or Subscription not found");
        }

        return sendService.sendToSubscriptionsTopic(subscription) + ", count: " + metricsCountService.getCount();
    }
}
