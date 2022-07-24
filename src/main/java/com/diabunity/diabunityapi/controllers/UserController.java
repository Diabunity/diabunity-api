package com.diabunity.diabunityapi.controllers;

import com.diabunity.diabunityapi.models.User;
import com.diabunity.diabunityapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.ServerException;
import java.util.List;

@RestController
public class UserController {
    private final UserRepository userRepository;
    @Autowired
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostMapping(path = "saveUser",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> create(@RequestBody User newUser) {
        User user = userRepository.save(newUser);
        if (user == null) {
        } else {
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        return null;
    }

    @GetMapping("/listUsers")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

}
