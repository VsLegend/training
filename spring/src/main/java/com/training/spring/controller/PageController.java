package com.training.spring.controller;

import com.training.spring.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wangjunwei
 * @Date 2020/5/28 16:49
 * @Description
 */

@RestController
@RequestMapping("/socket")
public class PageController {

//  @Inject
//  @Named(value = "dog")
//  MatchService matchService;
//
//  @Autowired
//  @Qualifier(value = "cat")
//  MatchService matchService;

//  @Resource
//  @Named(value = "dog")
//  @Qualifier(value = "cat")
//  MatchService matchService;


  @GetMapping("/matchAnimal")
  public String matchAnimal() {
    return matchService.getName();
  }

}
