package com.diabunity.diabunityapi.repositories;

import com.diabunity.diabunityapi.models.Reaction;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ReactionRepository extends MongoRepository<Reaction, String> {

    List<Reaction> findByPostId(String postId);

    Optional<Reaction> findByPostIdAndUserIdAndName(String postId, String userId, String name);
    void deleteByUserIdAndPostIdAndEmoji(String userId, String postId, String emoji);

}
