package com.diabunity.diabunityapi.models;

import java.util.List;
import org.springframework.data.mongodb.core.mapping.Field;

public class MeasurementsResponse {

  @Field
  private List<Measurement> measurements;

  @Field
  private MeasurementAverage avg;

  public MeasurementsResponse(List<Measurement> measurements, MeasurementAverage avg) {
    this.measurements = measurements;
    this.avg = avg;
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
}
