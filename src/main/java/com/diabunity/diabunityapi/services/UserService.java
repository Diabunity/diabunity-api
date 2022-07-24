package com.diabunity.diabunityapi.services;

import com.diabunity.diabunityapi.models.User;
import com.diabunity.diabunityapi.repositories.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public User saveUser(final User newUser) {
    return userRepository.save(newUser);
  }

  public Optional<User> getUser(final String id) {
   return userRepository.findById(id);
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

}
