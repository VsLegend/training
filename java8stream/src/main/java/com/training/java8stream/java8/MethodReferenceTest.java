package com.training.java8stream.java8;

import com.sun.istack.internal.NotNull;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @User: wong
 * @Time: 2021/5/11
 * @Description: 方法引用使用实例
 *
 * 方法引用，是指如果某个方法签名和接口恰好一致，就可以直接传入方法引用。
 * 因为Comparator<String>接口定义的方法是int compare(String, String)，和静态方法int combine(String, String)相比，除了方法名外，方法参数一致，返回类型相同，
 * 因此，我们说两者的方法签名一致，可以直接把方法名作为Lambda表达式传入
 *
 * 方法签名只看参数类型和返回类型，不看方法名称，也不看类的继承关系。
 */
public class MethodReferenceTest {

    private String name;

    public static void main(String[] args) {
        // 比较排序
        String[] array = new String[] { "Apple", "Orange", "Banana", "Lemon" };
        Arrays.sort(array, MethodReferenceTest::compare);
        Arrays.sort(array, String::compareTo);
        Arrays.sort(array, Comparator.comparingInt(String::hashCode));
        System.out.println(String.join(", ", array));

        List<String> fruit = Arrays.asList(array);
        // 方法引用
        /**
         * collect的三个参数
         * @param Supplier<R> 创建新容器R 由于stream会并行执行以增加执行速度，要求该容器能多次执行且每次执行都生成新的容器
         * @param BiConsumer<R, ? super T> 将元素(? super T)放入容器中，该元素必须是调用stream的容器元素的实现类或子类，如下是String类
         * @param BiConsumer<R, R> 将多个容器合并为一个容器
         */
        List<MethodReferenceTest> referenceTests = fruit.stream().collect((Supplier<List<MethodReferenceTest>>) ArrayList::new,
                MethodReferenceTest::createInstance, List::addAll);

        // 对象传递，构造函数使用方法引用
        List<MethodReferenceTest> collect = fruit.stream().map(MethodReferenceTest::new).collect(Collectors.toList());
        String reduce = collect.stream().map(MethodReferenceTest::getName).collect(Collectors.joining(" "));
        String reduce2 = collect.stream().map(MethodReferenceTest::getName).reduce("", MethodReferenceTest::combine);
        System.out.println(reduce);
        System.out.println(reduce2);
        System.out.println("Bob".concat(" Cale"));
    }

    public static void createInstance(List<MethodReferenceTest> list, String name) {
        list.add(new MethodReferenceTest(name));
    }

    public static Integer compare(String a, String b) {
        return a.compareTo(b);
    }

    public static String combine(String a, String b) {
        return a.concat(b + " ");
    }

    MethodReferenceTest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
