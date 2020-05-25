package com.training.common.po;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Wangjunwei
 * @Date 2020/5/20 17:02
 * @Description
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company implements Serializable {

  private String name;

  private String marketValue;

  private Integer people;

}
