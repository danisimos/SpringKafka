package com.orioninc.services;

import com.orioninc.models.User;

import java.util.List;

public interface UsersService {
    User saveUser(User user);
    List<User> getUsers();
}
