package com.example.swscreen.repository.implementation;

import com.example.swscreen.domain.BelowInfo;
import com.example.swscreen.repository.ImportantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
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

import static com.example.swscreen.query.ImportantQuery.INSERT_IMPORTANT_QUERY;
import static com.example.swscreen.query.ImportantQuery.SELECT_IMPORTANT_QUERY;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ImportantRepositoryImpl implements ImportantRepository<BelowInfo> {
    private final NamedParameterJdbcTemplate jdbc;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public BelowInfo createImportant(BelowInfo belowInfo) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("description", belowInfo.getDescription());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(INSERT_IMPORTANT_QUERY, parameters, keyHolder);
        long generatedId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        belowInfo.setId(generatedId);
        return belowInfo;
//        MapSqlParameterSource parameters = new MapSqlParameterSource();
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbc.update(INSERT_IMPORTANT_QUERY, parameters, keyHolder);
//        long generatedId = Objects.requireNonNull(keyHolder.getKey()).longValue();
//        parameters.addValue("description", belowInfo.getDescription());
//        belowInfo.setId(generatedId);
//        return belowInfo;
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
