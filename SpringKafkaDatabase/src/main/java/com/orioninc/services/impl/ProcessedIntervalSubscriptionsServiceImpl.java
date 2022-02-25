package com.orioninc.services.impl;

import com.orioninc.models.ProcessedIntervalSubscriptions;
import com.orioninc.repositories.ProcessedIntervalSubscriptionsRepository;
import com.orioninc.repositories.UsersRepository;
import com.orioninc.services.ProcessedIntervalSubscriptionsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessedIntervalSubscriptionsServiceImpl implements ProcessedIntervalSubscriptionsService {
    ProcessedIntervalSubscriptionsRepository processedIntervalDataRepository;
    UsersRepository usersRepository;

    public ProcessedIntervalSubscriptionsServiceImpl(ProcessedIntervalSubscriptionsRepository processedIntervalDataRepository, UsersRepository usersRepository) {
        this.processedIntervalDataRepository = processedIntervalDataRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public ProcessedIntervalSubscriptions saveProcessedIntervalSubscriptions(ProcessedIntervalSubscriptions data) {
        usersRepository.save(data.getUser());

        return processedIntervalDataRepository.save(data);
    }

    @Override
    public List<ProcessedIntervalSubscriptions> getAllProcessedIntervalsSubscriptions() {
        return processedIntervalDataRepository.findAll();
    }
}
