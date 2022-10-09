package com.diabunity.diabunityapi.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;

@Document
public class Post {

  @Field
  @Id
  @JsonProperty("post_id")
  private String postId;

  @Field
  @JsonProperty("user_id")
  private String userId;

  @Field
  @NotNull(message = "Body must not be null.")
  private String body;

  @Field
  private LocalDateTime timestamp;

  @Field
  @JsonProperty("parent_id")
  private String parentId;

  @Field
  private String image;

  @JsonProperty("qty_comments")
  private int qtyComments;

  @JsonProperty("users_favorites")
  private List<String> usersFavorites;

  @Field
  private List<Emoji> emojis;

  public Post(String postId, String userId, String body,
              LocalDateTime timestamp, String parentId, String image,
              int qtyComments, List<String> usersFavorites, List<Emoji> emojis) {
    this.postId = postId;
    this.userId = userId;
    this.body = body;
    this.timestamp = timestamp;
    this.parentId = parentId;
    this.image = image;
    this.qtyComments = qtyComments;
    this.usersFavorites = usersFavorites;
    this.emojis = emojis;
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

  public List<Emoji> getEmojis() {
    return emojis;
  }
  public void setEmojis(List<Emoji> emojis) {
    this.emojis = emojis;
  }
}
