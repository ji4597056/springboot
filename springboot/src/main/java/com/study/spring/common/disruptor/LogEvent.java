package com.study.spring.common.disruptor;

import java.util.Date;

/**
 * 事件对象
 *
 * @author Jeffrey
 * @since 2017/07/12 10:21
 */
public class LogEvent {

    private long logId;

    private String content;

    private Date date;

    public LogEvent() {
    }

    public LogEvent(long logId, String content, Date date) {
        this.logId = logId;
        this.content = content;
        this.date = date;
    }

    public long getLogId() {
        return logId;
    }

    public void setLogId(long logId) {
        this.logId = logId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LogEvent{");
        sb.append("logId=").append(logId);
        sb.append(", content='").append(content).append('\'');
        sb.append(", date=").append(date);
        sb.append('}');
        return sb.toString();
    }

}
