package com.nadezhdap.processor_service.service;

import com.nadezhdap.processor_service.repository.EventRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@Service
public class EventService {

    private final EventRepository repository;
    private final String clickhouseUrl;

    public EventService(EventRepository repository,
                        @Value("${app.clickhouse.url}") String clickhouseUrl) {
        this.repository = repository;
        this.clickhouseUrl = clickhouseUrl;
    }

    public long countAndSave() {
        long count = repository.count();

        try (Connection conn = DriverManager.getConnection(clickhouseUrl);
             PreparedStatement ps =
                     conn.prepareStatement("INSERT INTO app.event_counts (cnt) VALUES (?)")) {

            ps.setLong(1, count);
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return count;
    }
}