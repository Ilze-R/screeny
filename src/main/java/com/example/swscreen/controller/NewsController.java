package com.example.swscreen.controller;

import com.example.swscreen.domain.FavouriteNews;
import com.example.swscreen.domain.HttpResponse;
import com.example.swscreen.domain.News;
import com.example.swscreen.dto.UpdateNewsRequestDTO;
import com.example.swscreen.service.NewsService;
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
public class NewsController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    private final NewsService newsService;

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

    @DeleteMapping("/delete/news/{id}")
    public ResponseEntity<HttpResponse> deleteNews(@PathVariable("id") Long id) {
        newsService.deleteNews(id);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("deleted news with id: ", id))
                        .message("News deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @PatchMapping("/deactivate/news/{id}")
    public ResponseEntity<HttpResponse> deactivateNews(@PathVariable("id") Long id){
        newsService.deactivateNews(id);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("Deactivated news with id: ", id))
                        .message("News deactivated")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @PatchMapping("/activate/news/{id}")
    public ResponseEntity<HttpResponse> activateNews(@PathVariable("id") Long id){
        newsService.activateNews(id);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("Activated news with id: ", id))
                        .message("News activated")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @PatchMapping("/update/news/{id}")
    public ResponseEntity<HttpResponse> updateNews(@PathVariable("id") Long id, @RequestBody UpdateNewsRequestDTO updateNewsRequestDTO){
        String title = updateNewsRequestDTO.getTitle();
        String description = updateNewsRequestDTO.getDescription();
        newsService.updateNews(id, title, description);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("Updated news with id: ", id))
                        .message("News updated")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @PostMapping(path = "/favourite-save/news/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpResponse> saveNewsAsFavourite(@RequestBody FavouriteNews favouriteNews, @PathVariable("id") Long id) {
        FavouriteNews savedNews = newsService.saveFavouriteNews(favouriteNews, id);
        HttpResponse responseBody = HttpResponse.builder()
                .timeStamp(now().toString())
                .data(of("saved news to favourites", favouriteNews))
                .message("Saved news to favourites")
                .status(OK)
                .statusCode(OK.value())
                .build();
        return new ResponseEntity<>(responseBody, OK);
    }

    @GetMapping(path = "/favourite/news", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpResponse> getAllNewsFromFavourites() {
        List<FavouriteNews> favouriteNews = newsService.getAllFavouriteNews();
        HttpResponse responseBody = HttpResponse.builder()
                .timeStamp(now().toString())
                .data(of( "favourites", favouriteNews))
                .message("Fetched all news from favourites")
                .status(OK)
                .statusCode(OK.value())
                .build();
        return new ResponseEntity<>(responseBody, OK);
    }
    @DeleteMapping("/delete/favourite/news/{id}")
    public ResponseEntity<HttpResponse> deleteNewsFromFavourites(@PathVariable("id") Long id) {
       newsService.deleteFavouriteNews(id);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("Deleted favourite with id: ", id))
                        .message("Favourite deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

}
