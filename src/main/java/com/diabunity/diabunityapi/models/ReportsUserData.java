package com.diabunity.diabunityapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReportsUserData {

    private String name;
    private int age;
    private int weight;
    private int height;
    @JsonProperty("diabetes_type")
    private int diabetesType;
    @JsonProperty("glucose_info")
    private GlucoseInfo glucoseInfo;

    public ReportsUserData(String name, int age, int weight, int height, int diabetesType, GlucoseInfo glucoseInfo) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.diabetesType = diabetesType;
        this.glucoseInfo = glucoseInfo;
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getDiabetesType() {
        return diabetesType;
    }

    public void setDiabetesType(int diabetesType) {
        this.diabetesType = diabetesType;
    }

    public GlucoseInfo getGlucoseInfo() {
        return glucoseInfo;
    }

    public void setGlucoseInfo(GlucoseInfo glucoseInfo) {
        this.glucoseInfo = glucoseInfo;
    }
}

