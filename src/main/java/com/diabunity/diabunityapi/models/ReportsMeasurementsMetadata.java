package com.diabunity.diabunityapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReportsMeasurementsMetadata {
    private int low;
    @JsonProperty("in_range")
    private int inRange;
    private int high;
    private int hyper;

    public ReportsMeasurementsMetadata(int low, int inRange, int high, int hyper) {
        this.low = low;
        this.inRange = inRange;
        this.high = high;
        this.hyper = hyper;
    }

    // Getters and setters

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public int getInRange() {
        return inRange;
    }

    public void setInRange(int inRange) {
        this.inRange = inRange;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public int getHyper() {
        return hyper;
    }

    public void setHyper(int hyper) {
        this.hyper = hyper;
    }
}
