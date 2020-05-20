package com.training.mediatransfer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Wangjunwei
 * @Date 2019/9/6 10:25
 * @Description swagger 配置类
 */

@Configuration
@EnableSwagger2
public class Swagger {

  @Bean
  public Docket createRestApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .select()
        // .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))           //加了ApiOperation注解的方法，生成接口文档
        .apis(RequestHandlerSelectors.basePackage("com.training.mediatransfer.controller"))  //包下的类，生成接口文档
        .paths(PathSelectors.any())
        .build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("框架swagger")
        .description("测试")
        .version("1.0.0")
        .build();
  }
}
