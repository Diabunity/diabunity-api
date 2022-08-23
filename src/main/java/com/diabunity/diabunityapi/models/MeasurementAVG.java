package com.diabunity.diabunityapi.models;

import org.springframework.data.mongodb.core.mapping.Field;

public class MeasurementAVG {

  @Field
  private Double value;

  @Field
  private MeasurementStatus status;

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public MeasurementStatus getStatus() {
    return status;
  }

  public void setStatus(MeasurementStatus status) {
    this.status = status;
  }
}
