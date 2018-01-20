package com.study.spring.common.rabbit;

import com.study.spring.annotation.profile.RabbitEnv;
import com.study.spring.config.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Jeffrey
 * @since 2017/09/28 11:37
 */
@Component
@RabbitListener(queues = RabbitConfig.RABBIT_EXCHANGE_QUEUE_2)
@RabbitEnv
public class RabbitExchangeReceiver2 {

    private static final Logger logger = LoggerFactory.getLogger(RabbitExchangeReceiver2.class);

    private static final StringBuilder buffer = new StringBuilder();

    @RabbitHandler
    public void process(Message message) {
        String msg = new String(message.getBody());
        logger.info("(RabbitExchangeReceiver2-process)Receiver : " + msg);
        buffer.append(msg).append("_2_");
    }

    public String show() {
        return buffer.toString();
    }

}
