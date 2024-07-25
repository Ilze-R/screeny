package com.example.swscreen.controller;

import com.example.swscreen.domain.FavouriteImportant;
import com.example.swscreen.domain.HttpResponse;
import com.example.swscreen.domain.Important;
import com.example.swscreen.service.ImportantService;
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
public class ImportantController {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    private final ImportantService importantService;

    @PostMapping(path = "/create/important", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpResponse> addImportantInformation(@RequestBody Important important) {
        logger.info("Received request for important: {}", important);
        Important createdImportant = importantService.createImportant(important);
        logger.info("Created new important: {}", createdImportant);
        HttpResponse responseBody = HttpResponse.builder()
                .timeStamp(now().toString())
                .data(of("belowInfo", createdImportant))
                .message("BelowInfo created")
                .status(OK)
                .statusCode(OK.value())
                .build();
        return new ResponseEntity<>(responseBody, OK);
    }

    @PostMapping(path = "/save/important/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpResponse> saveImportantInformation(@RequestBody FavouriteImportant favouriteImportant, @PathVariable("id") Long id) {
        FavouriteImportant savedImportant = importantService.saveFavouriteImportant(favouriteImportant, id);
        HttpResponse responseBody = HttpResponse.builder()
                .timeStamp(now().toString())
                .data(of("saved important to favourites", favouriteImportant))
                .message("Saved important to favourites")
                .status(OK)
                .statusCode(OK.value())
                .build();
        return new ResponseEntity<>(responseBody, OK);
    }

    @GetMapping(path = "/important", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpResponse> getAllImportantInformation() {
        List<Important> important = importantService.getAllImportant();
        HttpResponse responseBody = HttpResponse.builder()
                .timeStamp(now().toString())
                .data(of("important", important))
                .message("Fetched all and current important")
                .status(OK)
                .statusCode(OK.value())
                .build();
        return new ResponseEntity<>(responseBody, OK);
    }

    @GetMapping(path = "/favourite/important", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpResponse> getAllSavedImportantInformation() {
        List<FavouriteImportant> favouriteImportant = importantService.getAllFavouriteImportant();
        HttpResponse responseBody = HttpResponse.builder()
                .timeStamp(now().toString())
                .data(of( "favourites", favouriteImportant))
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
                        .data(of("Deleted important with id: ", id))
                        .message("Important deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @DeleteMapping("/delete/favourite/important/{id}")
    public ResponseEntity<HttpResponse> deleteFavourite(@PathVariable("id") Long id) {
        importantService.deleteFavouriteImportant(id);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("Deleted favourite with id: ", id))
                        .message("Favourite deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @PatchMapping("/update/important/{id}")
    public ResponseEntity<HttpResponse> updateImportantInformation(@PathVariable("id") Long id, @RequestBody String description){
        importantService.updateImportant(id, description);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("Updated info for: ", id))
                        .message("Important updated")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @PatchMapping("/deactivate/important/{id}")
    public ResponseEntity<HttpResponse> deactivateImportant(@PathVariable("id") Long id){
        importantService.deactivateImportant(id);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("Deactivated important with id: ", id))
                        .message("Important deactivated")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @PatchMapping("/activate/important/{id}")
    public ResponseEntity<HttpResponse> activateImportant(@PathVariable("id") Long id){
        importantService.activateImportant(id);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("Activated important with id: ", id))
                        .message("Important activated")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }
}
