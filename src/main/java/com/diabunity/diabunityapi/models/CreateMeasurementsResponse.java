package com.diabunity.diabunityapi.models;

import java.util.List;

public class CreateMeasurementsResponse {
    List<Measurement> measurements;

    Tendency tendency;

    List<MeasurementTracing> measurementTracing;

    public CreateMeasurementsResponse(List<Measurement> measurements, Tendency tendency, List<MeasurementTracing> measurementTracing) {
        this.measurements = measurements;
        this.tendency = tendency;
        this.measurementTracing = measurementTracing;
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

    public List<MeasurementTracing> getMeasurementTracing() { return measurementTracing; }

    public void setMeasurementTracing(List<MeasurementTracing> measurementTracing) { this.measurementTracing = measurementTracing; }
}
