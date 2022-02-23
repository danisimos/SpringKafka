package com.orioninc.repositories.impl;

import com.orioninc.models.Interval;
import com.orioninc.models.ProcessedIntervalSubscriptions;
import com.orioninc.repositories.ProcessedIntervalSubscriptionsRepository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ProcessedIntervalSubscriptionsRepositoryJdbcImpl implements ProcessedIntervalSubscriptionsRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    private static final String SQL_INSERT_INTO = "insert into processed_interval_subscriptions(timestamp_from, timestamp_to, average_week_count, user_id) values (:from, :to, :averageWeekCount, :userId)";
    private static final String SQL_SELECT_ALL = "select * from processed_interval_subscriptions";

    private final RowMapper<ProcessedIntervalSubscriptions> processedIntervalSubscriptionsRowMapperRowMapper = (row, rowNumber) -> ProcessedIntervalSubscriptions.builder()
            .interval(Interval.builder()
                    .timestampFrom(row.getLong("from"))
                    .timestampTo(row.getLong("to"))
                    .build())
            .averageWeekCount(row.getInt("average_week_count"))
            .build();

    public ProcessedIntervalSubscriptionsRepositoryJdbcImpl(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<ProcessedIntervalSubscriptions> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, processedIntervalSubscriptionsRowMapperRowMapper);
    }

    @Override
    public ProcessedIntervalSubscriptions save(ProcessedIntervalSubscriptions subscriptions) {
        Map<String, Object> values = new HashMap<>();
        Timestamp from = new Timestamp(subscriptions.getInterval().getTimestampFrom());
        Timestamp to = new Timestamp(subscriptions.getInterval().getTimestampTo());

        values.put("from", from);
        values.put("to", to);
        values.put("averageWeekCount", subscriptions.getAverageWeekCount());
        values.put("userId", subscriptions.getUser().getId());

        SqlParameterSource parameterSource = new MapSqlParameterSource(values);

        jdbcTemplate.update(SQL_INSERT_INTO, parameterSource);

        return subscriptions;
    }

    @Override
    public Optional<ProcessedIntervalSubscriptions> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void update(Integer id, ProcessedIntervalSubscriptions subscriptions) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<ProcessedIntervalSubscriptions> findByAverageWeekCount(int averageWeekCount) {
        return null;
    }
}
