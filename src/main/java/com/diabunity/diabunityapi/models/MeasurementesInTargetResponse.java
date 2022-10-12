package com.diabunity.diabunityapi.models;

public class MeasurementesInTargetResponse {

    private String username;

    private String picture;

    private Integer month;

    private Integer percentage;

    public MeasurementesInTargetResponse(String username, String picture, Integer month, Integer measuresInTargetPercentage) {
        this.username = username;
        this.picture = picture;
        this.month = month;
        this.percentage = measuresInTargetPercentage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }
}
