package com.example.swscreen.controller;

import com.example.swscreen.domain.BelowInfo;
import com.example.swscreen.service.ImportantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
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

    @CrossOrigin(origins = "http://screen.local")
    @GetMapping("/test")
    public ResponseEntity<String> testCors() {
        return ResponseEntity.ok("CORS is working!");
    }

  //  @CrossOrigin(origins = "http://screen.local", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
    @PostMapping(path = "/create/important", produces = MediaType.APPLICATION_JSON_VALUE)
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
