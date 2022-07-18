package com.diabunity.diabunityapi.repositories;

import com.diabunity.diabunityapi.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
