package com.diabunity.diabunityapi.controllers;

import com.diabunity.diabunityapi.exceptions.BadRequestException;
import com.diabunity.diabunityapi.exceptions.InvalidUserTokenException;
import com.diabunity.diabunityapi.exceptions.NotFoundException;
import com.diabunity.diabunityapi.models.User;
import com.diabunity.diabunityapi.services.UserService;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/users")
  public ResponseEntity<User> createUser(HttpServletRequest request,
      @Valid @RequestBody User userData,
      BindingResult errors) throws Exception {

    if (errors.hasErrors()) {
      throw new BadRequestException("Parameters required but not found",
          errors.getAllErrors().stream().map(item -> item.getDefaultMessage()).collect(Collectors.toList()));
    }

    String authorizedUser = request.getSession().getAttribute("authorized_user").toString();
    if (!authorizedUser.equals(userData.getId())) {
      throw new InvalidUserTokenException();
    }

    User user = userService.saveUser(userData);

    return new ResponseEntity<>(user, HttpStatus.CREATED);
  }

  @PutMapping("/users/{id}")
  public ResponseEntity<User> updateUser(HttpServletRequest request,
      @PathVariable(value = "id") String uid,
      @Valid @RequestBody User userData,
      BindingResult errors) throws Exception {

    if (errors.hasErrors()) {
      throw new BadRequestException("Parameters required but not found",
          errors.getAllErrors().stream().map(item -> item.getDefaultMessage()).collect(Collectors.toList()));
    }

    String authorizedUser = request.getSession().getAttribute("authorized_user").toString();
    if (!authorizedUser.equals(uid)) {
      throw new InvalidUserTokenException();
    }

    Optional<User> user = userService.getUser(uid);

    if (user == null) {
      throw new NotFoundException("User not found with used_id " + uid);
    }

    userData.setId(uid);

    User updatedUser = userService.saveUser(userData);

    if (updatedUser == null) {
      throw new Exception("Save user failed.");
    }

    return new ResponseEntity<>(updatedUser, HttpStatus.OK);
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<User> getUser(HttpServletRequest request,
      @PathVariable(value = "id") String uid) throws Exception {

    String authorizedUser = request.getSession().getAttribute("authorized_user").toString();
    if (!authorizedUser.equals(uid)) {
      throw new InvalidUserTokenException();
    }

    Optional<User> user = userService.getUser(uid);

    if (user == null) {
      throw new NotFoundException("User not found by user_id " + uid);
    }

    return new ResponseEntity<>(user.get(), HttpStatus.OK);
  }

}
