package com.study.spring.quartz;

import com.study.spring.common.SysConstant;
import com.study.spring.config.DruidConfig;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * quartz 配置
 *
 * @author Jeffrey
 * @since 2017/01/18 10:36
 */
@Configuration
@ConditionalOnProperty(name = "quartz.enabled", havingValue = "true")
@AutoConfigureAfter(DruidConfig.class)
@EnableConfigurationProperties({DataSourceProperties.class, QuartzDSProperties.class})
public class QuartzConfig {

  private static final Logger logger = LoggerFactory.getLogger(QuartzConfig.class);

  private final String configPath = "/properties/quartz.properties";

  /**
   * 配置druid的quartz数据源
   *
   * @param properties
   * @return
   */
  @Bean(name = "quartzDataSource")
  @ConfigurationProperties(prefix = SysConstant.DRUID_PREFIX)
  public DataSource quartzDataSource(
      DataSourceProperties properties, QuartzDSProperties quartzDSProperties) {
    properties.setDriverClassName(quartzDSProperties.getDriverClassName());
    properties.setUrl(quartzDSProperties.getUrl());
    properties.setUsername(quartzDSProperties.getUsername());
    properties.setPassword(quartzDSProperties.getPassword());
    DataSource dataSource = properties.initializeDataSourceBuilder().build();
    return dataSource;
  }

  @Bean
  public Properties quartzProperties() throws IOException {
    PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
    propertiesFactoryBean.setLocalOverride(true);
    propertiesFactoryBean.setLocation(new ClassPathResource(configPath));
    propertiesFactoryBean.afterPropertiesSet();
    return propertiesFactoryBean.getObject();
  }

  @Bean
  public SchedulerFactoryBean schedulerFactoryBean(
      ApplicationContext context, @Qualifier("quartzDataSource") DataSource dataSource)
      throws IOException {
    SchedulerFactoryBean factory = new SchedulerFactoryBean();
    factory.setApplicationContext(context);
    factory.setQuartzProperties(quartzProperties());
    factory.setDataSource(dataSource);
    return factory;
  }

  /**
   * create JobDetail
   *
   * @param jobClass
   * @return
   */
  public static JobDetailFactoryBean createJobDetail(Class jobClass) {
    JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
    factoryBean.setJobClass(jobClass);
    factoryBean.setDurability(true);
    return factoryBean;
  }

  /**
   * create SimpleTrigger
   *
   * @param jobDetail
   * @param pollFrequencyMs
   * @return
   */
  public static SimpleTriggerFactoryBean createTrigger(JobDetail jobDetail, long pollFrequencyMs) {
    SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
    factoryBean.setJobDetail(jobDetail);
    factoryBean.setStartDelay(0L);
    factoryBean.setRepeatInterval(pollFrequencyMs);
    factoryBean.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
    factoryBean.setMisfireInstruction(
        SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT);
    return factoryBean;
  }

  /**
   * create CronTrigger
   *
   * @param jobDetail
   * @param cronExpression
   * @return
   */
  public static CronTriggerFactoryBean createCronTrigger(
      JobDetail jobDetail, String cronExpression) {
    CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
    factoryBean.setJobDetail(jobDetail);
    factoryBean.setCronExpression(cronExpression);
    factoryBean.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);
    return factoryBean;
  }
}
