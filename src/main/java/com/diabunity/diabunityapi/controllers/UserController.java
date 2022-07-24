package com.diabunity.diabunityapi.controllers;

import com.diabunity.diabunityapi.exceptions.InvalidUserTokenException;
import com.diabunity.diabunityapi.exceptions.NotFoundException;
import com.diabunity.diabunityapi.models.User;
import com.diabunity.diabunityapi.services.UserService;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
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
  public ResponseEntity<User> createUser(HttpServletRequest request,
                                         @RequestBody User userData) throws Exception {

    String authorizedUser = request.getSession().getAttribute("authorized_user").toString();
    if (!authorizedUser.equals(userData.getId())) {
      throw new InvalidUserTokenException();
    }

    User user = userService.saveUser(userData);

    return new ResponseEntity<>(user, HttpStatus.CREATED);
  }

  @PutMapping("/users")
  public ResponseEntity<User> updateUser(HttpServletRequest request,
                                         @RequestBody User userData) throws Exception {

    String authorizedUser = request.getSession().getAttribute("authorized_user").toString();
    if (!authorizedUser.equals(userData.getId())) {
      throw new InvalidUserTokenException();
    }

    Optional<User> user = userService.getUser(userData.getId());

    if (user == null) {
      throw new NotFoundException("User was not founded with used_id " + userData.getId());
    }

    if (userService.saveUser(userData) == null) {
      throw new Exception("Save user failed.");
    }

    return new ResponseEntity<>(user.get(), HttpStatus.CREATED);
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<User> getUser(HttpServletRequest request,
                                      @PathVariable(value="id") String uid) throws Exception {

    String authorizedUser = request.getSession().getAttribute("authorized_user").toString();
    if (!authorizedUser.equals(uid)) {
      throw new InvalidUserTokenException();
    }

    Optional<User> user = userService.getUser(uid);

    if (user == null) {
      throw new NotFoundException("User was not founded by user_id " + uid);
    }

    return new ResponseEntity<>(user.get(), HttpStatus.CREATED);
  }

}
