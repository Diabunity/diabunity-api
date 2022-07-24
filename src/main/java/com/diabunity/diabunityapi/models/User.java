package com.diabunity.diabunityapi.models;

import com.diabunity.diabunityapi.utils.DiabetesType;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class User {

  @Id
  private String id;

  @Field
  private String name;

  @Field
  private String mail;

  @Field
  private Integer age;

  @Field
  private DiabetesType type;

  @Field
  private Double weight;

  @Field
  private Double height;

  @Field
  private Date birthDate;

  @Field
  private boolean onboarding;

  public User(String id, String name, String mail,
              Integer age, DiabetesType type, Double weight,
              Double height, Date birthDate, boolean onboarding) {
    this.id = id;
    this.name = name;
    this.mail = mail;
    this.age = age;
    this.type = type;
    this.weight = weight;
    this.height = height;
    this.birthDate = birthDate;
    this.onboarding = onboarding;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMail() { return mail; }

  public void setMail(String mail) { this.mail = mail; }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public DiabetesType getType() {
    return type;
  }

  public void setType(DiabetesType type) {
    this.type = type;
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

  public boolean isOnboarding() {
    return onboarding;
  }

  public void setOnboarding(boolean onboarding) {
    this.onboarding = onboarding;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Double getHeight() {
    return height;
  }

  public void setHeight(Double height) {
    this.height = height;
  }

}
