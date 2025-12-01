package com.ibm.internship.eventsync.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;


    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Feedback> feedbackList = new ArrayList<>();
}