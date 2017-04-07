package com.study.spring.entity.mybatis;

import java.util.Date;
import org.apache.ibatis.type.Alias;

/**
 * @author Jeffrey
 * @since 2017/1/9 15:47
 */
@Alias("OrderInfo")
public class OrderInfo {

    private String id;

    private Goods goods;

    private User user;

    private Integer salesNum;

    private Date createDate;

    public OrderInfo() {
    }

    public OrderInfo(Goods goods, User user, Integer salesNum, Date createDate) {
        this.goods = goods;
        this.user = user;
        this.salesNum = salesNum;
        this.createDate = createDate;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        final StringBuilder sb = new StringBuilder("OrderInfo{");
        sb.append("id='").append(id).append('\'');
        sb.append(", goods=").append(goods);
        sb.append(", user=").append(user);
        sb.append(", salesNum=").append(salesNum);
        sb.append(", createDate=").append(createDate);
        sb.append('}');
        return sb.toString();
    }
}
