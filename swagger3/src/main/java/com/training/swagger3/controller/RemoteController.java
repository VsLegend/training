package com.training.swagger3.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wangjunwei
 * @Date 2020/5/20 14:25
 * @Description
 */

@Api(tags = "测试API文档描述")
@RestController
@RequestMapping("/offer")
public class RemoteController {


  private final Map<String, String> companyList = new HashMap<>();

  RemoteController() {
    companyList.put("TX", "国际盗版公司 缺Java 8人");
    companyList.put("ALBB996", "代言公司 缺Python 12人");
    companyList.put("Bai", "恰烂钱公司 缺莆田系类医生 99人");
    companyList.put("Googl", "全球垄断公司 缺外包 100000人");
    companyList.put("bagbag", "二刺螈 10000人");
  }

  @ApiOperation(value = "获取数据API")
  @GetMapping("/getJobList")
  public Object getJobList() {
    return companyList;
  }

}
