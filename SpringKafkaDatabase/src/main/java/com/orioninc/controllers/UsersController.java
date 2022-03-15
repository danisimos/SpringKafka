package com.orioninc.controllers;


import com.orioninc.api.UsersApi;
import com.orioninc.models.ProcessedIntervalSubscriptions;
import com.orioninc.models.User;
import com.orioninc.services.ProcessedIntervalSubscriptionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UsersController implements UsersApi {
    private final ProcessedIntervalSubscriptionsService processedIntervalSubscriptionsService;

    @Override
    public ResponseEntity<List<ProcessedIntervalSubscriptions>> getByUser(User user) {
        return new ResponseEntity<>(processedIntervalSubscriptionsService.getByUser(user), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProcessedIntervalSubscriptions>> usersIdGet(Integer id) {
        return new ResponseEntity<>(processedIntervalSubscriptionsService.getByUserId(id), HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<String> exception(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
