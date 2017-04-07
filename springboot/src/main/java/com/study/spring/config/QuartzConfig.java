package com.study.spring.config;

import com.study.spring.common.SysConstant;
import com.study.spring.common.druid.BaseDruidDataSourceFactory;
import com.study.spring.common.quartz.AutowiringSpringBeanJobFactory;
import java.io.IOException;
import java.util.Properties;
import javax.sql.DataSource;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.spi.JobFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

/**
 * quartz 配置
 *
 * @author Jeffrey
 * @since 2017/01/18 10:36
 */
@Configuration
@ConditionalOnProperty(name = "quartz.enabled", havingValue = "true")
@AutoConfigureAfter(DruidConfig.class)
public class QuartzConfig {

    private static final Logger logger = LoggerFactory.getLogger(QuartzConfig.class);

    private final String configPath = "/properties/quartz.properties";

    /**
     * DataSource 配置
     */
    @Bean(name = "quartzDataSource")
    @ConfigurationProperties(prefix = SysConstant.DRUID_PREFIX + ".quartz")
    public DataSource quartzDataSource(BaseDruidDataSourceFactory factory) throws Exception {
        return factory.createDataSource();
    }

    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
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
        JobFactory jobFactory,
        @Qualifier("quartzDataSource") DataSource dataSource,
        @Qualifier("quartzProperties") Properties properties,
        Trigger[] triggers)
        throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setJobFactory(jobFactory);
        factory.setQuartzProperties(properties);
        factory.setDataSource(dataSource);
        factory.setTriggers(triggers);
        factory.setOverwriteExistingJobs(true);
        return factory;
    }

    /**
     * create JobDetail
     */
    public static JobDetailFactoryBean createJobDetail(Class jobClass) {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(jobClass);
        factoryBean.setDurability(true);
        return factoryBean;
    }

    /**
     * create SimpleTrigger
     */
    public static SimpleTriggerFactoryBean createSimpleTrigger(
        JobDetail jobDetail, long pollFrequencyMs) {
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
