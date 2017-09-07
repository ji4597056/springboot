package com.study.spring.common.disruptor;

import com.lmax.disruptor.EventTranslatorVararg;
import com.lmax.disruptor.RingBuffer;
import java.util.Date;

/**
 * @author Jeffrey
 * @since 2017/07/12 10:34
 */
public class LogEventProducerWithTranslator {

    private final static EventTranslatorVararg<LogEvent> translator = (logEvent, seq, objs) -> {
        logEvent.setLogId((Long) objs[0]);
        logEvent.setContent((String) objs[1]);
        logEvent.setDate((Date) objs[2]);
    };

    private final RingBuffer<LogEvent> ringBuffer;

    public LogEventProducerWithTranslator(RingBuffer<LogEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(long logId, String content, Date date) {
        this.ringBuffer.publishEvent(translator, logId, content, date);
    }

}
