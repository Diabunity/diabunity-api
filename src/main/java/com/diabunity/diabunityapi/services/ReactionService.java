package com.diabunity.diabunityapi.services;

import com.diabunity.diabunityapi.models.Reaction;
import com.diabunity.diabunityapi.repositories.ReactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReactionService {

    @Autowired
    private ReactionRepository reactionRepository;

    public Reaction saveReaction(Reaction reaction) {
        return reactionRepository.save(reaction);
    }

    public List<Reaction> getReactionsByPost(String postId) {
        return reactionRepository.findByPostId(postId);
    }

    public Optional<Reaction> findReactionPerformed(String postId, String userId, String name) {
        return reactionRepository.findByPostIdAndUserIdAndName(postId, userId, name);
    }

    public void deleteUserReaction(String userId, String postId, String emoji) {
        reactionRepository.deleteByUserIdAndPostIdAndEmoji(userId, postId, emoji);
    }

}
