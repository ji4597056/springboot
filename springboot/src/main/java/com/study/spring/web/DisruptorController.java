package com.study.spring.web;

import com.study.spring.common.disruptor.LogEvent;
import com.study.spring.common.disruptor.LogEventHandler;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jeffrey
 * @since 2017/07/12 10:44
 */
@RestController
@RequestMapping("disruptor")
public class DisruptorController {

    @Autowired
    private LogEventHandler logEventHandler;

    @GetMapping("publish_and_get")
    @ApiOperation("通过disruptor发布事件")
    public List<LogEvent> publishAndGet(@RequestParam String content) {
        logEventHandler.publish(content);
        return logEventHandler.getLogEvents();
    }

}
