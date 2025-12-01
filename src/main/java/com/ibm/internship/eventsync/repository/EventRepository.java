package com.ibm.internship.eventsync.repository;

import com.ibm.internship.eventsync.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}