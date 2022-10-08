package com.diabunity.diabunityapi.repositories;

import com.diabunity.diabunityapi.models.UserReaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserReactionRepository extends MongoRepository<UserReaction, String> {

    Optional<UserReaction> findByUserIdAndPostIdAndEmoji(String userId, String postId, String emoji);
    void deleteByUserIdAndPostIdAndEmoji(String userId, String postId, String emoji);
}
