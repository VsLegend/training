package com.training.java8stream.java8;

import com.training.java8stream.domain.Student;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Wangjunwei
 * @Date 2019/9/19 11:14
 * @Description
 */
public class UseStream8 {

  public static void main(String[] args) {
//    Arrays.asList(T... a)
    List<Student> students = Stream.of(new Student("阿三", "56", "18")
        , new Student("巴西", "34", "7")
        , new Student("霓虹", "23", "14")
        , new Student("弯弯", "77", "12")
        , new Student("老米", "14", "19")
        , new Student("杂技", "16", "20")
        , new Student("天天", "17", "20")
    ).collect(Collectors.toList());

    // filter(Predicate<? super T>) 过滤数据 sorted(Comparator<? super T>) 排序
    // map(Function<? super T, ? extends R>) 映射内容 collect(Collector<? super T, A, R>)映射成集合 distinct去重
    List<String> studentName = students.stream()
        .filter(student -> Integer.parseInt(student.getLength()) > 15)
        .sorted(Comparator.comparing(Student::getLength).reversed()
            .thenComparing(Comparator.comparing(Student::getAge).reversed()))
        .map(Student::getName)
//        .distinct()
        .collect(Collectors.toList());
    List<Student> collect = students.stream()
        .filter(student -> Integer.parseInt(student.getLength()) > 15)
        .sorted(Comparator.comparing(Student::getLength).reversed()
            .thenComparing(Comparator.comparing(Student::getAge).reversed()))
        .collect(Collectors.toList());

    studentName.forEach(s -> System.out.print(s + " "));
    System.out.println("\n-----------------------------------");
    collect.forEach(c -> {
      System.out.print(c.getName() + " ");
      System.out.print(c.getAge() + " ");
      System.out.println(c.getLength());
    });
  }

}
