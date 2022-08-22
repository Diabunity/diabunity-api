package com.diabunity.diabunityapi.models;

import java.util.List;
import org.springframework.data.mongodb.core.mapping.Field;

public class MeasurementsResponse {

  @Field
  private List<Measurement> measurements;

  @Field
  private MeasurementAVG avg;

  public MeasurementsResponse(List<Measurement> measurements, MeasurementAVG avg) {
    this.measurements = measurements;
    this.avg = avg;
  }

  public List<Measurement> getMeasurements() {
    return measurements;
  }

  public void setMeasurements(List<Measurement> measurements) {
    this.measurements = measurements;
  }

  public MeasurementAVG getAvg() {
    return avg;
  }

  public void setAvg(MeasurementAVG avg) {
    this.avg = avg;
  }
}
