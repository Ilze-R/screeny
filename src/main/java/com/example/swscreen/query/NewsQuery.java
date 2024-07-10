package com.example.swscreen.query;

public class NewsQuery {
    public static final String INSERT_NEWS_QUERY = "INSERT INTO news (title, description, illustration) VALUES (:title, :description, :illustration)";
    public static final String SELECT_NEWS_QUERY = "SELECT * FROM news WHERE id = :id";
    public static final String DELETE_NEWS_QUERY = "DELETE FROM news WHERE id = :id";

    public static final String SELECT_ALL_NEWS_QUERY = "SELECT * FROM news";
}
