package com.diabunity.diabunityapi.models;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document
public class Feedback {

    @Field
    @JsonProperty("user_id")
    private String userId;

    @Field
    @JsonProperty("comment")
    private String comment;

    @Field
    @JsonProperty("stars")
    private FeedbackStars stars;

    @Field
    @JsonProperty("timestamp")
    private LocalDateTime timestamp;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setStars(FeedbackStars stars) {
        this.stars = stars;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getComment() {
        return this.comment;
    }

    public FeedbackStars getStars() {
        return this.stars;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

}
