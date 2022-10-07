package com.diabunity.diabunityapi.models;

import java.util.List;

public class PostResponse {

  private List<Post> posts;

  private long totalPages;

  private long totalElements;

  public PostResponse(List<Post> posts, long totalPages, long totalElements) {
    this.posts = posts;
    this.totalPages = totalPages;
    this.totalElements = totalElements;
  }

  public List<Post> getPosts() {
    return posts;
  }

  public void setPosts(List<Post> posts) {
    this.posts = posts;
  }

  public long getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(long totalPages) {
    this.totalPages = totalPages;
  }

  public long getTotalElements() {
    return totalElements;
  }

  public void setTotalElements(long totalElements) {
    this.totalElements = totalElements;
  }
}
