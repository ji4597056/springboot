package com.study.spring.common.rabbit;

import com.study.spring.annotation.profile.IgnoreEnv;
import com.study.spring.annotation.profile.RabbitEnv;
import com.study.spring.config.RabbitConfig;
import com.study.spring.entity.RabbitMessage;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Jeffrey
 * @since 2017/09/28 13:21
 */
@Component
@RabbitListener(queues = RabbitConfig.RABBIT_CONVERT_MESSAGE_QUEUE)
@RabbitEnv
public class RabbitConvertReceiver {

    private static final Logger logger = LoggerFactory.getLogger(RabbitConvertReceiver.class);

    private List<RabbitMessage> rabbitMessages = new ArrayList<>();

    @RabbitHandler
    public void process(Message message) {
        logger.info("(RabbitConvertReceiver-process)Receiver : " + message);
        rabbitMessages.add(null);
    }

    public List<RabbitMessage> show() {
        return rabbitMessages;
    }
}
