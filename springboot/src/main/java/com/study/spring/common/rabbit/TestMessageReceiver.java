package com.study.spring.common.rabbit;

import com.rabbitmq.client.Channel;
import com.study.spring.annotation.profile.RabbitEnv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jeffrey
 * @since 2017/10/16 17:15
 */
@Component
@RabbitEnv
public class TestMessageReceiver extends MessageListenerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(TestMessageReceiver.class);

    private static final StringBuilder buffer = new StringBuilder();

    @Autowired
    private MessageConverter messageConverter;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            logger.info("message:" + messageConverter.fromMessage(message));
            logger.info("(TestMessageReceiver-process)Receiver : " + message);
            buffer.append(new String(message.getBody())).append("_test_");
            // 手动ACK
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String show() {
        return buffer.toString();
    }
}
