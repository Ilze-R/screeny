package com.example.swscreen.repository.implementation;

import com.example.swscreen.controller.MainController;
import com.example.swscreen.domain.BelowInfo;
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
public class ImportantRepositoryImpl implements ImportantRepository<BelowInfo> {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    private final NamedParameterJdbcTemplate jdbc;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public BelowInfo createImportant(BelowInfo belowInfo) {
        Optional<Long> optionalCount = Optional.ofNullable(jdbcTemplate.queryForObject("SELECT COUNT(*) FROM below_info", Long.class));
        long count = optionalCount.orElse(0L);

        if (count >= 4) {
            Long oldestId = jdbcTemplate.queryForObject("SELECT id FROM below_info ORDER BY id ASC LIMIT 1", Long.class);
            jdbcTemplate.update("DELETE FROM below_info WHERE id = ?", oldestId);
            logger.debug("Deleted oldest record with ID: {}", oldestId);
        }
        logger.debug("Insert in database: {}", belowInfo);
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("description", belowInfo.getDescription());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(INSERT_IMPORTANT_QUERY, parameters, keyHolder);
        long generatedId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        belowInfo.setId(generatedId);
        logger.debug("Inserted, retrieved ID: {}", generatedId);

        return belowInfo;
    }

    @Override
    public void deleteImportant(Long id) {
        try {
            String deleteQuery = "DELETE FROM swscreen.BelowInfo WHERE id = ?";
            int rowsAffected = jdbc.getJdbcTemplate().update(deleteQuery, id);
            if (rowsAffected == 0) {
                throw new IllegalArgumentException("No BelowInfo found by id: " + id);
            }
        } catch (EmptyResultDataAccessException exception) {
            throw new IllegalArgumentException("No BelowInfo found by id: " + id);
        }
    }

    @Override
    public List<BelowInfo> getAllImportant() {
        try {
            return jdbcTemplate.query(SELECT_IMPORTANT_QUERY,
                    new BeanPropertyRowMapper<>(BelowInfo.class));
        } catch (DataAccessException e) {
            System.err.println("Error accessing data: " + e.getMessage());
            return new ArrayList<>();
        }
    }


}
