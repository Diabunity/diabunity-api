package com.diabunity.diabunityapi.models;

import java.util.List;

public class PostResponse {

  private List<Post> posts;

  private Pagining pagining;

  public PostResponse(List<Post> posts,Pagining pagining) {
    this.posts = posts;
    this.pagining = pagining;
  }

  public List<Post> getPosts() {
    return posts;
  }

  public void setPosts(List<Post> posts) {
    this.posts = posts;
  }

  public Pagining getPagining() {
    return pagining;
  }

  public void setPagining(Pagining pagining) {
    this.pagining = pagining;
  }
}
