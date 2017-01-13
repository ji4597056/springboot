package com.study.spring.dao;

import com.study.spring.dao.jpa.IGoodsDao;
import com.study.spring.entity.jpa.Goods;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/** Description: Author: Jeffrey Create: 2017/01/04 13:03 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("jpa")
public class IGoodsDaoTest {

  @Autowired private IGoodsDao goodsDao;

  @Test
  public void testAdd() {
    List<Goods> goodsList = new ArrayList<>();
    Goods A = new Goods("A", "A", 10);
    Goods B = new Goods("B", "B", 10);
    Goods C = new Goods("C", "C", 10);
    goodsList.add(A);
    goodsList.add(B);
    goodsList.add(C);
    goodsDao.save(goodsList);
  }

  public void testDeleteById() {
    Integer id = 1;
    goodsDao.delete(id);
  }
}
