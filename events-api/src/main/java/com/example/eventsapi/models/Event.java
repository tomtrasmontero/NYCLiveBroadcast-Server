package com.example.eventsapi.models;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "EVENTS")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "EVENT_ID")
    private String eventId;

    public Event(String userId, String eventId) {
        this.userId = userId;
        this.eventId = eventId;
    }
}
