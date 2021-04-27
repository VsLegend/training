package com.training.redismongodb.controller;

import com.training.redismongodb.domain.RedisData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Wangjunwei
 * @Date 2019/9/30 10:48
 * @Description
 */

@RestController
@Api(tags = "Redis 测试")
public class RedisController {

  @Resource
  private RedisTemplate<String, Object> redisTemplate;

  @PostMapping("/insert")
  @ApiOperation(value = "添加字符串类型的键值对")
  public void insert(@RequestBody RedisData redisData) {
    // key相同时，会进行覆盖操作
  }
}
