package com.example.swscreen.query;

public class ImportantQuery {

    public static final String INSERT_IMPORTANT_QUERY = "INSERT INTO important (description, favourite, active) VALUES (:description, :favourite, :active)";
    public static final String INSERT_SAVED_IMPORTANT_QUERY = "INSERT INTO favourite_important (important_id, description, favourite, active) VALUES (:important_id, :description, :favourite, :active)";
    public static final String SELECT_IMPORTANT_BY_ID_QUERY = "SELECT * FROM important WHERE id = :id";
    public static final String SELECT_IMPORTANT_QUERY = "SELECT * FROM important";

    public static final String SELECT_FAVOURITES_FROM_IMPORTANT_QUERY = "SELECT * FROM favourite_important";
    public static final String DELETE_IMPORTANT_QUERY = "DELETE FROM important WHERE id = :id";
    public static final String OLDEST_RECORD = "SELECT id FROM important ORDER BY id ASC LIMIT 1";
    public static final String UPDATE_IMPORTANT_QUERY = "UPDATE important SET description = ? WHERE id = ?";
    public static final String UPDATE_IMPORTANT_FAVOURITE_QUERY = "UPDATE important SET favourite = ? WHERE id = ?";
}
