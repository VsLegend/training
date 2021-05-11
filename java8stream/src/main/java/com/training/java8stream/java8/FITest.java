package com.training.java8stream.java8;

import com.training.java8stream.java8.funInterface.FunctionalInterfaceTest;

import java.util.Scanner;
import java.util.function.Predicate;

/**
 * @User: wong
 * @Time: 2021/5/11
 * @Description:
 */
public class FITest<T> {

    public static void main(String[] args) {
        // 判断输入的密码是否正确
        FITest<String> fiTest = new FITest<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入密码： ");
        String key = sc.next();
        fiTest.printIfTrue(key, k1 -> System.out.println("Right KEY! "), k2 -> k2.equals("KEY"));

        //
    }


    // 判断正确时打印输出
    public void printIfTrue(T t, FunctionalInterfaceTest<T> print, Predicate<T> predicate) {
        if (predicate.test(t)) {
            print.print(t);
        } else {
            System.err.println("密码错误！");
        }
    }

}
