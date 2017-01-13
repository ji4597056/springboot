package com.study.spring.web;

import com.study.spring.annotation.profile.JpaEnv;
import com.study.spring.entity.jpa.Goods;
import com.study.spring.service.jpa.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author Jeffrey
 * @since 2017/1/9 15:50
 */
@RestController
@RequestMapping("jpa")
@JpaEnv
public class JpaController {

  @Autowired private DataSource dataSource;

  @Autowired private IGoodsService goodsService;

  @RequestMapping(value = "data_source/class", method = RequestMethod.GET)
  public String getDataSourceClass() {
    return dataSource.getClass().toString();
  }

  @RequestMapping(value = "goods", method = RequestMethod.POST)
  public List<Goods> save(@RequestBody Goods goods) {
    return goodsService.save(goods);
  }

  @RequestMapping(value = "goods/{id}", method = RequestMethod.DELETE)
  public List<Goods> deleteById(@PathVariable int id) {
    return goodsService.deleteById(id);
  }

  @RequestMapping(value = "goods", method = RequestMethod.GET)
  public List<Goods> findAll() {
    return goodsService.findAll();
  }
}
