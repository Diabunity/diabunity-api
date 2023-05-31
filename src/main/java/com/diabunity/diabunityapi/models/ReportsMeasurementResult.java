package com.diabunity.diabunityapi.models;

import java.time.LocalDate;
import java.util.List;

public class ReportsMeasurementResult {
    private LocalDate timestamp;
    private List<ReportsMeasurementResultData> data;

    public ReportsMeasurementResult(LocalDate timestamp, List<ReportsMeasurementResultData> data) {
        this.timestamp = timestamp;
        this.data = data;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public List<ReportsMeasurementResultData> getData() {
        return data;
    }

    public void setData(List<ReportsMeasurementResultData> data) {
        this.data = data;
    }
}
