package com.orioninc.repositories;

import com.orioninc.models.User;

import java.util.List;

public interface UsersRepository extends CrudRepository<User, Integer>{
    List<User> findByFirstName(String firstName);
}
