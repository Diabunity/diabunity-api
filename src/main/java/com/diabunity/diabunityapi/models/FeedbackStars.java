package com.diabunity.diabunityapi.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum FeedbackStars {
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE;

    @JsonValue
    public int toValue() {
        return ordinal() + 1;
    }

}
