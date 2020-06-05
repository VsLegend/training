package com.trainings.socket.websocket.vo;

import com.alibaba.ans.shaded.com.alibaba.fastjson.JSON;
import com.trainings.socket.dto.RemoteBDTO;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * @author Wangjunwei
 * @Date 2020/5/29 14:40
 * @Description Java对象B
 */
public class EncoderBJava implements IMessage, Encoder.Text<RemoteBDTO> {


  @Override
  public String encode(RemoteBDTO t) throws EncodeException {
    // json 拼接等
    return JSON.toJSONString(t);
  }

  @Override
  public void init(EndpointConfig endpointConfig) {
  }

  @Override
  public void destroy() {

  }
}
