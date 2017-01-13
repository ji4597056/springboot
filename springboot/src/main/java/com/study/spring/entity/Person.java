package com.study.spring.entity;

/**
 * @author Jeffrey
 * @since 2017/1/9 15:47
 */
public class Person {

  private int id;

  private String name;

  public Person() {}

  public Person(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
