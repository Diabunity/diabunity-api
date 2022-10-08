package com.diabunity.diabunityapi.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserReaction {

    private String userId;

    private String postId;

    private String emoji;

    public UserReaction(String userId, String postId, String emoji) {
        this.userId = userId;
        this.postId = postId;
        this.emoji = emoji;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }
}
