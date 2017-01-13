package com.study.spring.enums;

/**
 * @author Jeffrey
 * @since 2017/1/9 15:48
 */
public enum ReqAction {
  DELETE_BATCHES("delete_batches");

  private final String value;

  ReqAction(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
