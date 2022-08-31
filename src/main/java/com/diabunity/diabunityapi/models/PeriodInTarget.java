package com.diabunity.diabunityapi.models;

import org.springframework.data.mongodb.core.mapping.Field;

public class PeriodInTarget {

    @Field
    private Double value;

    @Field
    private PeriodInTargetStatus status;

    public PeriodInTarget(Double value) {
        this.value = value;
        this.status = calculateStatusPeriodInTarget(value);
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

    private PeriodInTargetStatus calculateStatusPeriodInTarget(Double value) {
        if (value < 0.5D) {
            return PeriodInTargetStatus.BAD;
        } else if (value < 0.8D) {
            return PeriodInTargetStatus.STABLE;
        }
        return PeriodInTargetStatus.GOOD;
    }
}
