package com.training.common.common;

/**
 * @User: wong
 * @Date: 2021/4/23
 * @Description:
 */
public class SuperClass {

  SuperClass() {
    System.out.println("父类无参构造函数------完成初始化");
  }

  SuperClass(String s) {
    System.out.println("父类有参构造函数------完成初始化");
  }

  void getMessage() {
    System.out.println("父类函数调用");
  }

  public static void main(String[] args) {
    SubClass subClass = new SubClass();
    subClass.getMessage();
    System.out.println("--------------------------");
    SubClass subClass1 = new SubClass("s");
  }
}

class SubClass extends SuperClass {

  SubClass() {
    System.out.println("子类无参构造函数------完成初始化");
  }

  SubClass(String s) {
    super(s);
    System.out.println("子类有参构造函数------完成初始化");
  }

}