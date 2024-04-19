package com.example.swscreen.controller;

import com.example.swscreen.domain.BelowInfo;
import com.example.swscreen.service.ImportantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.swscreen.domain.HttpResponse;

import static java.time.LocalTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = "/screen")
@RequiredArgsConstructor
public class MainController {

    private final ImportantService importantService;

    @PostMapping(path = "/create/important")
    public ResponseEntity<HttpResponse> addImportantInformation(@RequestBody BelowInfo belowInfo) {
   BelowInfo createdBelowInfo = importantService.createImportant(belowInfo);
        return ResponseEntity.ok(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("belowInfo", createdBelowInfo))
                        .message("BelowInfo created")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
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