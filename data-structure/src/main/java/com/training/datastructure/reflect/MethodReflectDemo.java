package com.training.datastructure.reflect;

import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

/**
 * @User: wong
 * @Date: 2021/3/3
 * @Description: 反射方法获取
 */
public class MethodReflectDemo {

  public MethodReflectDemo() {}

  private void getNothing(String name) { }

  public int getNumByName(String name) throws NullPointerException {
    if (StringUtils.isEmpty(name))
      throw new NullPointerException("名字为空");
    return name.length();
  }

  public static void main(String[] args) {
    MethodReflectDemo methodReflectDemo = new MethodReflectDemo();
    try {
      Class<? extends MethodReflectDemo> demoClass = methodReflectDemo.getClass();
      Method method = demoClass.getDeclaredMethod("getNumByName", String.class);
      String name = method.getName();
      System.out.println("方法名：" + name);
      // 修饰符
      int modifiers = method.getModifiers();
      System.out.println("所有修饰符：" + Modifier.toString(modifiers));
      // 参数
      Parameter[] parameters = method.getParameters();
      // 返回类型
      Class<?> returnType = method.getReturnType();
      System.out.println("返回类型：" + returnType.getTypeName());
      // 异常
      Class<?>[] exceptionTypes = method.getExceptionTypes();
      System.out.println("");
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    }
  }

}
