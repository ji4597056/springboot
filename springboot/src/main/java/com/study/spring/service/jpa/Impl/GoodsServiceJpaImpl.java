package com.study.spring.service.jpa.Impl;

import com.study.spring.annotation.profile.JpaEnv;
import com.study.spring.dao.jpa.IGoodsDao;
import com.study.spring.entity.jpa.Goods;
import com.study.spring.service.jpa.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Jeffrey
 * @since 2017/1/9 15:49
 */
@Service
@Primary
@Transactional
@JpaEnv
public class GoodsServiceJpaImpl implements IGoodsService {

  @Autowired private IGoodsDao goodsDao;

  @Autowired private JdbcTemplate jdbcTemplate;

  @Override
  public List<Goods> save(Goods goods) {
    goodsDao.save(goods);
    return goodsDao.findAll();
  }

  @Override
  public List<Goods> deleteById(Integer id) {
    String sql = "delete from goods where id=" + id;
    jdbcTemplate.update(sql);
    return goodsDao.findAll();
  }

  @Override
  public List<Goods> findAll() {
    return goodsDao.findAll();
  }
}
