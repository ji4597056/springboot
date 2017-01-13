package com.study.spring.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Jeffrey
 * @since 2017/1/9 15:47
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResModel<T> {

  @JsonProperty(value = "pageSize")
  int pageSize;

  @JsonProperty(value = "pageNum")
  int pageNum;

  @JsonProperty(value = "totalPage")
  long totalPage;

  @JsonProperty("result")
  T result;

  public SearchResModel() {}

  public SearchResModel(int pageSize, int pageNum, long totalPage, T result) {
    this.pageSize = pageSize;
    this.pageNum = pageNum;
    this.totalPage = totalPage;
    this.result = result;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getPageNum() {
    return pageNum;
  }

  public void setPageNum(int pageNum) {
    this.pageNum = pageNum;
  }

  public long getTotalPage() {
    return totalPage;
  }

  public void setTotalPage(long totalPage) {
    this.totalPage = totalPage;
  }

  public T getResult() {
    return result;
  }

  public void setResult(T result) {
    this.result = result;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("SearchResModel{");
    sb.append("pageSize=").append(pageSize);
    sb.append(", pageNum=").append(pageNum);
    sb.append(", totalPage=").append(totalPage);
    sb.append(", result=").append(result);
    sb.append('}');
    return sb.toString();
  }
}
