package com.study.spring;

import com.study.spring.runner.StartupFirstRunner;
import com.study.spring.runner.StartupSecondRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * spring boot starter
 *
 * @author Jeffrey
 * @since 2017/1/9 15:49
 */
@SpringBootApplication
//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, TransactionAutoConfiguration.class})
//@ServletComponentScan
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public StartupFirstRunner firstRunner() {
        return new StartupFirstRunner();
    }

    @Bean
    public StartupSecondRunner secondRunner() {
        return new StartupSecondRunner();
    }
}
