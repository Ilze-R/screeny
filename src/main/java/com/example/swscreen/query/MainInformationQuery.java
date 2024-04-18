package com.example.swscreen.query;

public class MainInformationQuery {
    public static final String INSERT_MAIN_QUERY = "INSERT INTO MidInfo (title, created_at, description, illustration) VALUES (:title, :created_at, :description, :illustration)";
    public static final String SELECT_MAIN_QUERY = "SELECT * FROM MidInfo WHERE id = :id";
    public static final String DELETE_MAIN_QUERY = "DELETE FROM MidInfo WHERE id = :id";
}
