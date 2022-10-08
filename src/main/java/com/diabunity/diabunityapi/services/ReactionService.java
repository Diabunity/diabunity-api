package com.diabunity.diabunityapi.services;

import com.diabunity.diabunityapi.models.Emoji;
import com.diabunity.diabunityapi.models.Reaction;
import com.diabunity.diabunityapi.models.UserReaction;
import com.diabunity.diabunityapi.repositories.ReactionRepository;
import com.diabunity.diabunityapi.repositories.UserReactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReactionService {
    @Autowired
    private UserReactionRepository userReactionRepository;

    @Autowired
    private ReactionRepository reactionRepository;

    public Reaction saveReaction(List<Emoji> emojis, String postId) {
        Reaction reaction = new Reaction(emojis, postId);
        return reactionRepository.save(reaction);
    }

    public List<Emoji> getReactionsByPostId(String postId) {
        Optional<Reaction> reaction = reactionRepository.findById(postId);

        if (!reaction.isPresent()) {
            return null;
        }

        return reaction.get().getEmojis();
    }

    public Optional<UserReaction> getUserReactions(String userId, String postId, String emoji) {
        return userReactionRepository.findByUserIdAndPostIdAndEmoji(userId, postId, emoji);
    }

    public UserReaction saveUserReaction(String userId, String postId, String emoji) {
        UserReaction userReaction = new UserReaction(userId, postId, emoji);
        return userReactionRepository.save(userReaction);
    }


    public void deleteUserReaction(String userId, String postId, String emoji) {
        userReactionRepository.deleteByUserIdAndPostIdAndEmoji(userId, postId, emoji);
    }

}
