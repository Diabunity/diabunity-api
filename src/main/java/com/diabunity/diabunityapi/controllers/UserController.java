package com.diabunity.diabunityapi.controllers;

import com.diabunity.diabunityapi.models.User;
import com.diabunity.diabunityapi.services.UserService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/users")
  public ResponseEntity<User> createUser(@RequestBody User userData) {
    if(userData.getId().isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    User user = userService.saveUser(userData);

    if (user == null) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return new ResponseEntity<>(user, HttpStatus.CREATED);
  }

  @PutMapping("/users")
  public ResponseEntity<User> updateUser(@RequestBody User userData) {
    if(userData.getId().isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    Optional<User> user = userService.getUser(userData.getId());

    if (user == null) {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    if (userService.saveUser(userData) == null) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return new ResponseEntity<>(user.get(), HttpStatus.CREATED);
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<User> getUser(@PathVariable String id) {
    if(id.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    Optional<User> user = userService.getUser(id);

    if (user == null) {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(user.get(), HttpStatus.CREATED);
  }

  @GetMapping("/users/")
  public ResponseEntity<List<User>> getAllUser() {
    List<User> users = userService.getAllUsers();

    if (users == null) {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(users, HttpStatus.OK);
  }
}
