package com.training.remoteapi.controller;

import com.training.common.common.AppException;
import com.training.common.common.Result;
import com.training.common.enums.ErrorCode;
import com.training.remoteapi.domain.Company;
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
  public Result getJobListAuth(HttpServletRequest request) {
    // 请求头
    String authorization = request.getHeader("Authorization");
    if (StringUtils.isEmpty(authorization)) {
      throw new AppException(ErrorCode.ACCESS_DENIED);
    }
    return Result.success(companyList);
  }

  @GetMapping("/getJobList")
  public Result getJobList() {
    return Result.success(companyList);
  }

  @GetMapping("/getJob/{name}")
  public Result getJob(@PathVariable("name") String name) {
    return Result.success(companyList.get(name));
  }

  @GetMapping("/getJob")
  public Result getJobParam(@RequestParam("remain") String name) {
    return Result.success(companyList.get(name));
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
    // 请求参数为对象
    return Result
        .success(
            companyList.get(company.getIndex()).contains(company.getPeople().toString())
                ? companyList
                .get(company.getIndex()) : "公司已满员。");
  }

  @GetMapping("/returnMap")
  public Result returnMap() {
    // 返回参数为map
    return Result.success(companyList);
  }


  @GetMapping("/returnBean")
  public Result returnBean() {
    // 返回参数为对象
    return Result.success(
        Company.builder().index("Googl全球垄断公司").remain(companyList.get("Googl全球垄断公司")).build());
  }
}
