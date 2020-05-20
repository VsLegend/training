package com.training.java8stream.service;

import com.training.java8stream.domain.Student;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Wangjunwei
 * @Date 2019/9/19 11:14
 * @Description
 */
public class UseStream8 {

  public static void main(String[] args) {
    List<Student> students = new ArrayList<>();
    students.add(new Student("阿三", "56", "18"));
    students.add(new Student("巴西", "34", "7"));
    students.add(new Student("霓虹", "23", "14"));
    students.add(new Student("弯弯", "77", "12"));
    students.add(new Student("老米", "14", "19"));
    students.add(new Student("杂技", "16", "20"));
    students.add(new Student("天天", "17", "20"));
    List<String> studentName = students.stream()
        .filter(student -> Integer.parseInt(student.getLength()) > 15)
        .sorted(Comparator.comparing(Student::getLength).reversed()
            .thenComparing(Comparator.comparing(Student::getAge).reversed()))
        .map(Student::getName)
        .collect(Collectors.toList());
    List<Student> collect = students.stream()
        .filter(student -> Integer.parseInt(student.getLength()) > 15)
        .sorted(Comparator.comparing(Student::getLength).reversed()
            .thenComparing(Comparator.comparing(Student::getAge).reversed()))
        .collect(Collectors.toList());
    for (String s : studentName) {
      System.out.println("---" + s);
    }
    for (Student s : collect) {
      System.out.print(s.getName() + "---");
      System.out.print(s.getLength() + "---");
      System.out.print(s.getAge() + "---");
      System.out.println("");
    }
  }

}
