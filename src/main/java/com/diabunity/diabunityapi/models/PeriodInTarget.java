package com.diabunity.diabunityapi.models;

import org.springframework.data.mongodb.core.mapping.Field;

public class PeriodInTarget {

  @Field
  private Double value;

  @Field
  private int status;

  public PeriodInTarget(Double value, int status) {
    this.value = value;
    this.status = status;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }
}
