package com.study.spring.service.mybatis;

import com.study.spring.entity.mybatis.OrderInfo;

import java.util.List;

/**
 * @author Jeffrey
 * @since 2017/1/9 15:49
 */
public interface IOrderInfoService {

  default OrderInfo findById(String id) {
    return null;
  }

  default List<OrderInfo> findAll() {
    return null;
  }
}
