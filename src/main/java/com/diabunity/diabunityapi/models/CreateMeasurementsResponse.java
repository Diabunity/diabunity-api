package com.diabunity.diabunityapi.models;

import java.util.List;

public class CreateMeasurementsResponse {
    List<Measurement> measurements;

    Tendency tendency;

    public CreateMeasurementsResponse(List<Measurement> measurements, Tendency tendency) {
        this.measurements = measurements;
        this.tendency = tendency;
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }

    public Tendency getTendency() {
        return tendency;
    }

    public void setTendency(Tendency tendency) {
        this.tendency = tendency;
    }
}
