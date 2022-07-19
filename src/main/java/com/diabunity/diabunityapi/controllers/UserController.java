package com.diabunity.diabunityapi.controllers;

import com.diabunity.diabunityapi.models.User;
import com.diabunity.diabunityapi.services.UserService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/user")
  public ResponseEntity<User> createUser(@RequestBody User userData) {
    if(userData.getId().isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    User user = userService.createUser(userData);

    if (user == null) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return new ResponseEntity<>(user, HttpStatus.CREATED);
  }

  @GetMapping("/user/{id}")
  public ResponseEntity<User> getUser(@PathVariable String id) {
    if(id.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    Optional<User> user = userService.getUser(id);

    if (user == null) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return new ResponseEntity<>(user.get(), HttpStatus.CREATED);
  }
}
