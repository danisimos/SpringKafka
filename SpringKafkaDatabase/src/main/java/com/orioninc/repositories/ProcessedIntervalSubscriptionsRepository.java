package com.orioninc.repositories;

import com.orioninc.models.ProcessedIntervalSubscriptions;

import java.util.List;

public interface ProcessedIntervalSubscriptionsRepository extends CrudRepository<ProcessedIntervalSubscriptions, Integer> {
    List<ProcessedIntervalSubscriptions> findByFirstName(Integer id);
}
