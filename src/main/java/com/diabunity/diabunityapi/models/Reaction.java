package com.diabunity.diabunityapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

public class Reaction {

    @Id
    @Field
    @JsonProperty("post_id")
    private String postId;

    @Field
    private List<Emoji> emojis;

    public Reaction(List<Emoji> emojis, String postId) {
        this.emojis = emojis;
        this.postId = postId;
    }

    public List<Emoji> getEmojis() {
        return emojis;
    }

    public void setEmojis(List<Emoji> emojis) {
        this.emojis = emojis;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
