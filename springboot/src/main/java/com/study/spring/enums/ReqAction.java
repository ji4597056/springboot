package com.study.spring.enums;

/**
 * @author Jeffrey
 * @since 2017/1/9 15:48
 */
public enum ReqAction {
  //批量删除
  DELETE_BATCHES("delete_batches"),
  //暂停job
  PAUSE_JOB("pause_job"),
  //重启job
  RESUME_JOB("resume_job"),
  //运行job
  RUN_JOB("run_job");

  private final String value;

  ReqAction(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
