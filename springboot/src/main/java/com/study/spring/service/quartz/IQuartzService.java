package com.study.spring.service.quartz;

import com.study.spring.entity.model.ScheduleJob;
import java.util.List;
import org.quartz.SchedulerException;

/**
 * @author Jeffrey
 * @since 2017/01/22 1:20
 */
public interface IQuartzService {

    /**
     * 获取所有job
     */
    List<ScheduleJob> getAllJobs() throws SchedulerException;

    /**
     * 获取所有运行中的job
     */
    List<ScheduleJob> getAllRunningJobs() throws SchedulerException;

    /**
     * 暂停job
     */
    void pauseJob(String jobName) throws SchedulerException;

    /**
     * 重启job
     */
    void resumeJob(String jobName) throws SchedulerException;

    /**
     * 运行job
     */
    void runJob(String jobName) throws SchedulerException;

    /**
     * 删除job
     */
    void deleteJob(String jobName) throws SchedulerException;
}
