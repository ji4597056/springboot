package com.study.spring.config.properties;

import com.study.spring.common.SysConstant;
import org.dom4j.io.STAXEventReader;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * Druid 默认配置
 *
 * @author Jeffrey
 * @since 2017/01/19 15:20
 */
@ConfigurationProperties(prefix = SysConstant.DRUID_PREFIX)
public class DefaultDruidProperties {

  private String driverClassName;
  private String url;
  private String username;
  private String password;
  private String initialSize;
  private String minIdle;
  private String maxActive;
  private String maxWait;
  private String timeBetweenEvictionRunsMillis;
  private String minEvictableIdleTimeMillis;
  private String validationQuery;
  private String testWhileIdle;
  private String testOnBorrow;
  private String testOnReturn;
  private String poolPreparedStatements;
  private String maxPoolPreparedStatementPerConnectionSize;
  private String filters;
  private String connectionProperties;

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

  public String getInitialSize() {
    return initialSize;
  }

  public void setInitialSize(String initialSize) {
    this.initialSize = initialSize;
  }

  public String getMinIdle() {
    return minIdle;
  }

  public void setMinIdle(String minIdle) {
    this.minIdle = minIdle;
  }

  public String getMaxActive() {
    return maxActive;
  }

  public void setMaxActive(String maxActive) {
    this.maxActive = maxActive;
  }

  public String getMaxWait() {
    return maxWait;
  }

  public void setMaxWait(String maxWait) {
    this.maxWait = maxWait;
  }

  public String getTimeBetweenEvictionRunsMillis() {
    return timeBetweenEvictionRunsMillis;
  }

  public void setTimeBetweenEvictionRunsMillis(String timeBetweenEvictionRunsMillis) {
    this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
  }

  public String getMinEvictableIdleTimeMillis() {
    return minEvictableIdleTimeMillis;
  }

  public void setMinEvictableIdleTimeMillis(String minEvictableIdleTimeMillis) {
    this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
  }

  public String getValidationQuery() {
    return validationQuery;
  }

  public void setValidationQuery(String validationQuery) {
    this.validationQuery = validationQuery;
  }

  public String getTestWhileIdle() {
    return testWhileIdle;
  }

  public void setTestWhileIdle(String testWhileIdle) {
    this.testWhileIdle = testWhileIdle;
  }

  public String getTestOnBorrow() {
    return testOnBorrow;
  }

  public void setTestOnBorrow(String testOnBorrow) {
    this.testOnBorrow = testOnBorrow;
  }

  public String getTestOnReturn() {
    return testOnReturn;
  }

  public void setTestOnReturn(String testOnReturn) {
    this.testOnReturn = testOnReturn;
  }

  public String getPoolPreparedStatements() {
    return poolPreparedStatements;
  }

  public void setPoolPreparedStatements(String poolPreparedStatements) {
    this.poolPreparedStatements = poolPreparedStatements;
  }

  public String getMaxPoolPreparedStatementPerConnectionSize() {
    return maxPoolPreparedStatementPerConnectionSize;
  }

  public void setMaxPoolPreparedStatementPerConnectionSize(String maxPoolPreparedStatementPerConnectionSize) {
    this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
  }

  public String getFilters() {
    return filters;
  }

  public void setFilters(String filters) {
    this.filters = filters;
  }

  public String getConnectionProperties() {
    return connectionProperties;
  }

  public void setConnectionProperties(String connectionProperties) {
    this.connectionProperties = connectionProperties;
  }
}
