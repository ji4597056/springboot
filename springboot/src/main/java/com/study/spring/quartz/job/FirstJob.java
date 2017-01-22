package com.study.spring.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

/**
 * @author Jeffrey
 * @since 2017/01/18 11:17
 */
public class FirstJob implements Job {

  private static final Logger logger = LoggerFactory.getLogger(FirstJob.class);

  @Autowired private DataSource dataSource;

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    logger.info("正在执行第一个任务,dataSource:{}", dataSource.getClass());
  }
}
