package com.training.java8stream.java8.funInterface;

/**
 * @User: Administrator
 * @Time: 2021/5/11
 * @Description: 描述
 *
 * JDK 1.8 之前已有的函数式接口:
 * @see java.lang.Runnable
 * @see java.util.concurrent.Callable
 * @see java.security.PrivilegedAction
 * @see java.util.Comparator
 * @see java.io.FileFilter
 * @see java.nio.file.PathMatcher
 * @see java.lang.reflect.InvocationHandler
 * @see java.beans.PropertyChangeListener
 * @see java.awt.event.ActionListener
 * @see javax.swing.event.ChangeListener
 *
 *  JDK 1.8 新增加的函数接口：
 * @see java.util.function
 */

// 标明该接口为函数式接口，即有且仅有一个抽象方法，但是可以有多个非抽象方法的接口。
@FunctionalInterface
public interface FunctionalInterfaceTest<T> {

    void print(T a);

}
