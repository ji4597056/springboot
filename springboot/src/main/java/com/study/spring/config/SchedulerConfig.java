package com.study.spring.config;

import com.study.spring.annotation.profile.IgnoreEnv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * spring 定时任务配置
 *
 * @author Jeffrey
 * @since 2017/01/17 14:27
 */
@Configuration
@EnableScheduling
@IgnoreEnv
public class SchedulerConfig {

  private static final Logger logger = LoggerFactory.getLogger(SchedulerConfig.class);

  @Scheduled(cron = "0 0/2 8-20 * * ?")
  public void executeTask1() {
    // 间隔2分钟,执行工单上传任务
    Thread current = Thread.currentThread();
    System.out.println("定时任务1:" + current.getId());
    logger.info(
        "ScheduledTest.executeFileDownLoadTask 定时任务1:"
            + current.getId()
            + ",name:"
            + current.getName());
  }

  @Scheduled(cron = "0 0/1 8-20 * * ?")
  public void executeTask2() {
    // 间隔1分钟,执行工单上传任务
    Thread current = Thread.currentThread();
    System.out.println("定时任务2:" + current.getId());
    logger.info(
        "ScheduledTest.executeUploadTask 定时任务2:" + current.getId() + ",name:" + current.getName());
  }

  @Scheduled(cron = "0 0/3 5-23 * * ?")
  public void executeTask3() {
    // 间隔3分钟,执行工单上传任务
    Thread current = Thread.currentThread();
    System.out.println("定时任务3:" + current.getId());
    logger.info(
        "ScheduledTest.executeUploadBackTask 定时任务3:"
            + current.getId()
            + ",name:"
            + current.getName());
  }
}
