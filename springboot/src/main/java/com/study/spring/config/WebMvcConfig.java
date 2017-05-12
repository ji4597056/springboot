package com.study.spring.config;

import com.study.spring.annotation.profile.IgnoreEnv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.context.request.async.CallableProcessingInterceptor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * spring mvc 异步模式
 *
 * @author Jeffrey
 * @since 2017/05/12 13:32
 */
@Configuration
@IgnoreEnv
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private CallableProcessingInterceptor callableInterceptor;

    public ThreadPoolTaskExecutor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(1000);
        executor.setQueueCapacity(2000);
        executor.setKeepAliveSeconds(30000);
        executor.setThreadNamePrefix("request-task-");
        executor.initialize();
        return executor;
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setTaskExecutor(getAsyncExecutor());
//		configurer.registerCallableInterceptors(callableInterceptor);
        super.configureAsyncSupport(configurer);
    }
}
