package com.example.swscreen.controller;

import com.example.swscreen.domain.BelowInfo;
import com.example.swscreen.service.ImportantService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.swscreen.domain.HttpResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.time.LocalTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = "/screen")
@RequiredArgsConstructor
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    private final ImportantService importantService;

    //@CrossOrigin(origins = "http://screen.local")
    @GetMapping("/test")
    public ResponseEntity<String> testCors() {
        return ResponseEntity.ok("CORS is working!");
    }

  //  @CrossOrigin(origins = "http://screen.local", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
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
//        return ResponseEntity.ok(
//                HttpResponse.builder()
//                        .timeStamp(now().toString())
//                        .data(of("belowInfo", createdBelowInfo))
//                        .message("BelowInfo created")
//                        .status(OK)
//                        .statusCode(OK.value())
//                        .build());

//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Access-Control-Allow-Origin", "*");  // Example CORS header
//        headers.add("Access-Control-Allow-Methods", "POST, GET, OPTIONS");

//        return new ResponseEntity<>(responseBody, headers, OK);

        return new ResponseEntity<>(responseBody, OK);
    }

    @DeleteMapping("/delete/important/{id}")
    public ResponseEntity<HttpResponse> deleteImportant(@PathVariable("id") Long id) {
        importantService.deleteImportant(id);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("important", id))
                        .message("BelowInfo deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

}
