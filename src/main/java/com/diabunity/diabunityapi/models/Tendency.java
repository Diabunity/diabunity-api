package com.diabunity.diabunityapi.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Tendency {
    RISING,
    RISING_QUICKLY,
    CHANGING_SLOWLY,
    FAILING,
    FAILING_QUICKLY;

    @JsonValue
    public int toValue() {
        return ordinal();
    }
}
