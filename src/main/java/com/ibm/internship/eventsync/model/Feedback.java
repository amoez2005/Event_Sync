package com.ibm.internship.eventsync.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private String sentiment;

    private LocalDateTime timestamp;


    @ManyToOne
    @JoinColumn(name = "event_id")
    @JsonIgnore
    private Event event;
}