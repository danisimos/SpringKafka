package com.orioninc.controllers;

import com.orioninc.api.SubscriptionsApi;
import com.orioninc.models.Subscription;
import com.orioninc.services.SubscriptionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SubscriptionsController implements SubscriptionsApi {
    private final SubscriptionsService subscriptionsService;

    @Override
    public ResponseEntity<List<Subscription>> subscriptionsGetAllGet() {
        return new ResponseEntity<>(subscriptionsService.getAll(), HttpStatus.OK);
    }
}
