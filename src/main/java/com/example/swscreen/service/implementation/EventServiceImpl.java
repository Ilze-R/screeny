package com.example.swscreen.service.implementation;

import com.example.swscreen.domain.Events;
import com.example.swscreen.repository.EventRepository;
import com.example.swscreen.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}
