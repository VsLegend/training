package com.training.mediatransfer.domain;

import com.alibaba.ans.shaded.com.alibaba.fastjson.JSONPObject;
import java.util.List;
import java.util.Map;
import lombok.Data;

/**
 * @author Wangjunwei
 * @Date 2019/10/17 17:27
 * @Description
 */
@Data
public class Result {

  public Result() {
    this.code = 9999;
    this.data = null;
    this.msg = "访问资源错误";
  }

  private Integer code;

  private List<Map<String, Object>> data;

  private String msg;

}
