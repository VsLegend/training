package com.training.redismongodb.controller;

import io.swagger.annotations.Api;
import javax.annotation.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wangjunwei
 * @Date 2019/9/30 10:48
 * @Description
 */

@RestController
@Api(tags = "mongodb 测试")
public class MongodbController {

  @Resource
  private MongoTemplate mongoTemplate;


}
