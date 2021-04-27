package com.training.swagger3.config;

/**
 * @User: wong
 * @Date: 2020/10/13
 * @Description: 配置类同yml配置
 */

//@Component
//@ConfigurationProperties("swagger")
public class SwaggerConfig {

  /**
   * 是否开启swagger，生产环境一般关闭，所以这里定义一个变量
   */
  private Boolean enable;

  /**
   * 项目应用名
   */
  private String applicationName;

  /**
   * 项目版本信息
   */
  private String applicationVersion;

  /**
   * 项目描述信息
   */
  private String applicationDescription;

  /**
   * 接口调试地址
   */
  private String tryHost;

  public Boolean getEnable() {
    return enable;
  }

  public void setEnable(Boolean enable) {
    this.enable = enable;
  }

  public String getApplicationName() {
    return applicationName;
  }

  public void setApplicationName(String applicationName) {
    this.applicationName = applicationName;
  }

  public String getApplicationVersion() {
    return applicationVersion;
  }

  public void setApplicationVersion(String applicationVersion) {
    this.applicationVersion = applicationVersion;
  }

  public String getApplicationDescription() {
    return applicationDescription;
  }

  public void setApplicationDescription(String applicationDescription) {
    this.applicationDescription = applicationDescription;
  }

  public String getTryHost() {
    return tryHost;
  }

  public void setTryHost(String tryHost) {
    this.tryHost = tryHost;
  }

}
