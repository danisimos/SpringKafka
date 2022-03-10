package com.orioninc.controllers;

import com.orioninc.models.ProcessedIntervalSubscriptions;
import com.orioninc.models.User;
import com.orioninc.services.ProcessedIntervalSubscriptionsService;
import com.orioninc.utils.PsqlTimestampDateFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UsersController {
    private final ProcessedIntervalSubscriptionsService processedIntervalSubscriptionsService;

    @GetMapping(value = "/users")
    public ResponseEntity<List<ProcessedIntervalSubscriptions>> getByUser(User user) {
        return new ResponseEntity<>(processedIntervalSubscriptionsService.getByUser(user), HttpStatus.OK);
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<List<ProcessedIntervalSubscriptions>> getByUserId(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(processedIntervalSubscriptionsService.getByUserId(id), HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<String> exception(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
