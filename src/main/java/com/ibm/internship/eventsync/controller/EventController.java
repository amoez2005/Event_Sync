package com.ibm.internship.eventsync.controller;

import com.ibm.internship.eventsync.model.Event;
import com.ibm.internship.eventsync.model.Feedback;
import com.ibm.internship.eventsync.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;


    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }


    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }


    @PostMapping("/{id}/feedback")
    public Feedback submitFeedback(@PathVariable Long id, @RequestBody Map<String, String> requestBody) {

        String comment = requestBody.get("text");


        if (comment == null || comment.trim().isEmpty()) {
            throw new IllegalArgumentException("Feedback text cannot be empty.");
        }

        return eventService.addFeedback(id, comment);
    }


    @GetMapping("/{id}/summary")
    public Map<String, Object> getSummary(@PathVariable Long id) {
        return eventService.getEventSummary(id);
    }
}