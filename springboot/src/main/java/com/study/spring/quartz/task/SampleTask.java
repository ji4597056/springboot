package com.study.spring.quartz.task;

import com.study.spring.quartz.QuartzConfig;
import com.study.spring.quartz.job.SampleJob;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @author Jeffrey
 * @since 2017/01/18 12:58
 */
@Configuration
@AutoConfigureAfter(QuartzConfig.class)
public class SampleTask {

  @Autowired private SchedulerFactoryBean factory;

  private final String cronExpression = "*/5 * * * * ?";

  @Bean
  public JobDetailFactoryBean sampleJobDetail() {
    return QuartzConfig.createJobDetail(SampleJob.class);
  }

  @Bean
  public CronTriggerFactoryBean sampleJobTrigger(
      @Qualifier("sampleJobDetail") JobDetail jobDetail) {
    CronTriggerFactoryBean triggerBean = QuartzConfig.createCronTrigger(jobDetail, cronExpression);
    factory.setTriggers(triggerBean.getObject());
    return triggerBean;
  }
}
