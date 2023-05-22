package com.diabunity.diabunityapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MeasurementsReport {
    @JsonProperty("user_info")
    ReportsUserData userData;

    @JsonProperty("measurements_info")
    ReportsMeasurementsData measurementsData;

    public MeasurementsReport(ReportsUserData userData, ReportsMeasurementsData measurementsData) {
        this.userData = userData;
        this.measurementsData = measurementsData;
    }

    // Getters and setters

    public ReportsUserData getUserData() {
        return userData;
    }

    public void setUserData(ReportsUserData userData) {
        this.userData = userData;
    }

    public ReportsMeasurementsData getMeasurementsData() {
        return measurementsData;
    }

    public void setMeasurementsData(ReportsMeasurementsData measurementsData) {
        this.measurementsData = measurementsData;
    }
}
