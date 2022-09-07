package com.diabunity.diabunityapi.models;

import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Post {

  @Field
  private String id;

  @Field
  private String userId;

  @Field
  private String body;

  @Field
  private List<ChildPost> childPostList;

  public Post(String id, String userId, String body, List<ChildPost> childPostList) {
    this.id = id;
    this.userId = userId;
    this.body = body;
    this.childPostList = childPostList;
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

  public List<ChildPost> getChildPostList() {
    return childPostList;
  }

  public void setChildPostList(List<ChildPost> childPostList) {
    this.childPostList = childPostList;
  }
}
