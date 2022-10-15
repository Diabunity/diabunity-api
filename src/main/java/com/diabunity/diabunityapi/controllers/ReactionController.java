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

    @PostMapping("/posts/{post_id}/reaction/{name_emoji}")
    public Object saveReaction(HttpServletRequest request,
                               @PathVariable(value="post_id") String postId,
                               @PathVariable(value="name_emoji") String emoji,
                               @RequestBody List<Emoji> listEmojis) throws Exception {

            String authorizedUser = request.getSession().getAttribute("authorized_user").toString();

            reactionService.saveUserReaction(authorizedUser, postId, emoji);
            Reaction reactionResponse = reactionService.saveReaction(listEmojis, postId);
            return new ResponseEntity<>(reactionResponse, HttpStatus.OK);
    }

    @PutMapping("/posts/{post_id}/reaction/{name_emoji}")
    public Object deleteReaction(HttpServletRequest request,
                               @PathVariable(value="post_id") String postId,
                               @PathVariable(value="name_emoji") String nameEmoji,
                               @RequestBody List<Emoji> listEmojis) throws Exception {

            String authorizedUser = request.getSession().getAttribute("authorized_user").toString();

            reactionService.deleteUserReaction(authorizedUser, postId, nameEmoji);
            reactionService.saveReaction(listEmojis, postId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
