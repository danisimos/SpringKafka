package com.orioninc.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, I>{
    List<T> findAll();
    T save(T entity);
    Optional<T> findById(I id);
    void update(I id, T entity);
    void delete(I id);
}

