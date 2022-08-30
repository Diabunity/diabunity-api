package com.diabunity.diabunityapi.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PeriodInTargetStatus {
  BAD,
  STABLE,
  GOOD;

  @JsonValue
  public int toValue() {
    return ordinal();
  }
}
