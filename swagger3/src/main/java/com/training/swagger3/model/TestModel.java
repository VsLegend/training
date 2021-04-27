package com.training.swagger3.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @User: wong
 * @Date: 2020/10/13
 * @Description:
 */

@Data
@ApiModel("测试实体类")
public class TestModel {

  @ApiModelProperty("字段1")
  private String fieldA;

  @ApiModelProperty("字段2")
  private String fieldB;

}
