package com.diabunity.diabunityapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MeasurementsRequest {
    List<Measurement> measurements;

    @JsonProperty("trend_history")
    int[] trendHistory;

    public MeasurementsRequest(List<Measurement> measurements, int[] trendHistory) {
        this.measurements = measurements;
        this.trendHistory = trendHistory;
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }

    public int[] getTrendHistory() {
        return trendHistory;
    }

    public void setTrendHistory(int[] trendHistory) {
        this.trendHistory = trendHistory;
    }
}
