package com.study.spring.service.quartz.impl;

import com.study.spring.annotation.profile.DruidEnv;
import com.study.spring.entity.model.ScheduleJob;
import com.study.spring.service.quartz.IQuartzService;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Jeffrey
 * @since 2017/01/22 1:21
 */
@Service
@DruidEnv
@ConditionalOnProperty(name = "quartz.enabled", havingValue = "true")
public class QuartzServiceImpl implements IQuartzService {

  @Autowired private SchedulerFactoryBean factory;

  @Override
  public List<ScheduleJob> getAllJobs() throws SchedulerException {
    Scheduler scheduler = factory.getScheduler();
    GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
    Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
    List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();
    for (JobKey jobKey : jobKeys) {
      List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
      for (Trigger trigger : triggers) {
        ScheduleJob job = new ScheduleJob();
        job.setJobName(jobKey.getName());
        job.setJobGroup(jobKey.getGroup());
        job.setDescription("触发器:" + trigger.getKey());
        Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
        job.setJobStatus(triggerState.name());
        if (trigger instanceof CronTrigger) {
          CronTrigger cronTrigger = (CronTrigger) trigger;
          String cronExpression = cronTrigger.getCronExpression();
          job.setCronExpression(cronExpression);
        }
        jobList.add(job);
      }
    }
    return jobList;
  }

  @Override
  public List<ScheduleJob> getAllRunningJobs() throws SchedulerException {
    Scheduler scheduler = factory.getScheduler();
    List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
    List<ScheduleJob> jobList = new ArrayList<ScheduleJob>(executingJobs.size());
    for (JobExecutionContext executingJob : executingJobs) {
      ScheduleJob job = new ScheduleJob();
      JobDetail jobDetail = executingJob.getJobDetail();
      JobKey jobKey = jobDetail.getKey();
      Trigger trigger = executingJob.getTrigger();
      job.setJobName(jobKey.getName());
      job.setJobGroup(jobKey.getGroup());
      job.setDescription("触发器:" + trigger.getKey());
      Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
      job.setJobStatus(triggerState.name());
      if (trigger instanceof CronTrigger) {
        CronTrigger cronTrigger = (CronTrigger) trigger;
        String cronExpression = cronTrigger.getCronExpression();
        job.setCronExpression(cronExpression);
      }
      jobList.add(job);
    }
    return jobList;
  }

  @Override
  public void pauseJob(String jobName) throws SchedulerException {
    Scheduler scheduler = factory.getScheduler();
    JobKey jobKey = JobKey.jobKey(jobName);
    scheduler.pauseJob(jobKey);
  }

  @Override
  public void resumeJob(String jobName) throws SchedulerException {
    Scheduler scheduler = factory.getScheduler();
    JobKey jobKey = JobKey.jobKey(jobName);
    scheduler.resumeJob(jobKey);
  }

  @Override
  public void runJob(String jobName) throws SchedulerException {
    Scheduler scheduler = factory.getScheduler();
    JobKey jobKey = JobKey.jobKey(jobName);
    scheduler.triggerJob(jobKey);
  }

  @Override
  public void deleteJob(String jobName) throws SchedulerException {
    Scheduler scheduler = factory.getScheduler();
    JobKey jobKey = JobKey.jobKey(jobName);
    scheduler.deleteJob(jobKey);
  }
}
