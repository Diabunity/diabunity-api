package com.diabunity.diabunityapi.repositories;

import com.diabunity.diabunityapi.models.Reaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReactionRepository extends MongoRepository<Reaction, String> {

}
