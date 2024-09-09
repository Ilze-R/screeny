package com.example.swscreen.repository.implementation;

import com.example.swscreen.controller.MainController;
import com.example.swscreen.domain.Events;
import com.example.swscreen.domain.FavouriteEvents;
import com.example.swscreen.exception.ApiException;
import com.example.swscreen.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.*;

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
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("title", events.getTitle());
        parameters.addValue("event_date", events.getEvent_date());
        parameters.addValue("time", events.getTime());
        parameters.addValue("illustration", events.getIllustration());
        parameters.addValue("favourite", false);
        parameters.addValue("active", true);
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

    @Override
    public void deleteEvent(Long id) {
        try {
            String deleteQuery = "DELETE FROM events WHERE id = ?";
            int rowsAffected = jdbc.getJdbcTemplate().update(deleteQuery, id);
            if (rowsAffected == 0) {
                throw new IllegalArgumentException("No event found by id: " + id);
            }
        } catch (EmptyResultDataAccessException exception) {
            throw new IllegalArgumentException("No event found by id: " + id);
        }
    }

    @Override
    public void updateEvent(Long id, String title, Date date, LocalTime time) {
        try {
            jdbcTemplate.update(UPDATE_EVENTS_QUERY, title, date, time, id);
        } catch (EmptyResultDataAccessException exception) {
            throw new ApiException("No event found by id: " + id);
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new ApiException("An error occurred. Please try again.");
        }
    }

    @Override
    public void deactivateEvent(Long id) {
        try {
            String updateQuery = "UPDATE events SET active = FALSE WHERE id = ?";

            jdbc.getJdbcTemplate().update(updateQuery, id);
        } catch (EmptyResultDataAccessException exception) {
            throw new IllegalArgumentException("No event found by id: " + id);
        }
    }

    @Override
    public void activateEvent(Long id) {
        try {
            String updateQuery = "UPDATE events SET active = TRUE WHERE id = ?";
            jdbc.getJdbcTemplate().update(updateQuery, id);
        } catch (EmptyResultDataAccessException exception) {
            throw new IllegalArgumentException("No event found by id: " + id);
        }
    }

    @Override
    public FavouriteEvents saveFavouriteEvent(FavouriteEvents favouriteEvents, Long id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("event_id", id);
        parameters.addValue("title", favouriteEvents.getTitle());
        parameters.addValue("event_date", favouriteEvents.getEvent_date());
        parameters.addValue("time", favouriteEvents.getTime());
        parameters.addValue("illustration", favouriteEvents.getIllustration());
        parameters.addValue("favourite", true);
        parameters.addValue("active", favouriteEvents.isActive());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(INSERT_FAVOURITE_EVENTS_QUERY, parameters, keyHolder);
        jdbcTemplate.update(UPDATE_EVENTS_FAVOURITE_STATUS_QUERY, true, id);
        long generatedId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        favouriteEvents.setId(generatedId);

        return favouriteEvents;
    }

    @Override
    public void deleteFavouriteEvent(Long id) {
        try {
            String deleteQuery = "DELETE FROM favourite_events WHERE event_id = ?";
            String updateQuery = "UPDATE events SET favourite = FALSE WHERE id = ?";

            jdbc.getJdbcTemplate().update(deleteQuery, id);
            jdbc.getJdbcTemplate().update(updateQuery, id);
        } catch (EmptyResultDataAccessException exception) {
            throw new IllegalArgumentException("No event found by id: " + id);
        }
    }

    @Override
    public List<FavouriteEvents> getAllFavouriteEvents() {
        try {
            return jdbcTemplate.query(SELECT_EVENTS_FROM_FAVOURITES_QUERY,
                    new BeanPropertyRowMapper<>(FavouriteEvents.class));
        } catch (DataAccessException e) {
            System.err.println("Error accessing data: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}