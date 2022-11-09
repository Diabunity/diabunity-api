package com.diabunity.diabunityapi.models;

import java.util.List;

public class MeasurementsRequest {
    List<Measurement> measurements;

    int[] trend;

    public MeasurementsRequest(List<Measurement> measurements, int[] trend) {
        this.measurements = measurements;
        this.trend = trend;
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }

    public int[] getTrend() {
        return trend;
    }

    public void setTrend(int[] trend) {
        this.trend = trend;
    }
}
