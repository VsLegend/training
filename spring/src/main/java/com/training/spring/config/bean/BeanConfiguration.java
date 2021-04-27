package com.training.spring.config.bean;

import com.training.spring.service.MatchService;
import com.training.spring.service.impl.CatServiceImpl;
import com.training.spring.service.impl.DogServiceImpl;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.List;

/**
 * @User: wong
 * @Date: 2020/12/3
 * @Description:
 */

//@Configuration
public class BeanConfiguration implements InitializingBean, DisposableBean {

  @Bean(name = "catNames")
  public List<String> getCatNames() {
    List<String> list = new ArrayList<>();
    list.add("cat 7");
    list.add("cat 12");
    list.add("cat 19");
    return list;
  }

  @DependsOn({"dog", "catNames"}) // 需要的依赖，用于保证DependsOn中的bean会在该bean创建之前创建
  @Bean(name = "cat")
  public MatchService getCat() {
    MatchService cat = new CatServiceImpl();
    return cat;
  }

  @Lazy //延时加载
  @Bean(name = "dog")
  public MatchService getDog() {
    MatchService dog = new DogServiceImpl();
    return dog;
  }

  // bean 初始化回调
  @Override
  public void afterPropertiesSet() throws Exception {

  }

  // bean 销毁回调
  @Override
  public void destroy() throws Exception {

  }
}
