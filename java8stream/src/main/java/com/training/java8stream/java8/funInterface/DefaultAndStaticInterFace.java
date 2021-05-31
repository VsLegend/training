package com.training.java8stream.java8.funInterface;


import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @User: wong
 * @Time: 2021/5/11
 * @Description: 函数式接口定义
 * 接口默认方法 | 接口静态方法
 */

@FunctionalInterface
public interface DefaultAndStaticInterFace<T> {

    void normalMethod(T t, Predicate<T> predicate, Consumer<T> consumer);

    default void defaultMethod(T t, Consumer<T> consumer) {
        consumer.accept(t);
        System.out.println("This is a default method!");
    }

    static <T> void staticMethod(T t, Consumer<T> consumer) {
        consumer.accept(t);
        System.out.println("This is a static method!");
    }

}
