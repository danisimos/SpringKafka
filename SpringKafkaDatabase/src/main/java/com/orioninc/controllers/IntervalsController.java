package com.orioninc.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orioninc.models.ProcessedIntervalSubscriptions;
import com.orioninc.models.Subscription;
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

    @GetMapping(value = "/")
    public ResponseEntity<String> index(@RequestParam("from") String from, @RequestParam("to") String to) throws JsonProcessingException, ParseException {
        //String from = psqlTimestampDateFormatter.format(from);
        //String to = psqlTimestampDateFormatter.format(to);


        return new ResponseEntity<>(from + to, HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<String> exception(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
