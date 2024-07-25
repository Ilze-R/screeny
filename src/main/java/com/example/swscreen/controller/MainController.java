package com.example.swscreen.controller;

import com.example.swscreen.service.EventService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    private final EventService eventService;

}
