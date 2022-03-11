package com.orioninc.controllers;

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

import java.text.ParseException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class IntervalsController {
    private final ProcessedIntervalSubscriptionsService processedIntervalSubscriptionsService;
    private final PsqlTimestampDateFormatter psqlTimestampDateFormatter;

    @GetMapping(value = "/intervals")
    public ResponseEntity<List<ProcessedIntervalSubscriptions>> getIntervals(@RequestParam("from") String from,
                                                                      @RequestParam("to") String to) throws ParseException {
        return new ResponseEntity<>(processedIntervalSubscriptionsService
                .getByInterval(psqlTimestampDateFormatter.formatFrom(from), psqlTimestampDateFormatter.formatTo(to)), HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<String> exception(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
