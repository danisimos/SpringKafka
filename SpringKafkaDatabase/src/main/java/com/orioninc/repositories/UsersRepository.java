package com.orioninc.repositories;

import com.orioninc.models.ProcessedIntervalSubscriptions;
import com.orioninc.models.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.util.List;

public interface UsersRepository extends CrudRepository<User, Integer>{
    List<User> findByFirstName(String firstName);
}
