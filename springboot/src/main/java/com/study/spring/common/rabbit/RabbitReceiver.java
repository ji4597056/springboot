package com.study.spring.common.rabbit;

import com.rabbitmq.client.Channel;
import com.study.spring.annotation.profile.RabbitEnv;
import com.study.spring.config.RabbitConfig;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * @author Jeffrey
 * @since 2017/05/27 16:37
 */
@Component
@RabbitListener(queues = RabbitConfig.RABBIT_DEFAULT_QUEUE)
@RabbitEnv
public class RabbitReceiver {

    private static final Logger logger = LoggerFactory.getLogger(RabbitReceiver.class);

    private static final StringBuilder buffer = new StringBuilder();

    @RabbitHandler
    public void process(Message message, @Header(AmqpHeaders.DELIVERY_TAG) long tag,
        Channel channel) {
        String msg = new String(message.getBody());
        logger.info("(RabbitReceiver-process)Receiver : " + msg);
        buffer.append(msg).append("_1_");
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String show() {
        return buffer.toString();
    }
}
