package com.training.remotefeign.feign;

import com.training.common.common.Result;
import com.training.common.po.Company;
import com.training.remotefeign.config.FeignConfig;
import feign.HeaderMap;
import feign.Param;
import feign.RequestLine;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Wangjunwei
 * @Date 2020/5/21 20:01
 * @Description
 */

@FeignClient(value = "Remote", url = "http://localhost:7060/offer"
    , fallback = RemoteServiceHystrix.class, configuration = FeignConfig.class)
public interface RemoteService {

  @RequestLine("GET /getJobListAuth")
  Map getJobListAuth(@HeaderMap Map<String, ?> map);

  @RequestLine("GET /getJobList")
  Map getJobList();

  @RequestLine("GET /getJob/{name}")
  String getJob(@Param("name") String name);

  @RequestLine("GET /getJob?name={name}")
  String getJobParam(@Param("name") String name);


  @RequestLine("POST /getRemainMap")
  Result getRemainMap(@RequestBody Map<String, ?> map);


  @RequestLine("POST /getRemainBean")
  String getRemain(@RequestBody Company company);

  @RequestLine("GET /returnMap")
  Map returnMap();


  @RequestLine("GET /returnBean")
  Company returnBean();
}
