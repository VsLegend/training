package com.training.spring.config.bean;

import com.training.spring.service.MatchService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @User: wong
 * @Date: 2020/12/4
 * @Description: 生成非单例bean, 每次获取bean时，其都不是同一个实例
 */
public class BeanManager implements ApplicationContextAware {

  private ApplicationContext applicationContext;

  protected MatchService getBeans(String name) {
    // 通过改方法，每次都将获取一个新得实例bean（相当于prototype）
    return applicationContext.getBean(name, MatchService.class);
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext  = applicationContext;
  }
}
