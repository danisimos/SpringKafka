package com.orioninc.repositories.impl;

import com.orioninc.models.Interval;
import com.orioninc.models.ProcessedIntervalSubscriptions;
import com.orioninc.models.User;
import com.orioninc.repositories.UsersRepository;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class UsersRepositoryJdbcImpl implements UsersRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    private static final String SQL_INSERT_INTO = "insert into users (first_name, last_name, age)" +
            "(select :firstName, :lastName, :age where not exists " +
            "(select from users where first_name = :firstName and last_name = :lastName and age = :age))";
    private static final String SQL_SELECT_ALL = "select * from users";
    private static final String SQL_SELECT_BY_FIRSTNAME_LASTNAME_AGE = "select id from users " +
            "where first_name = :firstName and last_name = :lastName and age = :age";

    private final RowMapper<User> userRowMapper = (row, rowNumber) -> User.builder()
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .age(row.getInt("age"))
            .id(row.getInt("id"))
            .build();

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public User save(User user) {
        Map<String, Object> values = new HashMap<>();
        values.put("firstName", user.getFirstName());
        values.put("lastName", user.getLastName());
        values.put("age", user.getAge());
        SqlParameterSource parameterSource = new MapSqlParameterSource(values);

        jdbcTemplate.update(SQL_INSERT_INTO, parameterSource);

        Integer id = jdbcTemplate.queryForObject(SQL_SELECT_BY_FIRSTNAME_LASTNAME_AGE, parameterSource, Integer.class);

        user.setId(id);

        return user;
    }

    @Override
    public void update(Integer id, User user) {

    }


    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<User> findByFirstName(String firstName) {
        return null;
    }
}
