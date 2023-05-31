package com.diabunity.diabunityapi.models;

import java.time.LocalDateTime;

public class ReportsMeasurementResultData {
    private LocalDateTime timestamp;
    private double value;

    public ReportsMeasurementResultData(LocalDateTime timestamp, double value) {
        this.timestamp = timestamp;
        this.value = value;
    }

    // Getters and setters

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

}
