package com.study.spring.common.rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;

/**
 * 自定义MessageConvert
 * 发送消息时转json,接收消息时不转换
 *
 * @author Jeffrey
 * @since 2017/11/04 21:38
 */
public class CustomMessageConvert extends Jackson2JsonMessageConverter {

    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        return message;
    }
}
