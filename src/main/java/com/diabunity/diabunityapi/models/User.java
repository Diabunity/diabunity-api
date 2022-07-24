package com.diabunity.diabunityapi.models;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class User {

  @Id
  private String id;

  @Field
  private DiabetesType diabetesType;

  @Field
  private Date birthDate;

  @Field
  private boolean onBoarding;

  @Field
  private Double weight;

  @Field
  private Double height;

  public User(String id, DiabetesType diabetesType, Double weight,
              Double height, Date birthDate, boolean onBoarding) {
    this.id = id;
    this.diabetesType = diabetesType;
    this.weight = weight;
    this.height = height;
    this.birthDate = birthDate;
    this.onBoarding = onBoarding;
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

}
