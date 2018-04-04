package com.example.broadcastapi.models;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "BROADCAST")
public class Broadcast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ROOM_ID")
    private String roomId;

    @Column(name = "EVENT_ID")
    private String eventId;

    public Broadcast(String roomId, String eventId) {
        this.roomId = roomId;
        this.eventId = eventId;
    }
}
