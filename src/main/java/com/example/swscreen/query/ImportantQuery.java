package com.example.swscreen.query;

public class ImportantQuery {

    public static final String INSERT_IMPORTANT_QUERY = "INSERT INTO below_info (description, favourite) VALUES (:description, :favourite)";
    public static final String INSERT_CURRENT_IMPORTANT_QUERY = "INSERT INTO current_below_info (description) VALUES (:description)";
    public static final String INSERT_SAVED_IMPORTANT_QUERY = "INSERT INTO saved_below_info (description) VALUES (:description)";
    public static final String SELECT_IMPORTANT_BY_ID_QUERY = "SELECT * FROM below_info WHERE id = :id";
    public static final String SELECT_IMPORTANT_QUERY = "SELECT * FROM below_info";
    public static final String SELECT_CURRENT_IMPORTANT_QUERY = "SELECT * FROM current_below_info";

    public static final String SELECT_SAVED_IMPORTANT_QUERY = "SELECT * FROM saved_below_info";
    public static final String DELETE_IMPORTANT_QUERY = "DELETE FROM below_info WHERE id = :id";
    public static final String OLDEST_RECORD = "SELECT id FROM below_info ORDER BY id ASC LIMIT 1";
    public static final String UPDATE_IMPORTANT_QUERY = "UPDATE below_info SET description = ? WHERE id = ?";
    public static final String UPDATE_IMPORTANT_FAVOURITE_QUERY = "UPDATE below_info SET favourite = ? WHERE id = ?";
}
