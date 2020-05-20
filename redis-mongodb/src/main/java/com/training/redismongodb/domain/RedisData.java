package com.training.redismongodb.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Wangjunwei
 * @Date 2019/9/30 10:53
 * @Description
 */

@Data
@ApiModel("数据")
public class RedisData {

  @ApiModelProperty("键")
  private String key;

  @ApiModelProperty("新键")
  private String key1;

  @ApiModelProperty("值")
  private String value;

  @ApiModelProperty("偏移量-插入位置")
  private Integer offset;

}
