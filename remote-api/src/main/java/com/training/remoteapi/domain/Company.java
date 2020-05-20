package com.training.remoteapi.domain;

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

  private String index;

  private String remain;

  private Integer people;

}
