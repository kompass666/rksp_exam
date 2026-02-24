package com.nadezhdap.processor_service.controller;

import com.nadezhdap.processor_service.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    @PostMapping("/count")
    public ResponseEntity<Long> count() {
        long result = service.countAndSave();
        return ResponseEntity.ok(result);
    }
}