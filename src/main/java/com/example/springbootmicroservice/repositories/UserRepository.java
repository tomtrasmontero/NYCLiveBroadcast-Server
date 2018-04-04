package com.example.springbootmicroservice.repositories;

import com.example.springbootmicroservice.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}