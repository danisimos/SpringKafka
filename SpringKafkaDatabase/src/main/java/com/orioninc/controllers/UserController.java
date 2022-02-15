package com.orioninc.controllers;

import com.orioninc.models.ProcessedIntervalSubscriptions;
import com.orioninc.services.ProcessedIntervalSubscriptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    ProcessedIntervalSubscriptionsService processedIntervalSubscriptionsService;

    @GetMapping("/")
    public List<ProcessedIntervalSubscriptions> getUsers() {
        return processedIntervalSubscriptionsService.getAllProcessedIntervalsSubscriptions();
    }
}
