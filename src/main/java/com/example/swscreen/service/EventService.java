package com.example.swscreen.service;

import com.example.swscreen.domain.BelowInfo;
import com.example.swscreen.domain.Events;

import java.util.List;

public interface EventService {

    Events createEvent(Events events);

    List<Events> getAllEvents();
}
