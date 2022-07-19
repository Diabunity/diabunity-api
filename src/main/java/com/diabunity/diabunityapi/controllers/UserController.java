package com.diabunity.diabunityapi.controllers;

import com.diabunity.diabunityapi.models.User;
import com.diabunity.diabunityapi.services.UserService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
  public ResponseEntity<User> createUser(@RequestBody User userData) throws Exception {
    if(userData.getId() == null || userData.getId().isEmpty()) {
      throw new Exception("User ID is required.");
    }

    User user = userService.saveUser(userData);

    if (user == null) {
      throw new Exception("Save user failed.");
    }

    return new ResponseEntity<>(user, HttpStatus.CREATED);
  }

  @PutMapping("/users")
  public ResponseEntity<User> updateUser(@RequestBody User userData) throws Exception {
    if(userData == null || userData.getId().isEmpty()) {
      throw new Exception("User ID is required.");
    }

    Optional<User> user = userService.getUser(userData.getId());

    if (user == null) {
      throw new Exception("User was not founded with used_id " + userData.getId());
    }

    if (userService.saveUser(userData) == null) {
      throw new Exception("Save user failed.");
    }

    return new ResponseEntity<>(user.get(), HttpStatus.CREATED);
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<User> getUser(@PathVariable String id) throws Exception {
    if(id.isEmpty()) {
      throw new Exception("User ID is required.");
    }

    Optional<User> user = userService.getUser(id);

    if (user == null) {
      throw new Exception("User was not founded by user_id " + id);
    }

    return new ResponseEntity<>(user.get(), HttpStatus.CREATED);
  }

  @GetMapping("/users/")
  public ResponseEntity<List<User>> getAllUser() throws Exception {
    List<User> users = userService.getAllUsers();

    if (users == null || users.isEmpty()) {
      throw new Exception("Users were not founded");
    }

    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity handleException(Exception e) {
    return new ResponseEntity<>(e.getMessage() != "" ? e.getMessage() : e.getCause(), HttpStatus.BAD_REQUEST);
  }
}
