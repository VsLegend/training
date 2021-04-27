package com.training.spring.config.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

/**
 * @User: wong
 * @Date: 2020/12/15
 * @Description: 自定义bean scope
 * 需要继承如下类
 * @see org.springframework.beans.factory.config.Scope
 * 具体的实现方法可以查阅Scope的实现类
 */
public class ScopeConfig implements Scope {
  @Override
  public Object get(String name, ObjectFactory<?> objectFactory) {
    return null;
  }

  @Override
  public Object remove(String name) {
    return null;
  }

  @Override
  public void registerDestructionCallback(String name, Runnable callback) {

  }

  @Override
  public Object resolveContextualObject(String key) {
    return null;
  }

  @Override
  public String getConversationId() {
    return null;
  }
}
