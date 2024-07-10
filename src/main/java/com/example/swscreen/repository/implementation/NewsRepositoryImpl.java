package com.example.swscreen.repository.implementation;

import com.example.swscreen.controller.MainController;
import com.example.swscreen.domain.News;
import com.example.swscreen.repository.NewsRepository;
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

import static com.example.swscreen.query.NewsQuery.*;

@Repository
@RequiredArgsConstructor
@Slf4j
public class NewsRepositoryImpl implements NewsRepository<News> {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    private final NamedParameterJdbcTemplate jdbc;
    private final JdbcTemplate jdbcTemplate;
    @Override
    public News createNews(News news) {
        Optional<Long> recordsCount = Optional.ofNullable(jdbcTemplate.queryForObject("SELECT COUNT(*) FROM news", Long.class));
        long count = recordsCount.orElse(0L);

        if (count >= 4) {
            Long oldestId = jdbcTemplate.queryForObject("SELECT id FROM news ORDER BY id ASC LIMIT 1", Long.class);
            jdbcTemplate.update("DELETE FROM news WHERE id = ?", oldestId);
        }
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("title", news.getTitle());
        parameters.addValue("description",news.getDescription());
        parameters.addValue("illustration", news.getIllustration());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(INSERT_NEWS_QUERY, parameters, keyHolder);
        long generatedId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        news.setId(generatedId);

        return news;
    }

    @Override
    public List<News> getAllNews() {
        try {
            return jdbcTemplate.query(SELECT_ALL_NEWS_QUERY,
                    new BeanPropertyRowMapper<>(News.class));
        } catch (DataAccessException e) {
            System.err.println("Error accessing data: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
