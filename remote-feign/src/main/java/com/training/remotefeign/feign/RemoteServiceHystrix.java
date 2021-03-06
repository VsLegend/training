package com.training.remotefeign.feign;

import com.training.common.common.Result;
import com.training.common.po.Company;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Wangjunwei
 * @Date 2020/5/21 20:03
 * @Description
 */

// 开启降级处理时，需要添加注解
@Component
public class RemoteServiceHystrix implements RemoteService {

  @Override
  public Map getJobListAuth(Map<String, ?> map) {
    return null;
  }

  @Override
  public Map getJobList() {
    return null;
  }

  @Override
  public String getJob(String name) {
    return null;
  }

  @Override
  public Result getJobByMap(Map<String, ?> map) {
    return null;
  }

  @Override
  public String getJobByBean(Company company) {
    return null;
  }

  @Override
  public Map returnMap() {
    return null;
  }

  @Override
  public Company returnBean() {
    return null;
  }
}
