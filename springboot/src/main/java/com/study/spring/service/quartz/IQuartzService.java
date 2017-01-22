package com.study.spring.service.quartz;

import com.study.spring.entity.model.ScheduleJob;
import org.quartz.SchedulerException;

import java.util.List;

/**
 * @author Jeffrey
 * @since 2017/01/22 1:20
 */
public interface IQuartzService {

  /**
   * 获取所有job
   *
   * @return
   */
  List<ScheduleJob> getAllJobs() throws SchedulerException;

  /**
   * 获取所有运行中的job
   *
   * @return
   */
  List<ScheduleJob> getAllRunningJobs() throws SchedulerException;

  /**
   * 暂停job
   *
   * @param jobName
   */
  void pauseJob(String jobName) throws SchedulerException;

  /**
   * 重启job
   *
   * @param jobName
   */
  void resumeJob(String jobName) throws SchedulerException;

  /**
   * 运行job
   *
   * @param jobName
   */
  void runJob(String jobName) throws SchedulerException;

  /**
   * 删除job
   *
   * @param jobName
   */
  void deleteJob(String jobName) throws SchedulerException;
}
