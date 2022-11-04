package com.diabunity.diabunityapi.repositories;

import com.diabunity.diabunityapi.models.Reaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReactionRepository extends MongoRepository<Reaction, String> {

    List<Reaction> findByPostId(String postId);

    void deleteByUserIdAndPostIdAndName(String userId, String postId, String name);

}
