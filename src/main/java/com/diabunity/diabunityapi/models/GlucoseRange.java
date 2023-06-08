package com.diabunity.diabunityapi.models;

public class GlucoseRange {
    private Double min;
    private Double max;

    public GlucoseRange(Double min, Double max) {
        this.min = min;
        this.max = max;
    }

    // Getters and setters

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }
}
