package com.trainings.socket.config;

import javax.websocket.server.ServerEndpointConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author Wangjunwei
 * @Date 2020/5/28 17:53
 * @Description web socket 配置类
 */
@Configuration
public class WebSocketConfig extends ServerEndpointConfig.Configurator {

  @Bean
  public ServerEndpointExporter serverEndpointExporter() {
    return new ServerEndpointExporter();
  }

}
