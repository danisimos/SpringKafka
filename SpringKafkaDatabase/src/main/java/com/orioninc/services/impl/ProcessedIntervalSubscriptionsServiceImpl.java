package com.orioninc.services.impl;

import com.orioninc.models.ProcessedIntervalSubscriptions;
import com.orioninc.models.User;
import com.orioninc.repositories.ProcessedIntervalSubscriptionsRepository;
import com.orioninc.repositories.UsersRepository;
import com.orioninc.services.ProcessedIntervalSubscriptionsService;
import com.orioninc.utils.PsqlTimestampDateFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcessedIntervalSubscriptionsServiceImpl implements ProcessedIntervalSubscriptionsService {
    private final ProcessedIntervalSubscriptionsRepository processedIntervalDataRepository;
    private final PsqlTimestampDateFormatter psqlTimestampDateFormatter;
    private final UsersRepository usersRepository;

    @Override
    public ProcessedIntervalSubscriptions saveProcessedIntervalSubscriptions(ProcessedIntervalSubscriptions data) {
        usersRepository.save(data.getUser());

        return processedIntervalDataRepository.save(data);
    }

    @Override
    public List<ProcessedIntervalSubscriptions> getAllProcessedIntervalsSubscriptions() {
        return processedIntervalDataRepository.findAll();
    }

    @Override
    public List<ProcessedIntervalSubscriptions> getByInterval(String from, String to)  {
        from = psqlTimestampDateFormatter.formatFrom(from);
        to = psqlTimestampDateFormatter.formatTo(to);
        return processedIntervalDataRepository.findByInterval(from, to);
    }

    @Override
    public List<ProcessedIntervalSubscriptions> getByUser(User user) {
        return processedIntervalDataRepository.findByUser(user);
    }

    @Override
    public List<ProcessedIntervalSubscriptions> getByUserId(Integer id) {
        return processedIntervalDataRepository.findByUserId(id);
    }
}
