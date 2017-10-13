package com.study.spring.web;

import com.study.spring.annotation.profile.RabbitEnv;
import com.study.spring.common.rabbit.RabbitConvertReceiver;
import com.study.spring.common.rabbit.RabbitExchangeReceiver1;
import com.study.spring.common.rabbit.RabbitExchangeReceiver2;
import com.study.spring.common.rabbit.RabbitReceiver;
import com.study.spring.common.rabbit.RabbitReceiver2;
import com.study.spring.config.RabbitConfig;
import com.study.spring.entity.RabbitMessage;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jeffrey
 * @since 2017/05/27 16:33
 */
@RestController
@RequestMapping("rabbit")
@RabbitEnv
public class RabbitController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitReceiver rabbitReceiver;

    @Autowired
    private RabbitReceiver2 rabbitReceiver2;

    @Autowired
    private RabbitExchangeReceiver1 rabbitExchangeReceiver1;

    @Autowired
    private RabbitExchangeReceiver2 rabbitExchangeReceiver2;

    @Autowired
    private RabbitConvertReceiver rabbitConvertReceiver;

    @PostMapping("/send")
    @ApiOperation(value = "向rabbitmq发送消息")
    public String sendMessage(@RequestBody String message) {
        rabbitTemplate.convertAndSend(RabbitConfig.RABBIT_DEFAULT_QUEUE, message);
        return message;
    }

    @PostMapping("/exchange/send")
    @ApiOperation(value = "向rabbitmq exchange发送消息")
    public String sendExchangeMessage(@RequestBody String message) {
        rabbitTemplate.convertAndSend(RabbitConfig.RABBIT_FANOUT_EXCHANGE, "", message);
        return message;
    }

    @PostMapping("/convert/send")
    @ApiOperation(value = "向rabbitmq发送实体类")
    public RabbitMessage sendConvertMessage(@RequestBody RabbitMessage message) {
        rabbitTemplate.convertAndSend(RabbitConfig.RABBIT_CONVERT_MESSAGE_QUEUE, message);
        return message;
    }

    @GetMapping("/show1")
    @ApiOperation(value = "显示监听rabbitmq的消息(receiver1)")
    public String showMessage() {
        return rabbitReceiver.show();
    }

    @GetMapping("/show2")
    @ApiOperation(value = "显示监听rabbitmq的消息(receiver2)")
    public String showMessage2() {
        return rabbitReceiver2.show();
    }

    @GetMapping("/exchange/show1")
    @ApiOperation(value = "显示监听rabbitmq的消息(exchange-receiver1)")
    public String showExchangeMessage1() {
        return rabbitExchangeReceiver1.show();
    }

    @GetMapping("/exchange/show2")
    @ApiOperation(value = "显示监听rabbitmq的消息(exchange-receiver2)")
    public String showExchangeMessage2() {
        return rabbitExchangeReceiver2.show();
    }

    @GetMapping("/convert/show")
    @ApiOperation(value = "显示监听rabbitmq的消息(convert-receiver)")
    public List<RabbitMessage> showConvertMessage() {
        return rabbitConvertReceiver.show();
    }
}
