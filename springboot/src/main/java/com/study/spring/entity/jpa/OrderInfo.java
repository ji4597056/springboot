package com.study.spring.entity.jpa;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author Jeffrey
 * @since 2017/1/9 15:47
 */
@Entity
@Table(name = "orderInfo")
public class OrderInfo {

    @Id
    @GeneratedValue(generator = "order_uuid")
    @GenericGenerator(strategy = "uuid2", name = "order_uuid") //使用uuid的hibernate内置生成策略
    @Column(length = 36)
    private String id;

    @ManyToOne(targetEntity = Goods.class)
    @JoinColumn(name = "goods_id")
    private Goods goods;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "sales_num")
    private Integer salesNum;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
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
