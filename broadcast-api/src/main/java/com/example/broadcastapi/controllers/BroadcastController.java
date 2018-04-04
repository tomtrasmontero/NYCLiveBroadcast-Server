package com.example.broadcastapi.controllers;

import com.example.broadcastapi.models.Broadcast;
import com.example.broadcastapi.repositories.BroadcastRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class BroadcastController {

    @Autowired
    private BroadcastRepository broadcastRepository;

    @GetMapping("/")
    public Iterable<Broadcast> findAllUsers() {
        return broadcastRepository.findAll();
    }

    @GetMapping("/{broadcastId}")
    public Broadcast findEventByBroadcastId(@PathVariable Long broadcastId) throws NotFoundException {

        Broadcast foundBroadcast = broadcastRepository.findOne(broadcastId);

        if (foundBroadcast == null) {
            throw new NotFoundException("Broadcast for ID " + broadcastId + " was not found!");
        }

        return foundBroadcast;
    }

    @DeleteMapping("/{roomId}")
    public HttpStatus deleteEventByEventId(@PathVariable String roomId) throws EmptyResultDataAccessException {
        Broadcast broadcast = broadcastRepository.findByroomId(roomId);
        broadcastRepository.delete(broadcast.getId());
        return HttpStatus.OK;
    }

    @PostMapping("/")
    public Broadcast createNewBroadcast(@RequestBody Broadcast newBroadcast) {
        return broadcastRepository.save(newBroadcast);
    }

    @ExceptionHandler
    void handleUserNotFound(
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
