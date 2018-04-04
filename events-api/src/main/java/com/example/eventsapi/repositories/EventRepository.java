package com.example.eventsapi.repositories;

import com.example.eventsapi.models.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventRepository extends CrudRepository<Event, Long> {

    List<Event> findAll();

}