package com.training.java8stream.java8;

import com.training.java8stream.domain.Student;


import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
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
    // 找到列表的第一个以及最后一个元素
    String first = students.stream().map(Student::getName).findFirst().orElse(null);
    String last = students.stream().map(Student::getName).skip(students.size() - 1).reduce((a, b) -> b).orElse(null);
    System.out.println("First" + first);
    System.out.println("Last" + last);

    // filter(Predicate<? super T>) 过滤数据
    // sorted(Comparator<? super T>) 排序
    // map(Function<? super T, ? extends R>) 映射内容
    // collect(Collector<? super T, A, R>)映射成集合
    // distinct去重
    List<String> studentName = students.stream()
            // filter匿名函数的代码实现
            .filter(student -> Integer.parseInt(student.getLength()) > 15)
            .filter(new Predicate<Student>() {
              @Override
              public boolean test(Student student) {
                return Integer.parseInt(student.getLength()) > 15;
              }
            })
            .sorted(Comparator.comparing(Student::getLength).reversed()
                    .thenComparing(Comparator.comparing(Student::getAge).reversed()))
            // Lambda表达式，以下三种map功能都相同
            // Lambda表达式包括三部分：输入、函数体、输出
//            .map(s -> s.getName())
            .map(Student::getName)
            // 不用Lambda表达式
//            .map(new Function<Student, String>() {
//              @Override
//              public String apply(Student s) {
//                return s.getName();
//              }
//            })
//            .distinct()
            .collect(Collectors.toList());
    List<Student> collect = students.stream()
            .filter(student -> Integer.parseInt(student.getLength()) > 15)
            .sorted(Comparator.comparing(Student::getLength).reversed()
                    .thenComparing(Comparator.comparing(Student::getAge).reversed()))
            .collect(Collectors.toList());
    // Reduce 作用
    String names = students.stream().map(Student::getName).reduce("", (s, b) -> s + b);
    // OR
    String names1 = students.stream().map(Student::getName).reduce("", String::concat);
    System.out.println(names);
    System.out.println(names1);
    studentName.forEach(s -> System.out.print(s + " "));
    System.out.println("\n-----------------------------------");
    collect.forEach(c -> {
      System.out.print(c.getName() + " ");
      System.out.print(c.getAge() + " ");
      System.out.println(c.getLength());
    });

    // group by
    Map<String, String> map = students.stream().collect(
            Collectors.groupingBy(Student::getName, Collectors.reducing("0", Student::getLength, Student::sumLength))
    );
    map.forEach((key, value) -> System.out.println("=========" + key + ":" + value));
  }

}
