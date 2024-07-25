package com.example.swscreen.controller;

import com.example.swscreen.domain.Events;
import com.example.swscreen.domain.FavouriteEvents;
import com.example.swscreen.domain.HttpResponse;
import com.example.swscreen.service.EventService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.time.LocalTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
public class EventsController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    private final EventService eventService;

    @PostMapping(path = "/create/event", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpResponse> addEventInformation(@RequestBody Events events) {
        Events createdEvent = eventService.createEvent(events);
        HttpResponse responseBody = HttpResponse.builder()
                .timeStamp(now().toString())
                .data(of("event", events))
                .message("Event created")
                .status(OK)
                .statusCode(OK.value())
                .build();
        return new ResponseEntity<>(responseBody, OK);
    }

    @GetMapping(path = "/events", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpResponse> getAllEventInformation() {
        List<Events> eventInfo = eventService.getAllEvents();
        HttpResponse responseBody = HttpResponse.builder()
                .timeStamp(now().toString())
                .data(of("events", eventInfo))
                .message("Fetched all events")
                .status(OK)
                .statusCode(OK.value())
                .build();
        return new ResponseEntity<>(responseBody, OK);
    }

    @DeleteMapping("/delete/event/{id}")
    public ResponseEntity<HttpResponse> deleteEvent(@PathVariable("id") Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("deleted event with id: ", id))
                        .message("Event deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @PatchMapping("/update/event/{id}")
    public ResponseEntity<HttpResponse> updateEvent(@PathVariable("id") Long id, @RequestBody String title){
        eventService.updateEvent(id, title);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("Updated event with id: ", id))
                        .message("Event updated")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @PatchMapping("/deactivate/event/{id}")
    public ResponseEntity<HttpResponse> deactivateEvents(@PathVariable("id") Long id){
        eventService.deactivateEvent(id);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("Deactivated event with id: ", id))
                        .message("Event deactivated")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @PatchMapping("/activate/event/{id}")
    public ResponseEntity<HttpResponse> activateEvents(@PathVariable("id") Long id){
        eventService.activateEvent(id);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("Activated event with id: ", id))
                        .message("Event activated")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @PostMapping(path = "/favourite-save/event/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpResponse> saveEventAsFavourite(@RequestBody FavouriteEvents favouriteEvent, @PathVariable("id") Long id) {
        FavouriteEvents savedEvent = eventService.saveFavouriteEvent(favouriteEvent, id);
        HttpResponse responseBody = HttpResponse.builder()
                .timeStamp(now().toString())
                .data(of("saved event to favourites", favouriteEvent))
                .message("Saved event to favourites")
                .status(OK)
                .statusCode(OK.value())
                .build();
        return new ResponseEntity<>(responseBody, OK);
    }

    @GetMapping(path = "/favourite/events", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpResponse> getAllEventsFromFavourites() {
        List<FavouriteEvents> favouriteEvents = eventService.getAllFavouriteEvent();
        HttpResponse responseBody = HttpResponse.builder()
                .timeStamp(now().toString())
                .data(of( "favourites", favouriteEvents))
                .message("Fetched all events from favourites")
                .status(OK)
                .statusCode(OK.value())
                .build();
        return new ResponseEntity<>(responseBody, OK);
    }

    @DeleteMapping("/delete/favourite/event/{id}")
    public ResponseEntity<HttpResponse> deleteEventFromFavourites(@PathVariable("id") Long id) {
        eventService.deleteFavouriteEvent(id);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("Deleted favourite event with id: ", id))
                        .message("Favourite event deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

}
