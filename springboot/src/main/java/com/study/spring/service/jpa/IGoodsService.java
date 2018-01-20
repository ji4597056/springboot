package com.study.spring.service.jpa;

import com.study.spring.entity.jpa.Goods;
import com.study.spring.entity.jpa.OrderResult;
import java.util.List;

/**
 * @author Jeffrey
 * @since 2017/1/9 15:49
 */
public interface IGoodsService {

    default List<Goods> save(Goods goods) {
        return null;
    }

    default List<Goods> deleteById(Integer id) {
        return null;
    }

    default List<Goods> findAll() {
        return null;
    }

    default List<OrderResult> findAllBy(Integer id) {
        return null;
    }
}
