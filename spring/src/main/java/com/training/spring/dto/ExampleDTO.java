package com.training.spring.dto;

import com.training.spring.aop.annotation.valid.CNotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Wangjunwei
 * @Date 2020/5/20 17:02
 * @Description
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class ExampleDTO implements Serializable {

  @CNotEmpty(message = "姓名不能为空")
  private String name;

  private Date birthday;

  private int age;

  private boolean male;

  private double remainMoney;

  private List<String> list;

}
