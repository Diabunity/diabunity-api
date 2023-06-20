package com.diabunity.diabunityapi.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.diabunity.diabunityapi.exceptions.InvalidUserTokenException;
import com.diabunity.diabunityapi.models.Feedback;
import com.diabunity.diabunityapi.services.FeedbackService;

@RestController
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/users/{user_id}/feedback")
    public Object saveFeedback(HttpServletRequest request,
            @PathVariable(value = "user_id") String uid,
            @RequestBody Feedback feedback) throws Exception {

        String authorizedUser = request.getSession().getAttribute("authorized_user").toString();

        if (!authorizedUser.equals(uid)) {
            throw new InvalidUserTokenException();
        }

        Feedback response = feedbackService.saveFeedback(uid, feedback);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
