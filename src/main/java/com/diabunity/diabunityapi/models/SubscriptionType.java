package com.diabunity.diabunityapi.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SubscriptionType {
    FREE,
    PREMIUM;

    @JsonValue
    public int toValue() {
        return ordinal();
    }
}
