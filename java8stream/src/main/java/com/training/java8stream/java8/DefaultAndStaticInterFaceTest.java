package com.training.java8stream.java8;

import com.training.java8stream.java8.funInterface.DefaultAndStaticInterFace;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @User: wong
 * @Time: 2021/5/11
 * @Description: 接口默认方法 | 接口静态方法
 *
 * 接口默认方法、静态方法都可以有多个。
 * 默认方法通过实例调用，静态方法通过接口名调用。
 * 接口默认方法用default修饰，接口静态方法用static修饰。default默认方法关键字只能用在接口中。
 * 默认方法可以被继承，如果继承了多个接口，多个接口都定义了多个同样的默认方法，实现类需要重写默认方法不然会报错。
 * 静态方法不能被继承及覆盖，所以只被具体所在的接口调用。
 */
public class DefaultAndStaticInterFaceTest<T> implements DefaultAndStaticInterFace<T> {


    @Override
    public void normalMethod(T t, Predicate<T> predicate, Consumer<T> consumer) {
        if (predicate.test(t)) {
            consumer.accept(t);
        }
    }

    // 可以不进行重载
    @Override
    public void defaultMethod(T t, Consumer<T> consumer) {
        consumer.accept(t);
        System.out.println("This is a default method!");
    }

}
