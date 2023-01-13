package com.diabunity.diabunityapi.services;

import com.diabunity.diabunityapi.models.User;
import com.diabunity.diabunityapi.repositories.UserRepository;
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
    Optional<User> user = userRepository.findById(id);

    if (user.isPresent()) {
      user.get().setVerified(VerifiedUserService.isVerified(user.get().getId()));
    }

   return user;
  }

}
