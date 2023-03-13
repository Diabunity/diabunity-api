package com.diabunity.diabunityapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import org.springframework.data.mongodb.core.mapping.Field;

public class MeasurementTracing {

    @Field
    @JsonProperty("user_id")
    private String userId;

    @Field
    private MeasurementSource source;

    @Field
    private int count;

    @Field
    private LocalDateTime timestamp;

    public MeasurementTracing(String userId, MeasurementSource source, int count, LocalDateTime timestamp) {
        this.userId = userId;
        this.source = source;
        this.count = count;
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public MeasurementSource getSource() {
        return source;
    }

    public void setSource(MeasurementSource source) {
        this.source = source;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
