package com.diabunity.diabunityapi.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MeasurementSource {
    MANUAL,
    SENSOR;

    @JsonValue
    public int toValue() {
        return ordinal();
    }

}
