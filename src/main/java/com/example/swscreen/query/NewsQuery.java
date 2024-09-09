package com.example.swscreen.query;

public class NewsQuery {
    public static final String INSERT_NEWS_QUERY = "INSERT INTO news (title, description, illustration, favourite, active) VALUES (:title, :description, :illustration, :favourite, :active)";
    public static final String SELECT_NEWS_QUERY = "SELECT * FROM news WHERE id = :id";
    public static final String DELETE_NEWS_QUERY = "DELETE FROM news WHERE id = :id";

    public static final String INSERT_FAVOURITE_NEWS_QUERY = "INSERT INTO favourite_news (news_id, title, description, illustration, favourite, active) VALUES (:news_id, :title, :description, :illustration, :favourite, :active)";

    public static final String UPDATE_NEWS_FAVOURITE_STATUS_QUERY = "UPDATE news SET favourite = ? WHERE id = ?";

    public static final String SELECT_ALL_NEWS_QUERY = "SELECT * FROM news";

    public static final String SELECT_NEWS_FROM_FAVOURITES_QUERY = "SELECT * FROM favourite_news";

   // public static final String UPDATE_NEWS_QUERY = "UPDATE news SET description = ? WHERE id = ?";

    public static final String UPDATE_NEWS_QUERY = "UPDATE news SET title = ?, description = ? WHERE id = ?";
}
