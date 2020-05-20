package com.training.redismongodb.controller;

import com.training.redismongodb.domain.RedisData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
  public String insert(@RequestBody RedisData redisData) {
    // key相同时，会进行覆盖操作
    redisTemplate.opsForValue().set(redisData.getKey(), redisData.getValue());
    return "==================insert success================";
  }

  @DeleteMapping("/delete/{key}")
  @ApiOperation("删除键值对")
  public Object delete(@PathVariable("key") String key) {
    RedisOperations<String, Object> operations = redisTemplate.opsForValue().getOperations();
    operations.delete(key);
    return "==================delete success================";
  }

  @GetMapping("/getValue/{key}")
  @ApiOperation("读取值")
  public Object getValue(@PathVariable("key") String key) {
    return redisTemplate.opsForValue().get(key);
  }

  @PostMapping("/getValueList")
  @ApiOperation("批量获取值")
  public Object getValue(@RequestBody List<String> keys) {
    List<Object> objectList = redisTemplate.opsForValue().multiGet(keys);
    return objectList;
  }

  @PostMapping("/insertList")
  @ApiOperation(value = "批量初始化键值对")
  public String insertList(@RequestBody HashMap map) {
    redisTemplate.opsForValue().multiSet(map);
    return "==================insert success================";
  }

}
