package com.study.spring.entity.jpa;

import javax.persistence.*;

/**
 * @author Jeffrey
 * @since 2017/1/9 15:47
 */
@Entity
@Table(
  name = "goods",
  indexes = {@Index(name = "idx_goods_id", columnList = "id")}
)
public class Goods {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "product_name")
  private String productName;

  private String description;

  @Column(name = "stock_num")
  private Integer stockNum;

  public Goods() {}

  public Goods(String productName, String description, Integer stockNum) {
    this.productName = productName;
    this.description = description;
    this.stockNum = stockNum;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getStockNum() {
    return stockNum;
  }

  public void setStockNum(Integer stockNum) {
    this.stockNum = stockNum;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Goods{");
    sb.append("id=").append(id);
    sb.append(", productName='").append(productName).append('\'');
    sb.append(", description='").append(description).append('\'');
    sb.append(", stockNum=").append(stockNum);
    sb.append('}');
    return sb.toString();
  }
}
