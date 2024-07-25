package com.example.swscreen.query;

public class EventsQuery {

    public static final String INSERT_EVENT_QUERY = "INSERT INTO events (title, event_date, time, illustration, favourite, active) VALUES (:title, :event_date, :time, :illustration, :favourite, :active)";

    public static final String SELECT_ALL_EVENTS_QUERY = "SELECT * FROM events";
    public static final String SELECT_EVENT_QUERY = "SELECT * FROM events WHERE id = :id";
    public static final String DELETE_EVENT_QUERY = "DELETE FROM events WHERE id = :id";

    public static final String INSERT_FAVOURITE_EVENTS_QUERY = "INSERT INTO favourite_events (event_id, title, event_date, time, illustration, favourite, active) VALUES (:event_id, :title, :event_date, :time, :illustration, :favourite, :active)";

    public static final String UPDATE_EVENTS_FAVOURITE_STATUS_QUERY = "UPDATE events SET favourite = ? WHERE id = ?";

    public static final String SELECT_EVENTS_FROM_FAVOURITES_QUERY = "SELECT * FROM favourite_events";

    public static final String UPDATE_EVENTS_QUERY = "UPDATE events SET title = ? WHERE id = ?";

}
