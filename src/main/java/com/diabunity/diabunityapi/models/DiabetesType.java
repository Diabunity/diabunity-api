package com.diabunity.diabunityapi.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DiabetesType {
  TYPE1,
  TYPE2;

  @JsonValue
  public int toValue() {
    return ordinal();
  }
}
