package com.study.spring.dao.jpa;

import com.study.spring.annotation.profile.JpaEnv;
import com.study.spring.entity.jpa.Goods;
import com.study.spring.entity.jpa.OrderResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Jeffrey
 * @since 2017/1/9 15:41
 */
@JpaEnv
public interface IGoodsDao extends JpaRepository<Goods, Integer>, JpaSpecificationExecutor {

}
