package com.training.spring.controller;

import com.training.spring.config.bean.BeanConfiguration;
import com.training.spring.service.MatchService;
import com.training.spring.service.impl.CatServiceImpl;
import com.training.spring.service.impl.DogServiceImpl;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.inject.Named;

/**
 * @author Wangjunwei
 * @Date 2020/5/28 16:49
 * @Description 注入的三种方式
 */

@RestController
@RequestMapping("/inject")
public class InjectController {

//  @Inject
//  @Named(value = "dog")
//  MatchService matchService;
//
//  @Autowired
//  @Qualifier(value = "cat")
//  MatchService matchService;

  @Resource
  @Named(value = "dog")
//  @Qualifier(value = "cat")
  MatchService matchService;


  @PostMapping("/matchAnimal")
  public String matchAnimal() {
    return matchService.getName();
  }

  public static void main(String[] args) {
//    BeanFactory beanFactory = new XmlBeanDefinitionReader();
    ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(BeanConfiguration.class);
    CatServiceImpl cat = context.getBean("cat", CatServiceImpl.class);
    ConfigurableApplicationContext applicationContext  = new ClassPathXmlApplicationContext("classpath:application-dev.xml");
    System.out.println("上下文启动，bean加载中");
    MatchService dog = applicationContext.getBean("dog", DogServiceImpl.class);
//    CatServiceImpl cat = applicationContext.getBean("cat", CatServiceImpl.class);
//    System.out.println(dog.getName());
//    System.out.println(cat.getName());
//    ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
//    BeanDefinition beanDefinition = beanFactory.getBeanDefinition("dog");
//    System.out.println(beanDefinition.getBeanClassName());
    applicationContext.close();
  }

}
