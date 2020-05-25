package com.training.remotefeign.controller;

import com.training.common.common.Result;
import com.training.common.po.Company;
import com.training.remotefeign.feign.RemoteService;
import io.swagger.annotations.Api;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wangjunwei
 * @Date 2020/5/20 14:25
 * @Description
 */

@Api(tags = "远程接口测试")
@RestController
@RequestMapping("/offer")
public class RemoteController {

  @Resource
  private RemoteService remoteService;

  @GetMapping("/getJobListAuth")
  public Result getJobListAuth() {
    Map<String, Object> header = new HashMap<>();
    return Result.success(remoteService.getJobListAuth(header));
  }

  @GetMapping("/getJobList")
  public Result getJobList() {
    return Result.success(remoteService.getJobList());
  }

  @GetMapping("/getJob/{name}")
  public Result getJob(@PathVariable("name") String name) {
    return Result.success(remoteService.getJob(name));
  }

  @GetMapping("/getJob/{remain}")
  public Result getJobParam(@PathVariable("remain") String name) {
    return Result.success(remoteService.getJobParam(name));
  }


  @PostMapping("/getRemainMap")
  public Result getRemainMap() {
    // 请求参数为map
    Map<String, Object> map = new HashMap<>();
    return Result.success(remoteService.getRemainMap(map));
  }


  @PostMapping("/getRemainBean")
  public Result getRemain(@RequestBody Company company) {
    // 请求参数为对象
    return Result.success(company);
  }

  @GetMapping("/returnMap")
  public Result returnMap() {
    // 返回参数为map
    Map map = remoteService.returnMap();
    map.entrySet().forEach(m -> {
      System.out.println(map.get(m));
    });
    return Result.success(map);
  }


  @GetMapping("/returnBean")
  public Result returnBean() {
    // 返回参数为对象
    Company company = remoteService.returnBean();
    System.out.println(company);
    return Result.success(company);
  }
}
