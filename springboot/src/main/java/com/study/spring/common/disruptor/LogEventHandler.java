package com.study.spring.common.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadFactory;
import org.springframework.stereotype.Component;

/**
 * handler
 *
 * @author Jeffrey
 * @since 2017/07/12 10:36
 */
@Component
public class LogEventHandler {

    private static LogEventProducerWithTranslator producer;

    static {
        LogEventFactory factory = new LogEventFactory();
        int ringBufferSize = 1024;
        ThreadFactory threadFactory = Thread::new;
        Disruptor<LogEvent> disruptor = new Disruptor<>(factory, ringBufferSize, threadFactory,
            ProducerType.MULTI, new BlockingWaitStrategy());
        disruptor.handleEventsWithWorkerPool(new LogEventConsumer(), new LogEventConsumer());
        disruptor.start();
        RingBuffer<LogEvent> ringBuffer = disruptor.getRingBuffer();
        producer = new LogEventProducerWithTranslator(ringBuffer);
    }

    public void publish(String content) {
        producer.onData(new Random().nextLong(), content, new Date());
    }

    public List<LogEvent> getLogEvents() {
        return LogEventConsumer.getLogEvents();
    }

}
