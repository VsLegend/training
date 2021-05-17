package com.training.java8stream.java8;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @User: Administrator
 * @Time: 2021/5/12
 * @Description:
 */

public class MethodReferenceTest2 {


    public static void main(String... args) {
        List<Person> personList = Person.generatePerson();
        for (Person person : personList) {
            // 静态方法
            person.updateFlag(Person::isRightBirthStatic);
            // 普通方法
            person.updateFlag(person::isRightBirth);


            System.out.println(person.getName() + "检验出身日期结果：" + person.flag);
        }
    }


}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class Person {

    String name;

    int age;

    LocalDate birthday;

    boolean flag;


    public void updateFlag(Predicate<Person> predicate) {
        flag = predicate.test(this);
    }


    // 构造函数
    Person(String name) {
        this.name  = name;
        age = 0;
        birthday = LocalDate.now();
        flag = false;
    }

    // 静态方法
    public static boolean isRightBirthStatic(Person person) {
        return person.getBirthday().minusYears(person.getAge()).getYear() == person.getBirthday().getYear() ;
    }

    public String getNameAndAge(String name, String age) {
        return name + age;
    }

    // 普通方法
    public boolean isRightBirth(Person person) {
        return person.getBirthday().minusYears(person.getAge()).getYear() == person.getBirthday().getYear() ;
    }


    public static List<Person> generatePerson() {
        return Arrays.asList(new Person("Alice", 18, LocalDate.of(1997, 4, 12), true),
                new Person("Bob", 18, LocalDate.of(1997, 8, 25), true),
                new Person("Joly", 18, LocalDate.of(1997, 9, 1), true)
        );
    }

}