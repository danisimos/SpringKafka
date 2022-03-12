package com.orioninc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping("subscriptions")
    public String getSubscriptionsPage() {
        return "subscriptions";
    }

    @GetMapping("intervals")
    public String getIntervalsPage() {
        return "intervals";
    }

    @GetMapping("users")
    public String getUsersPage() {
        return "users";
    }
}
