package com.study.spring.dao.mybatis;

import com.study.spring.entity.mybatis.OrderInfo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Jeffrey
 * @since 2017/1/9 15:46
 */
@Mapper
public interface IOrderInfoMapper {

    OrderInfo findOrderInfoById(@Param("id") String id);

    List<OrderInfo> findAllOrderInfo();
}
