package com.study.spring.quartz.task;

import com.study.spring.config.QuartzConfig;
import com.study.spring.quartz.job.FirstJob;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

/**
 * @author Jeffrey
 * @since 2017/01/18 12:58
 */
@Configuration
@AutoConfigureBefore(QuartzConfig.class)
public class FirstTask {

  private final String cronExpression = "*/10 * * * * ?";

  @Bean
  public JobDetailFactoryBean firstJobDetail() {
    return QuartzConfig.createJobDetail(FirstJob.class);
  }

  @Bean
  public CronTriggerFactoryBean firstJobTrigger(@Qualifier("firstJobDetail") JobDetail jobDetail)
      throws SchedulerException {
    return QuartzConfig.createCronTrigger(jobDetail, cronExpression);
  }
}
