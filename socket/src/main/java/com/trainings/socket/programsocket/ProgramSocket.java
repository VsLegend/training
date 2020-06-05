package com.trainings.socket.programsocket;

import java.io.IOException;
import javax.websocket.CloseReason;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler.Whole;
import javax.websocket.Session;

/**
 * @author Wangjunwei
 * @Date 2020/5/28 14:40
 * @Description 编程式socket链接(Programmatic Endpoints)
 */
public class ProgramSocket extends Endpoint {

  /**
   * addMessageHandler 用于注册一个消息处理器
   * getBasicRemote 用于给客户端返回数据
   * onMessage 当收到客户端的消息时，便会触发onMessage方法
   * @param session 代表当前类的服务端和客户端的连接
   */
  @Override
  public void onOpen(Session session, EndpointConfig endpointConfig) {
    session.addMessageHandler(new Whole<String>() {
      @Override
      public void onMessage(String msg) {
        try {
          session.getBasicRemote().sendText(msg);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
  }

  @Override
  public void onClose(Session session, CloseReason closeReason) {
    super.onClose(session, closeReason);
  }

  @Override
  public void onError(Session session, Throwable throwable) {
    super.onError(session, throwable);
  }
}
