package com.study.spring.service.mybatis.impl;

import com.study.spring.annotation.profile.MybatisEnv;
import com.study.spring.dao.mybatis.IOrderInfoMapper;
import com.study.spring.entity.mybatis.OrderInfo;
import com.study.spring.service.mybatis.IOrderInfoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jeffrey
 * @since 2017/1/9 15:50
 */
@Service
@Transactional
@MybatisEnv
public class OrderInfoServiceImpl implements IOrderInfoService {

    @Autowired
    private IOrderInfoMapper orderMapper;

    @Override
    public OrderInfo findById(String id) {
        return orderMapper.findOrderInfoById(id);
    }

    @Override
    public List<OrderInfo> findAll() {
        return orderMapper.findAllOrderInfo();
    }
}
