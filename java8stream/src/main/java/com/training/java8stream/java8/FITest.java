package com.training.java8stream.java8;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @User: wong
 * @Time: 2021/5/11
 * @Description:
 */
public class FITest<T, R> {

    public static void main(String[] args) {
        // 判断输入的密码是否正确
        FITest<String, String> fiTest = new FITest<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入密码： ");
        String key = sc.next();
        String result = fiTest.getIfTrue(key, // 输入数据
                k1 -> k1 + " Is Right KEY! ", // 正确时的回调方法
                k2 -> k2.equals("KEY") // 判断条件
        );
        ArrayList<String> strings = new ArrayList<>();
        System.out.println(result);
    }


    // 判断正确时打印输出
    public R getIfTrue(T t, Function<T, R> function, Predicate<T> predicate) {
        if (predicate.test(t)) {
            return function.apply(t);
        } else {
            System.err.println("密码错误！");
        }
        return null;
    }

}
