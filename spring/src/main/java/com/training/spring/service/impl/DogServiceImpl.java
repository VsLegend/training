package com.training.spring.service.impl;

import com.training.spring.service.MatchService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * @User: wong
 * @Date: 2020/9/1
 * @Description:
 */

// singleton prototype
@Scope(value = "prototype") // 与 @Singleton 作用相同
@Service(value = "dog")
public class DogServiceImpl implements MatchService {

  @Override
  public String getName() {
    return "This is a Dog";
  }
}
