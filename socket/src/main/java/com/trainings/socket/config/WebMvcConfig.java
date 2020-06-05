package com.trainings.socket.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Wangjunwei
 * @Date 2020/5/28 17:19
 * @Description
 */
public class WebMvcConfig implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/**");
  }


  /**
   * /**的意思是所有文件，包括文件夹中的子文件
   * /*是所有文件，不包含子文件
   * /是web项目的根目录
   * 这里是在用户访问根目录时，跳转到/index下
   * @param registry
   */
  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("index");
  }

}
