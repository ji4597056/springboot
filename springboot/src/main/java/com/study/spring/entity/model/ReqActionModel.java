package com.study.spring.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.study.spring.enums.ReqAction;

/**
 * @author Jeffrey
 * @since 2017/1/9 15:47
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqActionModel {

  @JsonProperty(value = "action")
  ReqAction action;

  @JsonProperty(value = "body")
  Object body;

  public ReqActionModel() {}

  public ReqActionModel(ReqAction action, Object body) {
    this.action = action;
    this.body = body;
  }

  public ReqAction getAction() {
    return action;
  }

  public void setAction(ReqAction action) {
    this.action = action;
  }

  public Object getBody() {
    return body;
  }

  public void setBody(Object body) {
    this.body = body;
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
