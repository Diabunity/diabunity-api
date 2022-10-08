package com.diabunity.diabunityapi.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;

@Document
public class Post {

  @Field
  private String postId;

  @Field
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
  @NotNull(message = "Image max must not be null.")
  private String image;

  private int qtyComments;

  public Post(String id, String userId, String body, LocalDateTime timestamp, String parentId, String image, int qtyComments) {
    this.postId = id;
    this.userId = userId;
    this.body = body;
    this.timestamp = timestamp;
    this.parentId = parentId;
    this.image = image;
    this.qtyComments = qtyComments;
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
}
