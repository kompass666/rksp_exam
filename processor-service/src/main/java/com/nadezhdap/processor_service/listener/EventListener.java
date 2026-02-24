package com.nadezhdap.processor_service.listener;

import com.nadezhdap.processor_service.entity.EventEntity;
import com.nadezhdap.processor_service.repository.EventRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EventListener {

    private final EventRepository repository;

    public EventListener(EventRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = "${app.queue}")
    public void handle(String payload) {
        repository.save(new EventEntity(payload));
    }
}