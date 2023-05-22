package com.diabunity.diabunityapi.models;

public class GlucoseInfo {
    private GlucoseRange low;
    private GlucoseRange inRange;
    private GlucoseRange high;
    private GlucoseRange hyper;

    public GlucoseInfo(GlucoseRange low, GlucoseRange inRange, GlucoseRange high, GlucoseRange hyper) {
        this.low = low;
        this.inRange = inRange;
        this.high = high;
        this.hyper = hyper;
    }

    // Getters and setters

    public GlucoseRange getLow() {
        return low;
    }

    public void setLow(GlucoseRange low) {
        this.low = low;
    }

    public GlucoseRange getInRange() {
        return inRange;
    }

    public void setInRange(GlucoseRange inRange) {
        this.inRange = inRange;
    }

    public GlucoseRange getHigh() {
        return high;
    }

    public void setHigh(GlucoseRange high) {
        this.high = high;
    }

    public GlucoseRange getHyper() {
        return hyper;
    }

    public void setHyper(GlucoseRange hyper) {
        this.hyper = hyper;
    }
}
