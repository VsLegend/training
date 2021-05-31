package com.training.datastructure.reflect;

import lombok.Data;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @User: wong
 * @Date: 2021/3/2
 * @Description: 字段反射
 */

@Data
public class FieldReflectDemo {

  // 私有 不可变 常量池中的类
  private final String constantStr = "FinalConstantStringField";
  // JVM优化了getter方法，直接将对constantStr引用全部替换成了常量
  //  public String getConstantStr() {return "FinalConstantStringField";}

  // 引用地址中，复制了常量池的值
  private final String newStr = new String("FinalNewStringField");

  public FieldReflectDemo(){}

  public static void main(String[] args) {
    FieldReflectDemo fieldReflectDemo = new FieldReflectDemo();
    try {
      Class<?> className = fieldReflectDemo.getClass();
      Field constantStr = className.getDeclaredField("constantStr");
      Field newStr = className.getDeclaredField("newStr");
      // 获取实例对象的字段值
      System.out.println("constantStr原：" + constantStr.get(fieldReflectDemo));
      System.out.println("newStr原：" + newStr.get(fieldReflectDemo));
      constantStr.setAccessible(true);
      newStr.setAccessible(true);
      constantStr.set(fieldReflectDemo, "New Filed Name");
      newStr.set(fieldReflectDemo, "New Filed Name");
      System.out.println("constantStr反射修改：" + constantStr.get(fieldReflectDemo));
      System.out.println("newStr反射修改：" + newStr.get(fieldReflectDemo));
    } catch (NoSuchFieldException | IllegalAccessException e) {
      e.printStackTrace();
    }
//    System.out.println("constantStr实例对象值：" + fieldReflectDemo.getConstantStr());
//    System.out.println("newStr实例对象值：" + fieldReflectDemo.getNewStr());
  }

  /**
   * 输出
   * constantStr原：FinalConstantStringField
   * newStr原：FinalNewStringField
   * constantStr反射修改：New Filed Name
   * newStr反射修改：New Filed Name
   * constantStr实例对象值：FinalConstantStringField
   * newStr实例对象值：New Filed Name
   */
}
