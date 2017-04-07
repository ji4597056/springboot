package com.study.spring.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.study.spring.enums.ReqAction;

/**
 * @author Jeffrey
 * @since 2017/1/9 15:47
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqActionModel<T> {

    @JsonProperty(value = "action")
    private ReqAction action;

    @JsonProperty(value = "body")
    private T body;

    public ReqActionModel() {
    }

    public ReqActionModel(ReqAction action, T body) {
        this.action = action;
        this.body = body;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public ReqAction getAction() {
        return action;
    }

    public void setAction(ReqAction action) {
        this.action = action;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ReqActionModel{");
        sb.append("action=").append(action);
        sb.append(", body=").append(body);
        sb.append('}');
        return sb.toString();
    }
}
