package com.training.spring.service.impl;

import com.training.spring.service.MatchService;
import org.springframework.stereotype.Service;

/**
 * @User: wong
 * @Date: 2020/9/1
 * @Description:
 */

@Service(value = "dog")
public class DogServiceImpl implements MatchService {

  @Override
  public String getName() {
    return "Dog";
  }
}
