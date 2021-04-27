package com.training.spring.config.scope;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.WebApplicationContext;

/**
 * @User: wong
 * @Date: 2020/12/15
 * @Description:
 */
public class ScopeRegiter{

  @Bean
  public void regiterScope(ConfigurableListableBeanFactory beanFactory) {
    beanFactory.registerScope(WebApplicationContext.SCOPE_REQUEST, new ScopeConfig());
  }

}
