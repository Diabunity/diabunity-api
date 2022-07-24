package com.diabunity.diabunityapi.repositories;

import com.diabunity.diabunityapi.models.User;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

  Optional<User> findById(final String id);

}
