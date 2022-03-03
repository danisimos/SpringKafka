package com.orioninc.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orioninc.models.ProcessedIntervalSubscriptions;
import com.orioninc.models.Subscription;
import com.orioninc.models.User;
import com.orioninc.services.ProcessedIntervalSubscriptionsService;
import com.orioninc.utils.PsqlTimestampDateFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class IntervalsController {
    private final ProcessedIntervalSubscriptionsService processedIntervalSubscriptionsService;
    private final ObjectMapper objectMapper;
    private final PsqlTimestampDateFormatter psqlTimestampDateFormatter;

    @GetMapping(value = "/intervals")
    public ResponseEntity<List<ProcessedIntervalSubscriptions>> getIntervals(@RequestParam("from") String from,
                                                                      @RequestParam("to") String to) throws ParseException {
        return new ResponseEntity<>(processedIntervalSubscriptionsService
                .getByInterval(psqlTimestampDateFormatter.format(from), psqlTimestampDateFormatter.format(to)), HttpStatus.OK);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<ProcessedIntervalSubscriptions>> getByUser(User user) {
        System.out.println(user);
        return new ResponseEntity<>(processedIntervalSubscriptionsService.getByUser(user), HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<String> exception(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
