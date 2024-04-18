package com.example.swscreen.query;

public class ImportantQuery {

    public static final String INSERT_IMPORTANT_QUERY = "INSERT INTO below_info (description) VALUES (:description)";
    public static final String SELECT_IMPORTANT_BY_ID_QUERY = "SELECT * FROM BelowInfo WHERE id = :id";
    public static final String SELECT_IMPORTANT_QUERY = "SELECT * FROM BelowInfo";
    public static final String DELETE_IMPORTANT_QUERY = "DELETE FROM BelowInfo WHERE id = :id";
}
