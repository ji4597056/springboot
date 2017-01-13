package com.study.spring.entity.mybatis.model;

import java.util.Date;

/**
 * @author Jeffrey
 * @since 2017/1/9 15:47
 */
public class OrderInfoModel {

  private String id;

  private int goodsId;

  private String productName;

  private int userId;

  private String userName;

  private Integer salesNum;

  private Date createDate;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getGoodsId() {
    return goodsId;
  }

  public void setGoodsId(int goodsId) {
    this.goodsId = goodsId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Integer getSalesNum() {
    return salesNum;
  }

  public void setSalesNum(Integer salesNum) {
    this.salesNum = salesNum;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("OrderInfoModel{");
    sb.append("id='").append(id).append('\'');
    sb.append(", goodsId=").append(goodsId);
    sb.append(", productName='").append(productName).append('\'');
    sb.append(", userId=").append(userId);
    sb.append(", userName='").append(userName).append('\'');
    sb.append(", salesNum=").append(salesNum);
    sb.append(", createDate=").append(createDate);
    sb.append('}');
    return sb.toString();
  }
}
