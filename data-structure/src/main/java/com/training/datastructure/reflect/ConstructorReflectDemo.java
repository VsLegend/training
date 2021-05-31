package com.training.datastructure.reflect;

import org.springframework.util.StringUtils;

import java.lang.reflect.*;

/**
 * @User: wong
 * @Date: 2021/3/3
 * @Description: 反射方法获取
 */
public class ConstructorReflectDemo {

  public ConstructorReflectDemo() {}

  private void getNothing(String name) { }

  public int getNumByName(String name) throws NullPointerException {
    if (StringUtils.isEmpty(name))
      throw new NullPointerException("名字为空");
    return name.length();
  }

  public static void main(String[] args) {
    ConstructorReflectDemo methodReflectDemo = new ConstructorReflectDemo();
    try {
      Class<? extends ConstructorReflectDemo> demoClass = methodReflectDemo.getClass();
      Constructor<? extends ConstructorReflectDemo> constructor = demoClass.getConstructor();
      String name = constructor.getName();
      System.out.println("构造方法名：" + name);
      // 修饰符
      int modifiers = constructor.getModifiers();
      System.out.println("所有修饰符：" + Modifier.toString(modifiers));
      // 参数
      Parameter[] parameters = constructor.getParameters();
      // 异常
      Class<?>[] exceptionTypes = constructor.getExceptionTypes();
      System.out.println("");
      // 构造方法无法被回调，只可以创建新实例
      ConstructorReflectDemo constructorReflectDemo = constructor.newInstance();
    } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
      e.printStackTrace();
    }
  }

}
