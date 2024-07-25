package com.example.swscreen.repository;
import com.example.swscreen.domain.Events;
import com.example.swscreen.domain.FavouriteEvents;

import java.util.List;

public interface EventRepository  <T extends Events>{

    T createEvent(Events events);

    List<Events> getAllEvents();

    void deleteEvent(Long id);

    void updateEvent(Long id, String title);

    void deactivateEvent(Long id);

    void activateEvent(Long id);

    FavouriteEvents saveFavouriteEvent(FavouriteEvents favouriteEvents, Long id);

    void deleteFavouriteEvent(Long id);

    List<FavouriteEvents> getAllFavouriteEvents();
}
