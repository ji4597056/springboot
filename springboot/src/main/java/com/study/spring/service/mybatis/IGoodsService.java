package com.study.spring.service.mybatis;

import com.study.spring.entity.model.SearchResModel;
import com.study.spring.entity.mybatis.Goods;

import java.util.List;

/**
 * @author Jeffrey
 * @since 2017/1/9 15:49
 */
public interface IGoodsService {

  default Goods findById(Integer id) {
    return null;
  }

  default List<Goods> findAll() {
    return null;
  }

  default SearchResModel<List<Goods>> findLikeDes(
      String description, Integer pageNum, Integer pageSize) {
    return null;
  }

  default List<Goods> deleteByIds(List<Integer> ids) {
    return null;
  }
}
