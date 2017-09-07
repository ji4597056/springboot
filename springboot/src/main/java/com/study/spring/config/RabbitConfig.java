package com.study.spring.config;

import com.study.spring.annotation.profile.RabbitEnv;
import com.study.spring.common.constant.SysConstant;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq 配置
 *
 * @author Jeffrey
 * @since 2017/05/27 16:50
 */
@Configuration
@RabbitEnv
public class RabbitConfig {

    @Bean
    public Queue defaultQueue() {
        return new Queue(SysConstant.RABBIT_DEFAULT_QUEUE);
    }
}
