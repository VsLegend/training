package com.trainings.socket.websocket;

import com.training.common.common.AppException;
import com.training.common.enums.ErrorCode;
import com.trainings.socket.websocket.vo.EncoderAJava;
import com.trainings.socket.websocket.vo.EncoderBJava;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.PongMessage;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * @author Wangjunwei
 * @Date 2020/5/28 15:09
 * @Description 注解socket(Annotated Endpoints)
 */


/**
 * value：连接名称 encoders：Java对象转WebSocket messages。其作用是，可以直接把Java对象作为消息体发送给客户端。 decoders：WebSocket
 * messages转Java对象
 */
@Slf4j
@Component
@ServerEndpoint(value = "/chat/{userName}", encoders = {EncoderAJava.class, EncoderBJava.class}
    , decoders = DecoderMessage.class)
public class ChatSocket {

  private Session session;

  private String userName;

  // 记录每一个连接的socket信息
  private static Map<String, Set<Session>> socketList = new HashMap<String, Set<Session>>();

  /**
   * Annotated endpoints can receive path parameters as arguments in methods annotated with
   *
   * @OnOpen,
   * @OnMessage, and @OnClose. 消息连接建立后调用。通过保存改session，就可以主动向客户端发起连接。如sendMessage()方法
   * 在@OnOpen、@OnMessage、@OnClose注解的方法中，可以通过@PathParam获取socket连接地址中的参数
   */
  @OnOpen
  public void onOpen(Session session, EndpointConfig endpointConfig,
      @PathParam("userName") String userName) {
    log.info("与客户端建立连接，用户姓名：{}", userName);
    this.session = session;
    this.userName = userName;
    Set<Session> sessions = socketList.get(userName);
    if (CollectionUtils.isEmpty(sessions)) {
      socketList.put(userName, new HashSet<Session>() {
        {
          add(session);
        }
      });
    } else {
      sessions.add(session);
      socketList.put(userName, sessions);
    }
  }

  /**
   * You can have at most three methods annotated with @OnMessage in an endpoint, one for each
   * message type: text, binary, and pong. 接收到客户端发送的消息时调用 注：@OnMessage注解在同一个实例中，最多可以同时注解三个方法，分别是文本、二进制流、pong
   */
  @OnMessage
  public void onMessage(Session session, String msg) {
    log.info("接收到客户端{}的消息：{}", userName, msg);
    this.sendToAllPeers(userName + ": " + msg);
//      session.getBasicRemote().sendText(msg);
  }

  @OnMessage
  public void binaryMessage(Session session, ByteBuffer msg) {
    log.info("Binary message: {}", msg.toString());
    try {
      session.getBasicRemote().sendBinary(msg);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @OnMessage
  public void pongMessage(Session session, PongMessage msg) {
    log.info("Pong message: {}", msg.getApplicationData().toString());
    try {
      session.getBasicRemote().sendPong(msg.getApplicationData());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * 连接错误时调用
   */
  @OnError
  public void onError(Session session, Throwable throwable) {
    log.info("客户端连接异常：{}", throwable.getMessage());
  }

  /**
   * 关闭连接时调用
   */
  @OnClose
  public void onClose(Session session, CloseReason closeReason) {
    log.info("客户端已关闭连接：{}，消息：{}", closeReason.getCloseCode(), closeReason.getReasonPhrase());
  }


  /**
   * Each instance of an endpoint class is associated with one and only one connection and peer
   * 在每一个ServerEndpoint实例中，连接只能是一对一的。但是可以通过session的getOpenSessions获取与当前服务端连接的所有session 群发消息
   */
  public void sendToAllPeers(String message) {
    session.getOpenSessions().forEach(session1 -> {
      synchronized (session1) {
        if (session1.isOpen()) {
          try {
            session1.getBasicRemote().sendText(message);
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
    });
  }


  /**
   * 服务器端主动发送消息
   */
  public boolean sendMessage(String message, String userName) {
    Set<Session> sessions = socketList.get(userName);
    if (sessions.isEmpty()) {
      return false;
    }
    final boolean[] b = {false};
    sessions.forEach(session -> {
      if (session.isOpen()) {
        try {
          /**
           * This method blocks until the whole message has been transmitted
           * 同步执行，当前执行完毕后，才会执行下一个
           */
          session.getBasicRemote().sendText(message);
          /**
           * 异步执行
           */
//        session.getAsyncRemote().sendText(message);
          b[0] = true;
        } catch (IOException e) {
          e.printStackTrace();
        }
      } else {
        throw new AppException(ErrorCode.SOCKET_HAVE_BEEN_CLOSED);
      }
    });
    return b[0];
  }

}
