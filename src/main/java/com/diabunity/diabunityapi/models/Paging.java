package com.diabunity.diabunityapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Paging {

    @JsonProperty("total_pages")
    private long totalPages;

    @JsonProperty("total_elements")
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
