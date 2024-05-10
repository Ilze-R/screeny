package com.example.swscreen.repository.implementation;

import com.example.swscreen.controller.MainController;
import com.example.swscreen.domain.MidInfo;
import com.example.swscreen.repository.MainInfoRepository;
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

import static com.example.swscreen.query.MainInformationQuery.*;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MainInfoRepositoryImpl implements MainInfoRepository<MidInfo> {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    private final NamedParameterJdbcTemplate jdbc;
    private final JdbcTemplate jdbcTemplate;
    @Override
    public MidInfo createMainInfo(MidInfo midInfo) {
        Optional<Long> recordsCount = Optional.ofNullable(jdbcTemplate.queryForObject("SELECT COUNT(*) FROM mid_info", Long.class));
        long count = recordsCount.orElse(0L);

        if (count >= 4) {
            Long oldestId = jdbcTemplate.queryForObject("SELECT id FROM mid_info ORDER BY id ASC LIMIT 1", Long.class);
            jdbcTemplate.update("DELETE FROM mid_info WHERE id = ?", oldestId);
        }
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("title", midInfo.getTitle());
        parameters.addValue("created_at", midInfo.getCreated_at());
        parameters.addValue("description", midInfo.getDescription());
        parameters.addValue("illustration", midInfo.getIllustration());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(INSERT_MAIN_QUERY, parameters, keyHolder);
        long generatedId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        midInfo.setId(generatedId);

        return midInfo;
    }

    @Override
    public List<MidInfo> getAllMainInfo() {
        try {
            return jdbcTemplate.query(SELECT_ALL_MAIN_QUERY,
                    new BeanPropertyRowMapper<>(MidInfo.class));
        } catch (DataAccessException e) {
            System.err.println("Error accessing data: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
