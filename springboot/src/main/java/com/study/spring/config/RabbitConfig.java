package com.study.spring.config;

import com.study.spring.annotation.profile.RabbitEnv;
import com.study.spring.common.rabbit.CustomMessageConvert;
import com.study.spring.common.rabbit.TestMessageReceiver;
import java.util.Random;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.MessageConverter;
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

    /* rabbit 默认队列名 */
    public static final String RABBIT_DEFAULT_QUEUE = "default-queue";
    /* rabbit 默认exchange名 */
    public static final String RABBIT_FANOUT_EXCHANGE = "fanout-exchange";
    /* rabbit 测试exchange名 */
    public static final String RABBIT_TEST_EXCHANGE = "test-exchange";
    /* exchange queue-1 */
    public static final String RABBIT_EXCHANGE_QUEUE_1 = "queue-1";
    /* exchange queue-2 */
    public static final String RABBIT_EXCHANGE_QUEUE_2 = "queue-2";
    /* convert message queue */
    public static final String RABBIT_CONVERT_MESSAGE_QUEUE = "convert-queue";

    @Bean
    public Queue defaultQueue() {
        // 用于测试direct
        return new Queue(RABBIT_DEFAULT_QUEUE);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(RABBIT_FANOUT_EXCHANGE);
    }

    @Bean
    public FanoutExchange testExchange() {
        return new FanoutExchange(RABBIT_TEST_EXCHANGE);
    }

    @Bean
    public Queue exchangeQueue1() {
        return new Queue(RABBIT_EXCHANGE_QUEUE_1);
    }

    @Bean
    public Queue exchangeQueue2() {
        return new Queue(RABBIT_EXCHANGE_QUEUE_2);
    }

    @Bean
    public Queue convertQueue() {
        return new Queue(RABBIT_CONVERT_MESSAGE_QUEUE);
    }

    @Bean
    public Binding bindingExchangeQ1(Queue exchangeQueue1, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(exchangeQueue1).to(fanoutExchange);
    }

    @Bean
    public Binding bindingExchangeQ2(Queue exchangeQueue2, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(exchangeQueue2).to(fanoutExchange);
    }

    @Bean
    public Binding bindingTestExchangeQ1(Queue exchangeQueue1, FanoutExchange testExchange) {
        return BindingBuilder.bind(exchangeQueue1).to(testExchange);
    }

    @Bean
    public SimpleMessageListenerContainer listenerContainer(TestMessageReceiver testMessageReceiver,
        ConnectionFactory connectionFactory, MessageConverter messageConverter) throws Exception {
        String queueName = RABBIT_EXCHANGE_QUEUE_1;
        SimpleMessageListenerContainer simpleMessageListenerContainer =
            new SimpleMessageListenerContainer(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(testMessageReceiver);
        simpleMessageListenerContainer.setMessageConverter(messageConverter);
        // 设置手动 ACK
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return simpleMessageListenerContainer;
    }

    @Bean
    public MessageConverter messageConverter() {
        return new CustomMessageConvert();
    }

    @Bean
    public RabbitMessagingTemplate rabbitMessagingTemplate(RabbitTemplate rabbitTemplate,
        MessageConverter messageConverter) {
        rabbitTemplate.setMessageConverter(messageConverter);
        RabbitMessagingTemplate rabbitMessagingTemplate = new RabbitMessagingTemplate();
        rabbitMessagingTemplate.setRabbitTemplate(rabbitTemplate);
        return rabbitMessagingTemplate;
    }

}
