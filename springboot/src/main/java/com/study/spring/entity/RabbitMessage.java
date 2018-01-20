package com.study.spring.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Jeffrey
 * @since 2017/09/28 13:28
 */
public class RabbitMessage {

    private int id;

    private String message;

    private Date startTime;

    public RabbitMessage() {

    }

    public RabbitMessage(int id, String message, Date startTime) {
        this.id = id;
        this.message = message;
        this.startTime = startTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RabbitMessage{");
        sb.append("id=").append(id);
        sb.append(", message='").append(message).append('\'');
        sb.append(", startTime=").append(startTime);
        sb.append('}');
        return sb.toString();
    }
}
