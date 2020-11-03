package com.training.secure.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @User: wong
 * @Date: 2020/10/21
 * @Description:
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransferDataPO implements Serializable {

  @ApiModelProperty(notes = "license")
  private transient String license;

  @ApiModelProperty(notes = "请求时间")
  private Long timestamp;

  @ApiModelProperty(notes = "随机数")
  private String nonce;

  @ApiModelProperty(notes = "签名")
  private String sign;


  public String toString() {
    return "license=" + license + "&timestamp=" + timestamp + "&nonce=" + nonce;
  }

}
