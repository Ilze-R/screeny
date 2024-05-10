package com.example.swscreen.query;

public class MainInformationQuery {
    public static final String INSERT_MAIN_QUERY = "INSERT INTO mid_info (title, created_at, description, illustration) VALUES (:title, :created_at, :description, :illustration)";
    public static final String SELECT_MAIN_QUERY = "SELECT * FROM mid_info WHERE id = :id";
    public static final String DELETE_MAIN_QUERY = "DELETE FROM mid_info WHERE id = :id";

    public static final String SELECT_ALL_MAIN_QUERY = "SELECT * FROM mid_info";
}
