package com.ibm.internship.eventsync.service;

import com.ibm.internship.eventsync.model.*;
import com.ibm.internship.eventsync.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final SentimentService sentimentService;


    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }


    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }


    public Feedback addFeedback(Long eventId, String feedbackText) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event with ID " + eventId + " was not found inside the database."));


        String sentiment = sentimentService.analyze(feedbackText);


        System.out.println("Processing feedback for Event: '" + event.getTitle() + "' | Detected Sentiment: " + sentiment);

        Feedback feedback = new Feedback();
        feedback.setText(feedbackText);
        feedback.setSentiment(sentiment);
        feedback.setTimestamp(LocalDateTime.now());
        feedback.setEvent(event);

        event.getFeedbackList().add(feedback);
        eventRepository.save(event);

        return feedback;
    }


    public Map<String, Object> getEventSummary(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Cannot generate summary: Event ID " + eventId + " is invalid."));


        Map<String, Long> stats = event.getFeedbackList().stream()
                .collect(Collectors.groupingBy(Feedback::getSentiment, Collectors.counting()));


        return Map.of(
                "eventName", event.getTitle(),
                "totalReviews", event.getFeedbackList().size(),
                "sentimentStats", stats
        );
    }
}