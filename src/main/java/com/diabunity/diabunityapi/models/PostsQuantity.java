package com.diabunity.diabunityapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostsQuantity {

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("qty_posts")
    private int qtyPosts;

    public PostsQuantity(String userId, int qtyPosts) {
        this.userId = userId;
        this.qtyPosts = qtyPosts;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getQtyPosts() {
        return qtyPosts;
    }

    public void setQtyPosts(int qtyPosts) {
        this.qtyPosts = qtyPosts;
    }
}
