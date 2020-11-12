package com.training.spring.controller;

import com.training.spring.service.MatchService;
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

}
