package com.orioninc.services.impl;

import com.orioninc.models.User;
import com.orioninc.repositories.UsersRepository;
import com.orioninc.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    UsersRepository usersRepository;

    @Override
    public User saveUser(User user) {
        return usersRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return usersRepository.findAll();
    }
}
