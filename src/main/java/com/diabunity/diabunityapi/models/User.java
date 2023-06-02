package com.diabunity.diabunityapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class User {

    @Id
    @NotNull(message = "Id must not be null.")
    private String id;

    @Field
    @JsonProperty("diabetes_type")
    @NotNull(message = "Diabetes type must be 0 for type 1 or 1 for type 2.")
    private DiabetesType diabetesType;

    @Field
    @JsonProperty("birth_date")
    @NotNull(message = "Birth date must not be null.")
    private Date birthDate;

    @Field
    @JsonProperty("on_boarding")
    private boolean onBoarding;

    @Field
    @NotNull(message = "Weight must not be null.")
    private Double weight;

    @Field
    @NotNull(message = "Height must not be null.")
    private Double height;

    @Field
    @JsonProperty("glucose_min")
    @NotNull(message = "Glucose min must not be null.")
    private Double glucoseMin;

    @Field
    @JsonProperty("glucose_max")
    @NotNull(message = "Glucose max must not be null.")
    private Double glucoseMax;

    @Transient
    private boolean verified;

    @Field
    private Subscription subscription;

    public User(String id, DiabetesType diabetesType, Double weight,
                Double height, Date birthDate, boolean onBoarding,
                Double glucoseMin, Double glucoseMax, Subscription subscription) {
        this.id = id;
        this.diabetesType = diabetesType;
        this.weight = weight;
        this.height = height;
        this.birthDate = birthDate;
        this.onBoarding = onBoarding;
        this.glucoseMin = glucoseMin;
        this.glucoseMax = glucoseMax;
        if (subscription == null) {
            this.subscription = new Subscription(SubscriptionType.FREE, null);
        } else {
            this.subscription = subscription;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DiabetesType getDiabetesType() {
        return diabetesType;
    }

    public void setDiabetesType(DiabetesType diabetesType) {
        this.diabetesType = diabetesType;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isOnBoarding() {
        return onBoarding;
    }

    public void setOnBoarding(boolean onBoarding) {
        this.onBoarding = onBoarding;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getMinGlucose() {
        return glucoseMin;
    }

    public Double getMaxGlucose() {
        return glucoseMax;
    }

    public boolean isVerified() { return verified; }

    public void setVerified(boolean verified) { this.verified = verified; }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

}
