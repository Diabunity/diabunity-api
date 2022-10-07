package com.diabunity.diabunityapi.models;

import java.util.List;

public class MeasurementsResponse {

  private List<Measurement> measurements;

  private MeasurementAverage avg;

  private PeriodInTarget periodInTarget;

  private int totalPages;

  private long totalElements;

  public MeasurementsResponse(List<Measurement> measurements, MeasurementAverage avg,
                              PeriodInTarget periodInTarget, int pages, long totalElements) {
    this.measurements = measurements;
    this.avg = avg;
    this.periodInTarget = periodInTarget;
    this.totalPages = pages;
    this.totalElements = totalElements;
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

  public int getTotalPages() { return totalPages; }

  public void setTotalPages(int totalPages) { this.totalPages = totalPages; }

  public long getTotalElements() { return totalElements; }

  public void setTotalElements(long totalElements) { this.totalElements = totalElements; }
}
