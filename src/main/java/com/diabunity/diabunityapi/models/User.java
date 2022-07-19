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
  private String password;

  @Field
  private int age;

  @Field
  private DiabetesType type;

  @Field
  private int Weight;

  @Field
  private Date birthDate;

  public User(String id, String name, String mail, String password, int age, DiabetesType type, int weight, Date birthDate) {
    this.id = id;
    this.name = name;
    this.mail = mail;
    this.password = password;
    this.age = age;
    this.type = type;
    Weight = weight;
    this.birthDate = birthDate;
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

  public String getPassword() { return password; }

  public void setPassword(String password) {
    this.password = password;
  }

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

  public int getWeight() {
    return Weight;
  }

  public void setWeight(int weight) {
    Weight = weight;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }
}
