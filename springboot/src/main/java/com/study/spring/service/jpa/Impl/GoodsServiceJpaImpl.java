package com.study.spring.service.jpa.Impl;

import com.study.spring.annotation.profile.JpaEnv;
import com.study.spring.dao.jpa.IGoodsDao;
import com.study.spring.entity.jpa.Goods;
import com.study.spring.entity.jpa.OrderResult;
import com.study.spring.service.jpa.IGoodsService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jeffrey
 * @since 2017/1/9 15:49
 */
@Service
@Primary
@Transactional
@JpaEnv
public class GoodsServiceJpaImpl implements IGoodsService {

    @Autowired
    private IGoodsDao goodsDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

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

    @Override
    public List<OrderResult> findAllBy(Integer orderId) {
        return goodsDao.findAll((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>(3);
            Optional.ofNullable(orderId)
                .ifPresent(userId -> predicates.add(builder.equal(root.get("id"), orderId)));
            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        });
    }
}