package com.study.spring.common.rabbit;

import com.study.spring.annotation.profile.RabbitEnv;
import com.study.spring.common.constant.SysConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Jeffrey
 * @since 2017/05/27 16:37
 */
@Component
@RabbitListener(queues = SysConstant.RABBIT_DEFAULT_QUEUE)
@RabbitEnv
public class RabbitReceiver {

    private static final Logger logger = LoggerFactory.getLogger(RabbitReceiver.class);

    private static final StringBuilder buffer = new StringBuilder();

    @RabbitHandler
    public void process(String message) {
        logger.info("Receiver : " + message);
        buffer.append(message).append("/");
    }

    public String show() {
        return buffer.toString();
    }
}
