package com.diabunity.diabunityapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.firebase.auth.UserRecord;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Document
public class Post {

    @Field
    private String id;

    @Field
    @NotNull(message = "body must not be null.")
    private String body;

    @Field
    private LocalDateTime timestamp;

    @Field
    @JsonProperty("parent_id")
    private String parentId;

    @JsonProperty("qty_comments")
    @Transient
    private int qtyComments;

    @JsonProperty("users_favorites")
    @Transient
    private List<String> usersFavorites;

    @Transient
    private List<Reaction> emojis;

    @Transient
    private UserInfo userInfo;

    public Post(String id, String body,
                LocalDateTime timestamp, String parentId, UserInfo userInfo) {
        this.id = id;
        this.body = body;
        this.timestamp = timestamp;
        this.parentId = parentId;
        this.userInfo = userInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public int getQtyComments() {
        return qtyComments;
    }

    public void setQtyComments(int qtyComments) {
        this.qtyComments = qtyComments;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }


    public List<String> getUsersFavorites() {
        return usersFavorites;
    }

    public void setUsersFavorites(List<String> usersFavorites) {
        this.usersFavorites = usersFavorites;
    }

    public List<Reaction> getEmojis() {
      return emojis;
    }

    public void setEmojis(List<Reaction> emojis) {
      this.emojis = emojis;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
