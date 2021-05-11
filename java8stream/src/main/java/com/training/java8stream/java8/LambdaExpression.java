package com.training.java8stream.java8;

/**
 * @User: Administrator
 * @Time: 2021/5/11
 * @Description: lambda 表达式实例
 */
public class LambdaExpression {


    public static void main(String[] args) {
        LambdaExpression tester = new LambdaExpression();

        // 类型声明
        MathOperation addition = (int a, int b) -> a + b;

        // 不用类型声明
        MathOperation subtraction = (a, b) -> a - b;

        // 大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> {
            return a * b;
        };

        // 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;

        System.out.println("1 + 5 = " + tester.operate(1, 5, addition));
        System.out.println("12 - 3 = " + tester.operate(12, 3, subtraction));
        System.out.println("20 x 7 = " + tester.operate(20, 7, multiplication));
        System.out.println("50 / 5 = " + tester.operate(50, 5, division));

        // 不用括号
        GreetingService greetService1 = message ->
                System.out.println("Hello " + message);

        // 用括号
        GreetingService greetService2 = (message) ->
                System.out.println("Hello " + message);

        greetService1.sayMessage("Runoob");
        greetService2.sayMessage("Google");
    }

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

}
