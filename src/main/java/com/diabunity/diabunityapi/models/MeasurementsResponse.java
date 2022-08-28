package com.diabunity.diabunityapi.models;

import java.util.List;

public class MeasurementsResponse {

  private List<Measurement> measurements;

  private MeasurementAverage avg;

  private PeriodInTarget periodInTarget;

  public MeasurementsResponse(List<Measurement> measurements, MeasurementAverage avg,
                              PeriodInTarget periodInTarget) {
    this.measurements = measurements;
    this.avg = avg;
    this.periodInTarget = periodInTarget;
  }

  public MeasurementsResponse(List<Measurement> measurements) {
    this.measurements = measurements;
  }

  public List<Measurement> getMeasurements() {
    return measurements;
  }

  public void setMeasurements(List<Measurement> measurements) {
    this.measurements = measurements;
  }

  public MeasurementAverage getAvg() {
    return avg;
  }

  public void setAvg(MeasurementAverage avg) {
    this.avg = avg;
  }

  public PeriodInTarget getPeriodInTarget() { return periodInTarget; }

  public void setPeriodInTarget(PeriodInTarget periodInTarget) { this.periodInTarget = periodInTarget; }
}
