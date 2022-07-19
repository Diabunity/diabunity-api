package com.diabunity.diabunityapi.controllers;

import com.diabunity.diabunityapi.services.UserService;
import java.util.UUID;
import org.bson.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/user/{id}")
  public UUID createUser(@PathVariable("id") UUID id, @RequestBody JsonObject data) {
    return userService.create(id, data);
  }

}
