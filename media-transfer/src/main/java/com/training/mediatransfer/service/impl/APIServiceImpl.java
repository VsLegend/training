package com.training.mediatransfer.service.impl;

import com.training.mediatransfer.domain.Result;
import com.training.mediatransfer.service.APIService;
import org.springframework.stereotype.Service;

/**
 * @author Wangjunwei
 * @Date 2019/10/17 18:10
 * @Description 降级处理
 */

@Service
public class APIServiceImpl implements APIService {

  @Override
  public Result get(String page) {
    return new Result();
  }
}
