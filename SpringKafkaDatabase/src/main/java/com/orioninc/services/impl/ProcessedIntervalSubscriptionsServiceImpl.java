package com.orioninc.services.impl;

import com.orioninc.models.ProcessedIntervalSubscriptions;
import com.orioninc.repositories.ProcessedIntervalSubscriptionsRepository;
import com.orioninc.services.ProcessedIntervalSubscriptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessedIntervalSubscriptionsServiceImpl implements ProcessedIntervalSubscriptionsService {
    @Autowired
    ProcessedIntervalSubscriptionsRepository processedIntervalDataRepository;

    @Override
    public ProcessedIntervalSubscriptions saveProcessedIntervalSubscriptions(ProcessedIntervalSubscriptions data) {
        return processedIntervalDataRepository.save(data);
    }

    @Override
    public List<ProcessedIntervalSubscriptions> getAllProcessedIntervalsSubscriptions() {
        return processedIntervalDataRepository.findAll();
    }
}
