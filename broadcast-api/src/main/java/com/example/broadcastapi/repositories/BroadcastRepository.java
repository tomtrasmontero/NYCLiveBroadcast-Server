package com.example.broadcastapi.repositories;

import com.example.broadcastapi.models.Broadcast;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BroadcastRepository extends CrudRepository<Broadcast, Long> {

    List<Broadcast> findAll();

    Broadcast findByroomId(String roomId);

}