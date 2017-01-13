package com.study.spring.dao;

import com.study.spring.dao.jpa.IGoodsDao;
import com.study.spring.dao.jpa.IOrderInfoDao;
import com.study.spring.dao.jpa.IUserDao;
import com.study.spring.entity.jpa.Goods;
import com.study.spring.entity.jpa.OrderInfo;
import com.study.spring.entity.jpa.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/** Description: Author: Jeffrey Create: 2017/01/03 18:13 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("jpa")
public class IOrderInfoDaoTest {

  @Autowired private IOrderInfoDao orderInfoDao;

  @Autowired private IUserDao userDao;

  @Autowired private IGoodsDao goodsDao;

  @Test
  public void testAdd() {
    Goods goods = goodsDao.findOne(1);
    User user = userDao.findOne(1);
    List<OrderInfo> orderInfoList = new LinkedList<>();
    OrderInfo A = new OrderInfo();
    A.setSalesNum(1);
    A.setGoods(goods);
    A.setUser(user);
    A.setCreateDate(new Date());
    orderInfoList.add(A);
    orderInfoDao.save(orderInfoList);
  }
}
