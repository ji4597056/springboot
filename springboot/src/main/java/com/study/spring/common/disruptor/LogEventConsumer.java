package com.study.spring.common.disruptor;

import com.lmax.disruptor.WorkHandler;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 事件对象消费者
 *
 * @author Jeffrey
 * @since 2017/07/12 10:25
 */
public class LogEventConsumer implements WorkHandler<LogEvent> {

    private static List<LogEvent> logEvents = new CopyOnWriteArrayList<>();

    private static final Logger logger = LoggerFactory.getLogger(LogEventConsumer.class);

    @Override
    public void onEvent(LogEvent event) throws Exception {
        logEvents.add(event);
        logger.info(
            String.format("thread id : %s, event : %s", Thread.currentThread().getId(), event));
    }

    public static List<LogEvent> getLogEvents() {
        return logEvents;
    }

}
