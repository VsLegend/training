package com.trainings.socket.websocket;

import com.alibaba.ans.shaded.com.alibaba.fastjson.JSONArray;
import com.trainings.socket.websocket.vo.EncoderAJava;
import com.trainings.socket.websocket.vo.EncoderBJava;
import com.trainings.socket.websocket.vo.IMessage;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * @author Wangjunwei
 * @Date 2020/5/29 14:40
 * @Description
 */

/**
 * Like endpoints, decoder instances are associated with one and only one WebSocket connection and peer
 * , so there is only one thread executing the code of a decoder instance at any given time.
 * 连接只能是一对一的。所以同时只有一个线程执行一个decoder实例。
 */
public class DecoderMessage implements Decoder.Text<IMessage> {


  /**
   * 解析类
   * @param s
   * @return
   * @throws DecodeException
   */
  @Override
  public IMessage decode(String s) throws DecodeException {
    Object parse = JSONArray.parse(s);
    if (parse instanceof EncoderAJava) {
      return (EncoderAJava) parse;
    } else if (parse instanceof EncoderBJava) {
      return (EncoderBJava) parse;
    }
    return null;
  }

  /**
   * 判断消息是否能被解析为Encoder.Text<T>中的T.如果不能就不会执行decode
   * @param s 客户端发送的消息
   * @return
   */
  @Override
  public boolean willDecode(String s) {
    Object parse = JSONArray.parse(s);
    return parse instanceof IMessage;
  }

  @Override
  public void init(EndpointConfig endpointConfig) {

  }

  @Override
  public void destroy() {

  }
}
