package com.example.swscreen.service.implementation;

import com.example.swscreen.domain.Events;
import com.example.swscreen.domain.FavouriteEvents;
import com.example.swscreen.repository.EventRepository;
import com.example.swscreen.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository<Events> eventRepository;
    @Override
    public Events createEvent(Events events) {
        return eventRepository.createEvent(events);
    }

    @Override
    public List<Events> getAllEvents() {
        return eventRepository.getAllEvents();
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteEvent(id);
    }

    @Override
    public void updateEvent(Long id, String title, Date date, LocalTime time) {
eventRepository.updateEvent(id, title, date, time);
    }

    @Override
    public void deactivateEvent(Long id) {
eventRepository.deactivateEvent(id);
    }

    @Override
    public void activateEvent(Long id) {
eventRepository.activateEvent(id);
    }

    @Override
    public FavouriteEvents saveFavouriteEvent(FavouriteEvents favouriteEvents, Long id) {
        return eventRepository.saveFavouriteEvent(favouriteEvents, id);
    }

    @Override
    public void deleteFavouriteEvent(Long id) {
eventRepository.deleteFavouriteEvent(id);
    }

    @Override
    public List<FavouriteEvents> getAllFavouriteEvent() {
        return eventRepository.getAllFavouriteEvents();
    }

}
