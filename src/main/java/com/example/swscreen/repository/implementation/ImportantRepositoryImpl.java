package com.example.swscreen.repository.implementation;

import com.example.swscreen.controller.MainController;
import com.example.swscreen.domain.FavouriteImportant;
import com.example.swscreen.domain.Important;
import com.example.swscreen.exception.ApiException;
import com.example.swscreen.repository.ImportantRepository;
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

import java.util.*;

import static com.example.swscreen.query.ImportantQuery.*;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ImportantRepositoryImpl implements ImportantRepository<Important> {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    private final NamedParameterJdbcTemplate jdbc;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Important createImportant(Important important) {
//        Optional<Long> recordsCount = Optional.ofNullable(jdbcTemplate.queryForObject("SELECT COUNT(*) FROM important", Long.class));
//        long count = recordsCount.orElse(0L);
//        Optional<Long> recordsCurrentCount = Optional.ofNullable(jdbcTemplate.queryForObject("SELECT COUNT(*) FROM current_important", Long.class));
//        long currentCount = recordsCurrentCount.orElse(0L);

//        if (count >= 4) {
//            Long oldestId = jdbcTemplate.queryForObject("SELECT id FROM important ORDER BY id ASC LIMIT 1", Long.class);
//            jdbcTemplate.update("DELETE FROM important WHERE id = ?", oldestId);
//        }
//        if (currentCount >= 1) {
//            Long oldestId = jdbcTemplate.queryForObject("SELECT id FROM current_important ORDER BY id ASC LIMIT 1", Long.class);
//            jdbcTemplate.update("DELETE FROM current_important WHERE id = ?", oldestId);
//            logger.debug("Deleted oldest record with ID: {}", oldestId);
//        }
        String updateQuery = "UPDATE important SET active = FALSE WHERE active = TRUE";
        jdbc.update(updateQuery, new MapSqlParameterSource());
        logger.debug("Insert in database: {}", important);
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("description", important.getDescription());
        parameters.addValue("favourite", false);
        parameters.addValue("active", true);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(INSERT_IMPORTANT_QUERY, parameters, keyHolder);
//        jdbc.update(INSERT_CURRENT_IMPORTANT_QUERY, parameters, keyHolder);
        long generatedId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        important.setId(generatedId);
        logger.debug("Inserted, retrieved ID: {}", generatedId);

        return important;
    }

    @Override
    public FavouriteImportant saveFavouriteImportant(FavouriteImportant favouriteImportant, Long id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("important_id", id);
        parameters.addValue("description", favouriteImportant.getDescription());
        parameters.addValue("favourite", true);
        parameters.addValue("active", favouriteImportant.isActive());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(INSERT_SAVED_IMPORTANT_QUERY, parameters, keyHolder);
        jdbcTemplate.update(UPDATE_IMPORTANT_FAVOURITE_QUERY, true, id);
//        String deleteQuery = "DELETE FROM important WHERE id = ?";
//        jdbc.getJdbcTemplate().update(deleteQuery, id);
        long generatedId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        favouriteImportant.setId(generatedId);

        return favouriteImportant;
    }

    @Override
    public void deleteImportant(Long id) {
        try {
            String deleteQuery = "DELETE FROM important WHERE id = ?";
            int rowsAffected = jdbc.getJdbcTemplate().update(deleteQuery, id);
            if (rowsAffected == 0) {
                throw new IllegalArgumentException("No important found by id: " + id);
            }
        } catch (EmptyResultDataAccessException exception) {
            throw new IllegalArgumentException("No important found by id: " + id);
        }
    }

    @Override
    public void deleteFavouriteImportant(Long id) {
        try {
            String deleteQuery = "DELETE FROM favourite_important WHERE important_id = ?";
            String updateQuery = "UPDATE important SET favourite = FALSE WHERE id = ?";

            jdbc.getJdbcTemplate().update(deleteQuery, id);
            jdbc.getJdbcTemplate().update(updateQuery, id);
        } catch (EmptyResultDataAccessException exception) {
            throw new IllegalArgumentException("No important found by id: " + id);
        }
    }

    @Override
    public List<Important> getAllImportant() {
        try {
            return jdbcTemplate.query(SELECT_IMPORTANT_QUERY,
                    new BeanPropertyRowMapper<>(Important.class));
        } catch (DataAccessException e) {
            System.err.println("Error accessing data: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<FavouriteImportant> getAllFavouriteImportant() {
        try {
            return jdbcTemplate.query(SELECT_FAVOURITES_FROM_IMPORTANT_QUERY,
                    new BeanPropertyRowMapper<>(FavouriteImportant.class));
        } catch (DataAccessException e) {
            System.err.println("Error accessing data: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void updateImportant(Long id, String description) {
        try {
          jdbcTemplate.update(UPDATE_IMPORTANT_QUERY, description, id);
        } catch (EmptyResultDataAccessException exception) {
            throw new ApiException("No information found by id: " + id);
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new ApiException("An error occurred. Please try again.");
        }
    }

    @Override
    public void deactivateImportant(Long id) {
        try {
            String updateQuery = "UPDATE important SET active = FALSE WHERE id = ?";

            jdbc.getJdbcTemplate().update(updateQuery, id);
        } catch (EmptyResultDataAccessException exception) {
            throw new IllegalArgumentException("No important found by id: " + id);
        }
    }

    @Override
    public void activateImportant(Long id) {
        try {
            String updateQuery = "UPDATE important SET active = TRUE WHERE id = ?";

            jdbc.getJdbcTemplate().update(updateQuery, id);
        } catch (EmptyResultDataAccessException exception) {
            throw new IllegalArgumentException("No important found by id: " + id);
        }
    }


}
