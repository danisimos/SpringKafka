package com.orioninc.repositories;

import com.orioninc.models.ProcessedIntervalSubscriptions;
import com.orioninc.models.User;

import java.util.List;

public interface ProcessedIntervalSubscriptionsRepository extends CrudRepository<ProcessedIntervalSubscriptions, Integer> {
    List<ProcessedIntervalSubscriptions> findByAverageWeekCount(int averageWeekCount);
    List<ProcessedIntervalSubscriptions> findByInterval(String from, String to);
    List<ProcessedIntervalSubscriptions> findByUser(User user);
    List<ProcessedIntervalSubscriptions> findByUserId(Integer id);
}
