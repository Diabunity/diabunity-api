package com.diabunity.diabunityapi.controllers;

import com.diabunity.diabunityapi.models.Reaction;
import com.diabunity.diabunityapi.services.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ReactionController {

    @Autowired
    private ReactionService reactionService;

    @PostMapping("/posts/{post_id}/reaction")
    public Object saveReaction(HttpServletRequest request,
                               @PathVariable(value="post_id") String postId,
                               @RequestBody Reaction reaction) throws Exception {

            String authorizedUser = request.getSession().getAttribute("authorized_user").toString();
            reaction.setUserId(authorizedUser);
            reaction.setPostId(postId);

            Reaction reactionResponse = reactionService.saveReaction(reaction);
            return new ResponseEntity<>(reactionResponse, HttpStatus.OK);
    }

    @DeleteMapping("/users/{user_id}/posts/{post_id}/reaction/{name_emoji}")
    public Object deleteReaction(HttpServletRequest request,
                               @PathVariable(value="post_id") String postId,
                               @PathVariable(value="name_emoji") String nameEmoji) throws Exception {

            String authorizedUser = request.getSession().getAttribute("authorized_user").toString();

            reactionService.deleteUserReaction(authorizedUser, postId, nameEmoji);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
