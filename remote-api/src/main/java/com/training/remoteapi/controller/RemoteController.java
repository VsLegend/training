package com.training.remoteapi.controller;

import com.training.common.common.AppException;
import com.training.common.common.Result;
import com.training.common.enums.ErrorCode;
import com.training.common.po.Company;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wangjunwei
 * @Date 2020/5/20 14:25
 * @Description
 */

@RestController
@RequestMapping("/offer")
public class RemoteController {


  private final Map<String, String> companyList = new HashMap<>();

  RemoteController() {
    companyList.put("TX国际盗版公司", "缺Java 8人");
    companyList.put("ALBB996代言公司", "缺Python 12人");
    companyList.put("Bai恰烂钱公司", "缺莆田系类医生 99人");
    companyList.put("Googl全球垄断公司", "缺外包 100000人");
  }


  @GetMapping("/getJobListAuth")
  public Object getJobListAuth(HttpServletRequest request) {
    // 请求头
    String authorization = request.getHeader("Authorization");
    if (StringUtils.isEmpty(authorization)) {
      throw new AppException(ErrorCode.ACCESS_DENIED);
    }
    return companyList;
  }

  @GetMapping("/getJobList")
  public Object getJobList() {
    return companyList;
  }

  @GetMapping("/getJob/{name}")
  public Object getJob(@PathVariable("name") String name) {
    return companyList.get(name);
  }

  @GetMapping("/getJob")
  public Object getJobParam(@RequestParam("name") String name) {
    return companyList.get(name);
  }


  @PostMapping("/getRemainMap")
  public Result getRemainMap(@RequestBody Map<String, String> map) {
    // 请求参数为map
    map.forEach((s, s2) -> {
      System.out.println("Key:" + s);
      System.out.println("Value:" + s2);
    });
    return Result.success();
  }


  @PostMapping("/getRemainBean")
  public Result getRemain(@RequestBody Company company) {
    System.out.println(company.toString());
    // 请求参数为对象
    return Result.success();
  }

  @GetMapping("/returnMap")
  public Map<String, String> returnMap() {
    // 返回参数为map
    return companyList;
  }


  @GetMapping("/returnBean")
  public Company returnBean() {
    // 返回参数为对象
    return Company.builder().name("BBLA996福报公司").marketValue("100, 000, 000, 000, 000$").people(120000).build();
  }
}
