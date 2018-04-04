package com.example.eventsapi.controllers;

import com.example.eventsapi.models.Event;
import com.example.eventsapi.repositories.EventRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class EventsController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/")
    public Iterable<Event> findAllEvents() {
        return eventRepository.findAll();
    }

    @GetMapping("/{eventId}")
    public Event findEventById(@PathVariable Long eventId) throws NotFoundException {

        Event foundEvent = eventRepository.findOne(eventId);

        if (foundEvent == null) {
            throw new NotFoundException("Event with ID of " + eventId + " was not found!");
        }

        return foundEvent;
    }

    @DeleteMapping("/{eventId}")
    public HttpStatus deleteEventById(@PathVariable Long eventId) throws EmptyResultDataAccessException {

        eventRepository.delete(eventId);
        return HttpStatus.OK;
    }

    @PostMapping("/")
    public Event createNewEvent(@RequestBody Event newEvent) {
        return eventRepository.save(newEvent);
    }

    @PatchMapping("/{eventId}")
    public Event updateEventById(@PathVariable Long eventId, @RequestBody Event eventRequest) throws NotFoundException {
        Event eventFromDb = eventRepository.findOne(eventId);

        if (eventFromDb == null) {
            throw new NotFoundException("Event with ID of " + eventId + " was not found!");
        }

        eventFromDb.setUserId(eventRequest.getUserId());
        eventFromDb.setEventId(eventRequest.getEventId());

        return eventRepository.save(eventFromDb);
    }

    @ExceptionHandler
    void handleEventNotFound(
            NotFoundException exception,
            HttpServletResponse response) throws IOException {

        response.sendError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @ExceptionHandler
    void handleDeleteNotFoundException(
            EmptyResultDataAccessException exception,
            HttpServletResponse response) throws IOException {

        response.sendError(HttpStatus.NOT_FOUND.value());
    }
}

