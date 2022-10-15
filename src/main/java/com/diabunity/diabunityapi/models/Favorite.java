package com.diabunity.diabunityapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Favorite {

  @Field
  @JsonProperty("user_id")
  private String userId;

  @Field
  @JsonProperty("post_id")
  private String postId;

  public Favorite(String userId, String postId) {
    this.userId = userId;
    this.postId = postId;
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
}
