package com.study.spring.quartz.task;

import com.study.spring.config.QuartzConfig;
import com.study.spring.quartz.job.SecondJob;
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
public class SecondTask {

  private final String cronExpression = "*/15 * * * * ?";

  @Bean
  public JobDetailFactoryBean secondJobDetail() {
    return QuartzConfig.createJobDetail(SecondJob.class);
  }

  @Bean
  public CronTriggerFactoryBean secondTrigger(@Qualifier("secondJobDetail") JobDetail jobDetail)
      throws SchedulerException {
    return QuartzConfig.createCronTrigger(jobDetail, cronExpression);
  }
}
