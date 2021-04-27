package com.training.remotefeign.controller;

import com.training.common.common.Result;
import com.training.common.po.Company;
import com.training.remotefeign.feign.RemoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Wangjunwei
 * @Date 2020/5/20 14:25
 * @Description
 */

@Api(tags = "远程接口测试")
@RestController
@RequestMapping("/offer")
public class RemoteController {

  // feign.codec.EncodeException: Content-Type cannot contain wildcard type '*'
  @Resource
  private RemoteService remoteService;

  @GetMapping("/getJobListAuth")
  @ApiOperation(value = "设置header请求头参数")
  public Result getJobListAuth() {
    Map<String, Object> header = new HashMap<>();
    // 假设这里是传递token
    header.put("Authorization", "user_access");
    return Result.success(remoteService.getJobListAuth(header));
  }

  @GetMapping("/getJobList")
  @ApiOperation(value = "GET请求")
  public Result getJobList() {
    return Result.success(remoteService.getJobList());
  }

  @GetMapping("/getJob/{name}")
  @ApiOperation(value = "/getJob/{name} 链接参数请求")
  public Result getJob(@PathVariable("name") String name) {
    return Result.success(remoteService.getJob(name));
  }


  @PostMapping("/getJobByMap")
  @ApiOperation(value = "请求参数为map")
  public Result getJobByMap() {
    Map<String, Object> map = new HashMap<>();
    return Result.success(remoteService.getJobByMap(map));
  }


  @PostMapping("/getJobByBean")
  @ApiOperation(value = "请求参数为对象")
  public Result<String> getJobByBean(@RequestBody Company company) {
    // 需保证请求方与接收方对象的字段以及字段属性相同
    company.setTime(null);
    System.out.println(company.getTime());
    return Result.success(remoteService.getJobByBean(company));
  }

  @GetMapping("/returnMap")
  @ApiOperation(value = "返回参数为map")
  public Result<Map> returnMap() {
    Map map = remoteService.returnMap();
    System.out.println("返回map： " + map);
    return Result.success(map);
  }


  @GetMapping("/returnBean")
  @ApiOperation(value = "返回参数为对象")
  public Result<Company> returnBean() {
    Company company = remoteService.returnBean();
    System.out.println("返回对象： " + company);
    return Result.success(company);
  }
}
