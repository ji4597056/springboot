package com.study.spring.common.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * 事件对象工厂
 *
 * @author Jeffrey
 * @since 2017/07/12 10:23
 */
public class LogEventFactory implements EventFactory<LogEvent> {

    @Override
    public LogEvent newInstance() {
        return new LogEvent();
    }

}
