package com.nadezhdap.ingest_service.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    private final RabbitTemplate rabbitTemplate;
    private final String queue;

    public EventController(RabbitTemplate rabbitTemplate,
                           @Value("${app.queue}") String queue) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
    }

    @PostMapping
    public ResponseEntity<Void> publish(@RequestBody String body) {
        rabbitTemplate.convertAndSend(queue, body);
        return ResponseEntity.accepted().build();
    }
}