package com.training.common.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

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

  private LocalDateTime time;

}
