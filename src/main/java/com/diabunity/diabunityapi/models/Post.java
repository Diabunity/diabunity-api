package com.diabunity.diabunityapi.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

import com.google.firebase.auth.UserRecord;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;

@Document
public class Post {

  @Field
  private String id;

  @Field
  @JsonProperty("user_id")
  private String userId;

  @Field
  @NotNull(message = "body must not be null.")
  private String body;

  @Field
  private LocalDateTime timestamp;

  @Field
  @JsonProperty("parent_id")
  private String parentId;

  @Field
  private String image;

  @JsonProperty("qty_comments")
  @Transient
  private int qtyComments;

  @JsonProperty("users_favorites")
  @Transient
  private List<String> usersFavorites;

  @Field
  @Transient
  private List<Reaction> emojis;
  @Transient
  private UserRecord user;

  public Post(String id, String userId, String body,
              LocalDateTime timestamp, String parentId, String image) {
    this.id = id;
    this.userId = userId;
    this.body = body;
    this.timestamp = timestamp;
    this.parentId = parentId;
    this.image = image;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
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

  public int getQtyComments() { return qtyComments; }

  public void setQtyComments(int qtyComments) { this.qtyComments = qtyComments; }

  public LocalDateTime getTimestamp() { return timestamp; }

  public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

  public String getImage() { return image; }

  public void setImage(String image) { this.image = image; }

  public List<String> getUsersFavorites() { return usersFavorites; }

  public void setUsersFavorites(List<String> usersFavorites) { this.usersFavorites = usersFavorites; }

  public List<Reaction> getEmojis() {
    return emojis;
  }
  public void setEmojis(List<Reaction> emojis) {
    this.emojis = emojis;
  }

  public UserRecord getUser() {
    return user;
  }

  public void setUser(UserRecord user) {
    this.user = user;
  }
}
