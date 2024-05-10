package com.example.swscreen.repository.implementation;

import com.example.swscreen.controller.MainController;
import com.example.swscreen.domain.Events;
import com.example.swscreen.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.example.swscreen.query.EventsQuery.*;

@Repository
@RequiredArgsConstructor
@Slf4j
public class EventRepositoryImpl  implements EventRepository<Events> {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    private final NamedParameterJdbcTemplate jdbc;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Events createEvent(Events events) {
        Optional<Long> recordsCount = Optional.ofNullable(jdbcTemplate.queryForObject("SELECT COUNT(*) FROM events", Long.class));
        long count = recordsCount.orElse(0L);

        if (count >= 4) {
            Long oldestId = jdbcTemplate.queryForObject("SELECT id FROM events ORDER BY id ASC LIMIT 1", Long.class);
            jdbcTemplate.update("DELETE FROM events WHERE id = ?", oldestId);
        }
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("title", events.getTitle());
        parameters.addValue("created_at", events.getCreated_at());
        parameters.addValue("description", events.getDescription());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(INSERT_EVENT_QUERY, parameters, keyHolder);
        long generatedId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        events.setId(generatedId);

        return events;
    }

    @Override
    public List<Events> getAllEvents() {
        try {
            return jdbcTemplate.query(SELECT_ALL_EVENTS_QUERY,
                    new BeanPropertyRowMapper<>(Events.class));
        } catch (DataAccessException e) {
            System.err.println("Error accessing data: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}