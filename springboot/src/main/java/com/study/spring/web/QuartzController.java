package com.study.spring.web;

import com.study.spring.annotation.profile.DruidEnv;
import com.study.spring.entity.model.ReqActionModel;
import com.study.spring.entity.model.ScheduleJob;
import com.study.spring.enums.ReqAction;
import com.study.spring.service.quartz.IQuartzService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jeffrey
 * @since 2017/01/22 1:19
 */
@RestController
@RequestMapping(value = "quartz")
@DruidEnv
@ConditionalOnProperty(name = "quartz.enabled", havingValue = "true")
public class QuartzController {

    @Autowired
    private IQuartzService quartzService;

    @RequestMapping(value = "jobs", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有job")
    public List<ScheduleJob> getAllJobs(@RequestParam(required = false) boolean isRun)
        throws SchedulerException {
        if (isRun) {
            return quartzService.getAllRunningJobs();
        } else {
            return quartzService.getAllJobs();
        }
    }

    @RequestMapping(value = "jobs/{job_name}", method = RequestMethod.POST)
    @ApiOperation(value = "根据jobName管理(暂停,重启,运行)job")
    public void manageJob(
        @RequestBody ReqActionModel reqActionModel, @PathVariable("job_name") String jobName)
        throws SchedulerException {
        ReqAction reqAction = reqActionModel.getAction();
        if (reqAction.equals(ReqAction.PAUSE_JOB)) {
            quartzService.pauseJob(jobName);
        } else if (reqAction.equals(ReqAction.RESUME_JOB)) {
            quartzService.resumeJob(jobName);
        } else if (reqAction.equals(ReqAction.RUN_JOB)) {
            quartzService.runJob(jobName);
        }
    }

    @RequestMapping(value = "jobs/{job_name}", method = RequestMethod.DELETE)
    @ApiOperation(value = "根据jobName删除job")
    public void deleteJob(@PathVariable("job_name") String jobName) throws SchedulerException {
        quartzService.deleteJob(jobName);
    }
}
