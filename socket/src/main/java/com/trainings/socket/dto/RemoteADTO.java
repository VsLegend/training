package com.trainings.socket.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * @author Wangjunwei
 * @Date 2020/5/29 14:56
 * @Description
 */

@Data
public class RemoteADTO implements Serializable {

  private String message;

  private String id;

  private String type;

}
