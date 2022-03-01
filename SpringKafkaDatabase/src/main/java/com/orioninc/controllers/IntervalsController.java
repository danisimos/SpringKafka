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
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class IntervalsController {
    private final ProcessedIntervalSubscriptionsService processedIntervalSubscriptionsService;
    private final ObjectMapper objectMapper;
    private final PsqlTimestampDateFormatter psqlTimestampDateFormatter;

    @GetMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProcessedIntervalSubscriptions>> index(@RequestBody(required = false) String json) throws JsonProcessingException, ParseException {
        JsonNode jsonNode = objectMapper.readTree(json);
        String from = psqlTimestampDateFormatter.format(jsonNode.get("from").asText());
        String to = psqlTimestampDateFormatter.format(jsonNode.get("to").asText());

        return new ResponseEntity<List<ProcessedIntervalSubscriptions>>(processedIntervalSubscriptionsService.getByInterval(from, to), HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<String> exception(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
