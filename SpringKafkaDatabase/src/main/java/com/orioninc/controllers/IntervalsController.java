package com.orioninc.controllers;

import com.orioninc.api.IntervalsApi;
import com.orioninc.models.ProcessedIntervalSubscriptions;
import com.orioninc.services.ProcessedIntervalSubscriptionsService;
import com.orioninc.utils.PsqlTimestampDateFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class IntervalsController implements IntervalsApi {
    private final ProcessedIntervalSubscriptionsService processedIntervalSubscriptionsService;

    @Override
    public ResponseEntity<List<ProcessedIntervalSubscriptions>> intervalsFromToGet(String from, String to) {
        return new ResponseEntity<>(processedIntervalSubscriptionsService.getByInterval(from, to), HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<String> exception(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
