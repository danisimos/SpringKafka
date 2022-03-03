package com.orioninc.repositories.impl;

import com.orioninc.models.Interval;
import com.orioninc.models.ProcessedIntervalSubscriptions;
import com.orioninc.models.User;
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
    private static final String SQL_SELECT_BY_INTERVAL = "select p.timestamp_from as \"timestamp_from\", p.timestamp_to as \"timestamp_to\"," +
            "u.id as \"user_id\", u.first_name as \"first_name\", u.last_name as \"last_name\"," +
            "u.age as \"age\", p.average_week_count as \"average_week_count\" " +
            "from processed_interval_subscriptions p left join users u on u.id = p.user_id " +
            "where cast(p.timestamp_from as text) >= cast(':from' as text) and cast(':to' as text) <= cast(p.timestamp_to as text)";
    private static final String SQL_SELECT_LIKE_USER = "select p.timestamp_from as \"timestamp_from\", p.timestamp_to as \"timestamp_to\"," +
            "u.id as \"user_id\", u.first_name as \"first_name\", u.last_name as \"last_name\"," +
            "u.age as \"age\", p.average_week_count as \"average_week_count\" " +
            "from processed_interval_subscriptions p left join users u on u.id = p.user_id " +
            "where cast(u.id as text) like ':id' or (u.\"first_name\" like ':firstName' " +
            "and u.\"last_name\" like ':lastName' and cast(u.age as text) like ':age')";

    private final RowMapper<ProcessedIntervalSubscriptions> processedIntervalSubscriptionsRowMapperRowMapper = (row, rowNumber) -> ProcessedIntervalSubscriptions.builder()
            .interval(Interval.builder()
                    .timestampFromg(row.getTimestamp("timestamp_from"))
                    .timestampTo(row.getTimestamp("timestamp_to"))
                    .build())
            .user(User.builder()
                    .firstName(row.getString("first_name"))
                    .lastName(row.getString("last_name"))
                    .id(row.getInt("user_id"))
                    .age(row.getInt("age"))
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

        values.put("from", subscriptions.getInterval().getTimestampFromg());
        values.put("to", subscriptions.getInterval().getTimestampTo());
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

    @Override
    public List<ProcessedIntervalSubscriptions> findByInterval(String from, String to) {
        Map<String, Object> values = new HashMap<>();
        System.out.println(from);

        values.put("from", from);
        values.put("to", to);

        SqlParameterSource parameterSource = new MapSqlParameterSource(values);

        return jdbcTemplate.query(SQL_SELECT_BY_INTERVAL, parameterSource, processedIntervalSubscriptionsRowMapperRowMapper);
    }

    @Override
    public List<ProcessedIntervalSubscriptions> findByUser(User user) {
        Map<String, Object> values = new HashMap<>();
        //System.out.println(user.getId().toString());
        values.put("id", user.getId() == null ? "%": user.getId().toString());
        values.put("firstName", user.getFirstName() == null ? "%": "%" + user.getFirstName() + "%");
        values.put("lastName", user.getLastName() == null ? "%": "%" + user.getLastName() + "%");
        values.put("age", user.getAge() == 0 ? "%": Integer.toString(user.getAge()));

        SqlParameterSource parameterSource = new MapSqlParameterSource(values);

        return jdbcTemplate.query(SQL_SELECT_LIKE_USER, parameterSource, processedIntervalSubscriptionsRowMapperRowMapper);
    }
}
