package com.example.swscreen.repository.implementation;

import com.example.swscreen.controller.MainController;
import com.example.swscreen.domain.*;
import com.example.swscreen.exception.ApiException;
import com.example.swscreen.repository.NewsRepository;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
//        Optional<Long> recordsCount = Optional.ofNullable(jdbcTemplate.queryForObject("SELECT COUNT(*) FROM news", Long.class));
//        long count = recordsCount.orElse(0L);
//
//        if (count >= 4) {
//            Long oldestId = jdbcTemplate.queryForObject("SELECT id FROM news ORDER BY id ASC LIMIT 1", Long.class);
//            jdbcTemplate.update("DELETE FROM news WHERE id = ?", oldestId);
//        }
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("title", news.getTitle());
        parameters.addValue("description",news.getDescription());
        parameters.addValue("illustration", news.getIllustration());
        parameters.addValue("favourite", false);
        parameters.addValue("active", true);
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

    @Override
    public void deleteNews(Long id) {
        try {
            String deleteQuery = "DELETE FROM news WHERE id = ?";
            int rowsAffected = jdbc.getJdbcTemplate().update(deleteQuery, id);
            if (rowsAffected == 0) {
                throw new IllegalArgumentException("No news found by id: " + id);
            }
        } catch (EmptyResultDataAccessException exception) {
            throw new IllegalArgumentException("No news found by id: " + id);
        }
    }

    @Override
    public void updateNews(Long id, String description) {
        try {
            jdbcTemplate.update(UPDATE_NEWS_QUERY, description, id);
        } catch (EmptyResultDataAccessException exception) {
            throw new ApiException("No news found by id: " + id);
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new ApiException("An error occurred. Please try again.");
        }
    }

    @Override
    public void deactivateNews(Long id) {
        try {
            String updateQuery = "UPDATE news SET active = FALSE WHERE id = ?";

            jdbc.getJdbcTemplate().update(updateQuery, id);
        } catch (EmptyResultDataAccessException exception) {
            throw new IllegalArgumentException("No news found by id: " + id);
        }
    }

    @Override
    public void activateNews(Long id) {
        try {
            String updateQuery = "UPDATE news SET active = TRUE WHERE id = ?";
            jdbc.getJdbcTemplate().update(updateQuery, id);
        } catch (EmptyResultDataAccessException exception) {
            throw new IllegalArgumentException("No news found by id: " + id);
        }
    }

    @Override
    public FavouriteNews saveFavouriteNews(FavouriteNews favouriteNews, Long id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("news_id", id);
        parameters.addValue("title", favouriteNews.getTitle());
        parameters.addValue("description", favouriteNews.getDescription());
        parameters.addValue("illustration", favouriteNews.getIllustration());
        parameters.addValue("favourite", true);
        parameters.addValue("active", favouriteNews.isActive());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(INSERT_FAVOURITE_NEWS_QUERY, parameters, keyHolder);
        jdbcTemplate.update(UPDATE_NEWS_FAVOURITE_STATUS_QUERY, true, id);
        long generatedId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        favouriteNews.setId(generatedId);

        return favouriteNews;
    }



    @Override
    public void deleteFavouriteNews(Long id) {
        try {
            String deleteQuery = "DELETE FROM favourite_news WHERE news_id = ?";
            String updateQuery = "UPDATE news SET favourite = FALSE WHERE id = ?";

            jdbc.getJdbcTemplate().update(deleteQuery, id);
            jdbc.getJdbcTemplate().update(updateQuery, id);
        } catch (EmptyResultDataAccessException exception) {
            throw new IllegalArgumentException("No news found by id: " + id);
        }
    }

    @Override
    public List<FavouriteNews> getAllFavouriteNews() {
        try {
            return jdbcTemplate.query(SELECT_NEWS_FROM_FAVOURITES_QUERY,
                    new BeanPropertyRowMapper<>(FavouriteNews.class));
        } catch (DataAccessException e) {
            System.err.println("Error accessing data: " + e.getMessage());
            return new ArrayList<>();
        }
    }

//    @Override
//    public CurrentNews getCurrentNews() {
//        try {
//            return jdbcTemplate.queryForObject(SELECT_CURRENT_NEWS_QUERY,
//                    new BeanPropertyRowMapper<>(CurrentNews.class));
//        } catch (DataAccessException e) {
//            System.err.println("Error accessing data: " + e.getMessage());
//            return new CurrentNews();
//        }
//    }


}
