package com.example.swscreen.controller;

import com.example.swscreen.domain.*;
import com.example.swscreen.service.EventService;
import com.example.swscreen.service.ImportantService;
import com.example.swscreen.service.NewsService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.time.LocalTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    private final ImportantService importantService;
    private final EventService eventService;
    private final NewsService newsService;

    @PostMapping(path = "/create/important", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpResponse> addImportantInformation(@RequestBody BelowInfo belowInfo) {
        logger.info("Received request for important: {}", belowInfo);
   BelowInfo createdBelowInfo = importantService.createImportant(belowInfo);
        logger.info("Created new important: {}", createdBelowInfo);
        HttpResponse responseBody = HttpResponse.builder()
                .timeStamp(now().toString())
                .data(of("belowInfo", createdBelowInfo))
                .message("BelowInfo created")
                .status(OK)
                .statusCode(OK.value())
                .build();
        return new ResponseEntity<>(responseBody, OK);
    }

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

    @PostMapping(path = "/create/news", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpResponse> addNews(@RequestBody News news) {
        News createdNew = newsService.createNews(news);
        HttpResponse responseBody = HttpResponse.builder()
                .timeStamp(now().toString())
                .data(of("news", createdNew))
                .message("News topic created")
                .status(OK)
                .statusCode(OK.value())
                .build();
        return new ResponseEntity<>(responseBody, OK);
    }

    @PostMapping(path = "/save/important/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpResponse> saveImportantInformation(@RequestBody SavedBelowInfo savedBelowInfo, @PathVariable("id") Long id) {
        SavedBelowInfo savedBelowInfo1 = importantService.saveImportant(savedBelowInfo, id);
        HttpResponse responseBody = HttpResponse.builder()
                .timeStamp(now().toString())
                .data(of("saved belowInfo", savedBelowInfo))
                .message("Saved Info to favourites")
                .status(OK)
                .statusCode(OK.value())
                .build();
        return new ResponseEntity<>(responseBody, OK);
    }

    @GetMapping(path = "/important", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpResponse> getAllImportantInformation() {
        List<BelowInfo> belowInfo = importantService.getAllImportant();
        CurrentBelowInfo currentBelowInfo = importantService.getCurrentImportant();
        HttpResponse responseBody = HttpResponse.builder()
                .timeStamp(now().toString())
                .data(of("important", belowInfo, "current", currentBelowInfo))
                .message("Fetched all and current important")
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

    @GetMapping(path = "/news", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpResponse> getAllNews() {
        List<News> news = newsService.getAllNews();
        HttpResponse responseBody = HttpResponse.builder()
                .timeStamp(now().toString())
                .data(of("news", news))
                .message("Fetched all news")
                .status(OK)
                .statusCode(OK.value())
                .build();
        return new ResponseEntity<>(responseBody, OK);
    }

    @GetMapping(path = "/saved/important", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpResponse> getAllSavedImportantInformation() {
        List<SavedBelowInfo> savedBelowInfos = importantService.getAllSavedImportant();
        HttpResponse responseBody = HttpResponse.builder()
                .timeStamp(now().toString())
                .data(of( "saved", savedBelowInfos))
                .message("Fetched all saved important")
                .status(OK)
                .statusCode(OK.value())
                .build();
        return new ResponseEntity<>(responseBody, OK);
    }

    @DeleteMapping("/delete/important/{id}")
    public ResponseEntity<HttpResponse> deleteImportant(@PathVariable("id") Long id) {
        importantService.deleteImportant(id);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("deleted important with id: ", id))
                        .message("BelowInfo deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @DeleteMapping("/delete/favourite/{id}")
    public ResponseEntity<HttpResponse> deleteFavourite(@PathVariable("id") Long id) {
        importantService.deleteFavouriteImportant(id);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("deleted favourite with id: ", id))
                        .message("Favourite deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }


    @PatchMapping("/update/important/{id}")
    public ResponseEntity<HttpResponse> updateImportantInformation(@PathVariable("id") Long id, @RequestBody String description){
        importantService.updateImportantInformation(id, description);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("updated info for: ", id))
                        .message("Important updated")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

}
