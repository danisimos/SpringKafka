package com.orioninc.controllers;

import com.orioninc.models.User;
import com.orioninc.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UsersService usersService;

    @GetMapping("/")
    public List<User> getUsers() {
        return usersService.getUsers();
    }
}
