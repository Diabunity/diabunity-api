package com.diabunity.diabunityapi.models;

import java.util.List;

public class ReportsMeasurementsData {
    private ReportsMeasurementsMetadata reportsMeasurementsMetadata;
    private List<ReportsMeasurementResult> results;

    public ReportsMeasurementsData(ReportsMeasurementsMetadata reportsMeasurementsMetadata, List<ReportsMeasurementResult> results) {
        this.reportsMeasurementsMetadata = reportsMeasurementsMetadata;
        this.results = results;
    }

    // Getters and setters

    public ReportsMeasurementsMetadata getMetadata() {
        return reportsMeasurementsMetadata;
    }

    public void setMetadata(ReportsMeasurementsMetadata reportsMeasurementsMetadata) {
        this.reportsMeasurementsMetadata = reportsMeasurementsMetadata;
    }

    public List<ReportsMeasurementResult> getResults() {
        return results;
    }

    public void setResults(List<ReportsMeasurementResult> results) {
        this.results = results;
    }
}

