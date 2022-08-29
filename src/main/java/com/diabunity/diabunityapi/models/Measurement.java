package com.diabunity.diabunityapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document
public class Measurement {

    @Field
    @JsonIgnore
    private String userId;

    @Field
    private Double measurement;

    @Field
    private LocalDateTime timestamp;

    @Field
    private MeasurementSource source;

    @Field
    private String comments;

    @Transient
    private MeasurementStatus status;

    public Measurement(String userId, double measurement,
                       LocalDateTime timestamp, MeasurementSource source,
                       String comments) {
        this.userId = userId;
        this.measurement = measurement;
        this.timestamp = timestamp;
        this.source = source;
        this.comments = comments;
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

    public MeasurementSource getSource() {
        return source;
    }

    public void setSource(MeasurementSource source) {
        this.source = source;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public MeasurementStatus getStatus() {
        return status;
    }

    public void setStatus(MeasurementStatus status) {
        this.status = status;
    }

}
