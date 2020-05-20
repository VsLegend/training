package com.training.java8stream.domain;

import lombok.Data;

/**
 * @author Wangjunwei
 * @Date 2019/9/19 11:15
 * @Description
 */

@Data
public class Student {

  private String name;

  private String age;

  private String length;

  public Student(String name, String age, String length) {
    this.name = name;
    this.age = age;
    this.length = length;
  }

}
