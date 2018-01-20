package com.study.spring.entity.jpa;

/**
 * @author Jeffrey
 * @since 2017/12/19 14:00
 */
public class OrderResult {

    private String orderId;

    private Integer salesNum;

    private Integer goodsId;

    private String description;

    public OrderResult() {
    }

    public OrderResult(String orderId, Integer salesNum, Integer goodsId, String description) {
        this.orderId = orderId;
        this.salesNum = salesNum;
        this.goodsId = goodsId;
        this.description = description;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getSalesNum() {
        return salesNum;
    }

    public void setSalesNum(Integer salesNum) {
        this.salesNum = salesNum;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderResult{");
        sb.append("orderId='").append(orderId).append('\'');
        sb.append(", salesNum=").append(salesNum);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
