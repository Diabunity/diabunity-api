package com.diabunity.diabunityapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

public class Measurement {

  @Field
  @JsonIgnore
  @Indexed(unique = true)
  private String userId;

  @Field
  private Double measurement;

  @Field
  @Indexed(unique = true)
  private LocalDateTime timestamp;

  @Field
  private MeasurementSource source;

  @Field
  private String comments;

  @Field
  private int status;

  public Measurement(String userId, double measurement,
                     LocalDateTime timestamp, MeasurementSource source,
                     String comments, MeasurementStatus status) {
    this.userId = userId;
    this.measurement = measurement;
    this.timestamp = timestamp;
    this.source = source;
    this.comments = comments;
    this.status = status.ordinal();
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public Double getMeasurement() {
    return measurement;
  }

  public void setMeasurement(Double measurement) {
    this.measurement = measurement;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public MeasurementSource getSource() {return source;}

  public void setSource(MeasurementSource source) { this.source = source; }

  public String getComments() { return comments; }

  public void setComments(String comments) { this.comments = comments; }

  public int getStatus() { return status; }

  public void setStatus(MeasurementStatus status) { this.status = status.ordinal(); }

}
