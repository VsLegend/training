package com.training.remotefeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class RemoteFeignApplication {

  public static void main(String[] args) {
    SpringApplication.run(RemoteFeignApplication.class, args);
  }

}
