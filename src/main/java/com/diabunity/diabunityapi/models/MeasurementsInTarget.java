package com.diabunity.diabunityapi.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@CompoundIndex(name = "user_month_idx", unique = true, def = "{'userID' : 1, 'month' : 1}")
@Document
public class MeasurementsInTarget {
    @Id
    private ObjectId _id;

    @Field
    private String userID;

    @Field
    private Integer month;

    @Field
    private Integer totalMeasurements;

    @Field
    private Integer measurementsInTarget;

    public MeasurementsInTarget(String userID, Integer month, Integer measurementsInTarget, Integer totalMeasurements) {
        this.userID = userID;
        this.month = month;
        this.measurementsInTarget = measurementsInTarget;
        this.totalMeasurements = totalMeasurements;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Integer getTotalMeasurements() {
        return totalMeasurements;
    }

    public void setTotalMeasurements(Integer totalMeasurements) {
        this.totalMeasurements = totalMeasurements;
    }

    public Integer getMeasurementsInTarget() {
        return measurementsInTarget;
    }

    public void setMeasurementsInTarget(Integer measurementsInTarget) {
        this.measurementsInTarget = measurementsInTarget;
    }

}
