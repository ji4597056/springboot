package com.study.spring.web;

import com.study.spring.annotation.profile.RabbitEnv;
import com.study.spring.common.constant.SysConstant;
import com.study.spring.common.rabbit.RabbitReceiver;
import io.swagger.annotations.ApiOperation;
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

    @PostMapping("/send")
    @ApiOperation(value = "向rabbitmq发送消息")
    public String sendMessage(@RequestBody String message) {
        try {
            rabbitTemplate.convertAndSend(SysConstant.RABBIT_DEFAULT_QUEUE, message);
            return message;
        } catch (Exception e) {
            throw new RuntimeException("send rabbitmq error!", e);
        }
    }

    @GetMapping("/show")
    @ApiOperation(value = "显示监听rabbitmq的消息")
    public String showMessage() {
        return rabbitReceiver.show();
    }
}
