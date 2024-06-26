package com.example.swscreen.query;

public class EventsQuery {

    public static final String INSERT_EVENT_QUERY = "INSERT INTO events (title, created_at, description) VALUES (:title, :created_at, :description)";

    public static final String SELECT_ALL_EVENTS_QUERY = "SELECT * FROM events";
    public static final String SELECT_EVENT_QUERY = "SELECT * FROM Events WHERE id = :id";
    public static final String DELETE_EVENT_QUERY = "DELETE FROM Events WHERE id = :id";


}
