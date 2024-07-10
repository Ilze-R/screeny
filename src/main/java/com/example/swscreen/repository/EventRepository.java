package com.example.swscreen.repository;
import com.example.swscreen.domain.Events;

import java.util.List;

public interface EventRepository  <T extends Events>{

    T createEvent(Events events);

    List<Events> getAllEvents();
}
