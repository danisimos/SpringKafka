package com.orioninc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.orioninc.models.Subscription;
import com.orioninc.models.User;
import com.orioninc.services.ListenService;
import com.orioninc.services.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class Controller {
    @Autowired
    SendService sendService;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String index(@RequestBody ObjectNode objectNode) {
        ObjectMapper mapper = new ObjectMapper();
        if(objectNode.get("user") == null) {
            return "no user";
        }
        User user = mapper.convertValue(objectNode.get("user"), User.class);
        Subscription subscription = mapper.convertValue(objectNode.get("subscription"), Subscription.class);
        System.out.println(subscription);

        return sendService.send(user, subscription);
    }
}
