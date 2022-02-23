package com.orioninc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.orioninc.models.Subscription;
import com.orioninc.models.User;
import com.orioninc.services.ListenService;
import com.orioninc.services.MetricsCountService;
import com.orioninc.services.SendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

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

        return sendService.send(subscription.getUser(), subscription) + ", count: " + metricsCountService.getCount();
    }
}
