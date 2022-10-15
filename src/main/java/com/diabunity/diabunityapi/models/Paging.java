package com.diabunity.diabunityapi.models;

public class Paging {
  private long totalPages;

  private long totalElements;

  public Paging(long totalPages, long totalElements) {
    this.totalPages = totalPages;
    this.totalElements = totalElements;
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
