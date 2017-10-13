package com.study.spring.common.rabbit;

import com.study.spring.annotation.profile.RabbitEnv;
import com.study.spring.config.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Jeffrey
 * @since 2017/09/28 11:06
 */
@Component
@RabbitListener(queues = RabbitConfig.RABBIT_DEFAULT_QUEUE)
@RabbitEnv
public class RabbitReceiver2 {

    private static final Logger logger = LoggerFactory.getLogger(RabbitReceiver2.class);

    private static final StringBuilder buffer = new StringBuilder();

    @RabbitHandler
    public void process(String message) {
        logger.info("(RabbitReceiver2-process)Receiver : " + message);
        buffer.append(message).append("_2_");
    }

    public String show() {
        return buffer.toString();
    }
}
