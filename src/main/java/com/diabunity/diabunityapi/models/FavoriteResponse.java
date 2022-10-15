package com.diabunity.diabunityapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class FavoriteResponse {
    @JsonProperty("user_id")
    private String userId;

    private List<String> posts;

    public FavoriteResponse(String userId,  List<String> posts) {
        this.userId = userId;
        this.posts = posts;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public List<String> getPosts() {
        return posts;
    }

    public void setPosts(List<String> posts) {
        this.posts = posts;
    }
}
