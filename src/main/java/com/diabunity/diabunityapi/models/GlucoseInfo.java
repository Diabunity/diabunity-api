package com.diabunity.diabunityapi.models;

public class GlucoseInfo {
    private GlucoseRange low;
    private GlucoseRange range;
    private GlucoseRange high;
    private GlucoseRange hyper;

    public GlucoseInfo(GlucoseRange low, GlucoseRange range, GlucoseRange high, GlucoseRange hyper) {
        this.low = low;
        this.range = range;
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

    public GlucoseRange getRange() {
        return range;
    }

    public void setRange(GlucoseRange range) {
        this.range = range;
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
