package com.study.spring.quartz;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * quartz DataSource 配置
 *
 * @author Jeffrey
 * @since 2017/01/18 17:19
 */
@ConfigurationProperties(prefix = "spring.datasource.druid.quartz")
public class QuartzDSProperties {

  private String driverClassName;
  private String url;
  private String username;
  private String password;

  public String getDriverClassName() {
    return driverClassName;
  }

  public void setDriverClassName(String driverClassName) {
    this.driverClassName = driverClassName;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
