package com.study.spring.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.study.spring.annotation.profile.DruidEnv;
import com.study.spring.common.SysConstant;
import com.study.spring.config.properties.DefaultDruidProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * 配置druid数据源(推荐方式)
 *
 * @author Jeffrey
 * @since 2017/1/9 15:44
 */
@Configuration
@EnableConfigurationProperties({DataSourceProperties.class,DefaultDruidProperties.class})
@EnableTransactionManagement
@DruidEnv
public class SimpleDruidConfig {

  private static final Logger logger = LoggerFactory.getLogger(SimpleDruidConfig.class);

  @Bean
  @ConfigurationProperties(prefix = SysConstant.DRUID_PREFIX)
  @Primary
  public DataSource dataSource(
      DataSourceProperties properties) {
    return properties.initializeDataSourceBuilder().build();
  }

  @Bean
  public DruidDSFactory druidDSFactory(DefaultDruidProperties properties) {
    return new DruidDSFactory(properties);
  }

  /**
   * 注册一个StatViewServlet
   *
   * @return
   */
  @Bean
  public ServletRegistrationBean druidStatViewServle() {
    ServletRegistrationBean servletRegistrationBean =
        new ServletRegistrationBean(new StatViewServlet(), "/admin/druid/*");
    // 添加初始化参数：initParams
    // 白名单：
    // servletRegistrationBean.addInitParameter("allow","127.0.0.1");
    // IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
    // servletRegistrationBean.addInitParameter("deny","127.0.0.1");
    // 登录查看信息的账号密码.
    // servletRegistrationBean.addInitParameter("loginUsername","admin");
    // servletRegistrationBean.addInitParameter("loginPassword","admin");
    // 是否能够重置数据.
    servletRegistrationBean.addInitParameter("resetEnable", "false");
    return servletRegistrationBean;
  }

  /**
   * 注册一个：filterRegistrationBean
   *
   * @return
   */
  @Bean
  public FilterRegistrationBean druidStatFilter() {
    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
    // 添加过滤规则.
    filterRegistrationBean.addUrlPatterns("/*");
    // 添加不需要忽略的格式信息.
    filterRegistrationBean.addInitParameter(
        "exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/admin/druid/*");
    return filterRegistrationBean;
  }

  /**
   * Durid datasource工厂类
   */
  public class DruidDSFactory{

    private DefaultDruidProperties properties;

    public DruidDSFactory(DefaultDruidProperties properties) {
      this.properties = properties;
    }

    public DefaultDruidProperties getProperties() {
      return properties;
    }

    public void setProperties(DefaultDruidProperties properties) {
      this.properties = properties;
    }

    public DataSource createDataSource() {
      HashMap<String, Object> map = new HashMap<>();
      map.put(DruidDataSourceFactory.PROP_DRIVERCLASSNAME, properties.getDriverClassName());
      map.put(DruidDataSourceFactory.PROP_URL, properties.getUrl());
      map.put(DruidDataSourceFactory.PROP_USERNAME, properties.getUsername());
      map.put(DruidDataSourceFactory.PROP_PASSWORD, properties.getPassword());
      map.put(DruidDataSourceFactory.PROP_INITIALSIZE, properties.getInitialSize());
      map.put(DruidDataSourceFactory.PROP_MINIDLE, properties.getMinIdle());
      map.put(DruidDataSourceFactory.PROP_MAXACTIVE, properties.getMaxActive());
      map.put(DruidDataSourceFactory.PROP_MAXWAIT, properties.getMaxWait());
      map.put(DruidDataSourceFactory.PROP_TIMEBETWEENEVICTIONRUNSMILLIS, properties.getTimeBetweenEvictionRunsMillis());
      map.put(DruidDataSourceFactory.PROP_MINEVICTABLEIDLETIMEMILLIS, properties.getMinEvictableIdleTimeMillis());
      map.put(DruidDataSourceFactory.PROP_VALIDATIONQUERY, properties.getValidationQuery());
      map.put(DruidDataSourceFactory.PROP_TESTWHILEIDLE, properties.getTestWhileIdle());
      map.put(DruidDataSourceFactory.PROP_TESTONBORROW, properties.getTestOnBorrow());
      map.put(DruidDataSourceFactory.PROP_TESTONRETURN, properties.getTestOnReturn());
      map.put(DruidDataSourceFactory.PROP_POOLPREPAREDSTATEMENTS, properties.getPoolPreparedStatements());
      map.put(DruidDataSourceFactory.PROP_FILTERS, properties.getFilters());
      map.put(DruidDataSourceFactory.PROP_CONNECTIONPROPERTIES, properties.getConnectionProperties());
      try {
        return DruidDataSourceFactory.createDataSource(map);
      } catch (Exception e) {
        e.printStackTrace();
      }
      return null;
    }
  }
}
