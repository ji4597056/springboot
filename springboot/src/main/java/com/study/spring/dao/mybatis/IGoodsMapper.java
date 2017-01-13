package com.study.spring.dao.mybatis;

import com.study.spring.entity.mybatis.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Jeffrey
 * @since 2017/1/9 15:46
 */
@Mapper
public interface IGoodsMapper {

  Goods findGoodsById(@Param("id") Integer id);

  List<Goods> findAllGoods();

  List<Goods> findGoodsLikeDes(@Param("description") String description);

  void deleteGoodsByIds(@Param("ids") List<Integer> ids);
}
