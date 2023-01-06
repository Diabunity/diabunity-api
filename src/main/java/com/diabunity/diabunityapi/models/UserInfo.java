package com.diabunity.diabunityapi.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Field;

public class UserInfo {

    @Field
    @JsonProperty("user_id")
    private String userId;

    @Field
    @JsonProperty("display_name")
    private String displayName;

    @Field
    @JsonProperty("image_path")
    private String imagePath;

    @JsonCreator
    public UserInfo(String userId, String displayName, String imagePath) {
        this.userId = userId;
        this.displayName = displayName;
        this.imagePath = imagePath;
    }

    public UserInfo(String displayName, String imagePath) {
        this.displayName = displayName;
        this.imagePath = imagePath;
    }

    public UserInfo(String userId) {
        this.userId = userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
