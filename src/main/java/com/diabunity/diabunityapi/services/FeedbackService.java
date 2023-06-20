package com.diabunity.diabunityapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diabunity.diabunityapi.models.Feedback;
import com.diabunity.diabunityapi.repositories.FeedbackRepository;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    public Feedback saveFeedback(String userId, Feedback feedback) {
        feedback.setUserId(userId);
        feedback.setTimestamp(java.time.LocalDateTime.now());
        return feedbackRepository.save(feedback);
    }
}
