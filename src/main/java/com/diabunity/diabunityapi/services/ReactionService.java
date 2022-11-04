package com.diabunity.diabunityapi.services;

import com.diabunity.diabunityapi.models.Reaction;
import com.diabunity.diabunityapi.repositories.ReactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void deleteUserReaction(String userId, String postId, String name) {
        reactionRepository.deleteByUserIdAndPostIdAndName(userId, postId, name);
    }

}
