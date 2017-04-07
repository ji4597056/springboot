package com.study.spring.dao.jpa;

import com.study.spring.annotation.profile.JpaEnv;
import com.study.spring.entity.jpa.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jeffrey
 * @since 2017/1/9 15:46
 */
@JpaEnv
public interface IOrderInfoDao extends JpaRepository<OrderInfo, String> {

}
