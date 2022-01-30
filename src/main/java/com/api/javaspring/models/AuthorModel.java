package com.api.javaspring.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class AuthorModel {
  @JsonProperty("name")
  public String name;
  @JsonProperty("lastName")
  public String lastName;
  @JsonProperty("age")
  public Integer age;

  public AuthorModel() {
    super();
  }

  public AuthorModel(String name, String lastName, Integer age) {
    this.name = name;
    this.lastName = lastName;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "AuthorModel{" +
            "name='" + name + '\'' +
            ", lastName='" + lastName + '\'' +
            ", age=" + age +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AuthorModel that = (AuthorModel) o;
    return Objects.equals(name, that.name) && Objects.equals(lastName, that.lastName) && Objects.equals(age, that.age);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, lastName, age);
  }
}
