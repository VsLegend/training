package com.training.swagger3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi // 开启api
@SpringBootApplication
public class Swagger3Application {

  public static void main(String[] args) {
    SpringApplication.run(Swagger3Application.class, args);
  }

}
