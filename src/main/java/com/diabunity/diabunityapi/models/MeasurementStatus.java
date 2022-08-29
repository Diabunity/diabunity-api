package com.diabunity.diabunityapi.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MeasurementStatus {
    LOW,
    OK,
    HIGH,
    SUPER_HIGH;

    @JsonValue
    public int toValue() {
        return ordinal();
    }


}
