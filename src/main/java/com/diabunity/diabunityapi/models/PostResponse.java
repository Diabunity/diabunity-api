package com.diabunity.diabunityapi.models;

import java.util.List;

public class PostResponse {

  private List<Post> posts;

  private Paging paging;

  public PostResponse(List<Post> posts, Paging paging) {
    this.posts = posts;
    this.paging = paging;
  }

  public List<Post> getPosts() {
    return posts;
  }

  public void setPosts(List<Post> posts) {
    this.posts = posts;
  }

  public Paging getPagining() {
    return paging;
  }

  public void setPagining(Paging paging) {
    this.paging = paging;
  }
}
