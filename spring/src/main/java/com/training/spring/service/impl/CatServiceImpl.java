package com.training.spring.service.impl;

import com.training.spring.service.MatchService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerMapping;

import java.util.List;

/**
 * @User: wong
 * @Date: 2020/9/1
 * @Description:
 */

@Service(value = "cat")
public class CatServiceImpl implements MatchService {

  @Override
  public String getName() {
    DispatcherServlet dispatcherServlet = new DispatcherServlet();
    List<HandlerMapping> handlerMappings = dispatcherServlet.getHandlerMappings();
    return "This is a Cat";
  }
}
