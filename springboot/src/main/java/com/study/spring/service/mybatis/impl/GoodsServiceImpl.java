package com.study.spring.service.mybatis.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.spring.annotation.profile.MybatisEnv;
import com.study.spring.dao.mybatis.IGoodsMapper;
import com.study.spring.entity.model.SearchResModel;
import com.study.spring.entity.mybatis.Goods;
import com.study.spring.service.mybatis.IGoodsService;
import com.study.spring.util.ResUtil;
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
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    private IGoodsMapper goodsMapper;

    @Override
    public Goods findById(Integer id) {
        return goodsMapper.findGoodsById(id);
    }

    @Override
    public List<Goods> findAll() {
        return goodsMapper.findAllGoods();
    }

    @Override
    public SearchResModel<List<Goods>> findLikeDes(
        String description, Integer pageNum, Integer pageSize) {
        // PageHelper下紧跟mybatis查询方法才线程安全
        PageHelper.startPage(pageNum, pageSize);
        List<Goods> goodsList = goodsMapper.findGoodsLikeDes(description);
        PageInfo page = new PageInfo(goodsList);
        return ResUtil.getSearchRes(page);
    }

    @Override
    public List<Goods> deleteByIds(List ids) {
        goodsMapper.deleteGoodsByIds(ids);
        return goodsMapper.findAllGoods();
    }
}
