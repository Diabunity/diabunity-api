package com.diabunity.diabunityapi.models;

import org.springframework.data.mongodb.core.mapping.Field;

public class PeriodInTarget {

  @Field
  private Double value;

  @Field
  private PeriodInTargetStatus status;

  public PeriodInTarget(Double value, PeriodInTargetStatus status) {
    this.value = value;
    this.status = status;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public PeriodInTargetStatus getStatus() {
    return status;
  }

  public void setStatus(PeriodInTargetStatus status) {
    this.status = status;
  }
}
