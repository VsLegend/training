package com.training.java8stream.java8;

import java.text.DateFormat;
import java.util.StringJoiner;

/**
 * @User: wong
 * @Date: 2021/4/25
 * @Description: jdk8 StringJoiner 使用
 */
public class StringJoinerTest {

  public static void main(String[] args) {
    // 分隔符 前缀 后缀
    StringJoiner stringJoiner = new StringJoiner(",", "[", "]");
    stringJoiner.add("Jon").add("Kay").add("Moy");
    System.out.println(stringJoiner.toString());
  }

}
