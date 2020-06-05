package com.trainings.socket.service.impl;

import com.trainings.socket.service.ChatService;
import com.trainings.socket.websocket.ChatSocket;
import java.util.HashMap;
import org.springframework.stereotype.Service;

/**
 * @author Wangjunwei
 * @Date 2020/5/29 10:59
 * @Description
 */
@Service
public class ChatServiceImpl implements ChatService {

  private final HashMap<String, Object> hashMap = new HashMap<>();

  private ChatSocket annotationSocket = new ChatSocket();

  @Override
  public void sendMessage(String message, String id) {
    annotationSocket.sendMessage(message, id);
  }
}
