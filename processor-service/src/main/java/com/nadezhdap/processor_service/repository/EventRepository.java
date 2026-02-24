package com.nadezhdap.processor_service.repository;

import com.nadezhdap.processor_service.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventEntity, Long> {
}