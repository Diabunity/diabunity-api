package com.diabunity.diabunityapi.controllers;

import com.diabunity.diabunityapi.models.Emoji;
import com.diabunity.diabunityapi.models.Reaction;
import com.diabunity.diabunityapi.services.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ReactionController {

    @Autowired
    private ReactionService reactionService;

    @PostMapping("/post/{post_id}/reaction")
    public Object saveReaction(HttpServletRequest request,
                               @PathVariable(value="post_id") String postId,
                               @PathVariable(value="emoji") String emoji,
                               @RequestBody List<Emoji> emojis) throws Exception {

        String authorizedUser = request.getSession().getAttribute("authorized_user").toString();

        reactionService.saveUserReaction(authorizedUser, postId, emoji);

        Reaction reactionResponse = reactionService.saveReaction(emojis, postId);

        return new ResponseEntity<>(reactionResponse, HttpStatus.OK);
    }

    @DeleteMapping("/post/{post_id}/reaction")
    public Object deleteReaction(HttpServletRequest request,
                               @PathVariable(value="post_id") String postId,
                               @PathVariable(value="emoji") String emoji,
                               @RequestBody List<Emoji> emojis) throws Exception {

        String authorizedUser = request.getSession().getAttribute("authorized_user").toString();

        reactionService.deleteUserReaction(authorizedUser, postId, emoji);

         reactionService.saveReaction(emojis, postId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
