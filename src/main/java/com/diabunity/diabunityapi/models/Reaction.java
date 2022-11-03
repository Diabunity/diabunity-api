package com.diabunity.diabunityapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

public class Reaction {

    @Field
    @JsonIgnore
    private String postId;

    @Field
    @JsonIgnore
    private String userId;

    @Field
    private Object data;

    @Field
    private String emoji;

    @Field
    private String name;

    @Transient
    private int index;

    @Transient
    private boolean isSelected;

    public Reaction(String postId, String userId, Object data, String emoji, String name) {
        this.postId = postId;
        this.userId = userId;
        this.data = data;
        this.emoji = emoji;
        this.name = name;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
