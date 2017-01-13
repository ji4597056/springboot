package com.study.spring.web;

import com.study.spring.annotation.profile.MybatisEnv;
import com.study.spring.entity.model.ReqActionModel;
import com.study.spring.entity.model.SearchResModel;
import com.study.spring.entity.mybatis.Goods;
import com.study.spring.entity.mybatis.OrderInfo;
import com.study.spring.entity.mybatis.User;
import com.study.spring.enums.ReqAction;
import com.study.spring.service.mybatis.IGoodsService;
import com.study.spring.service.mybatis.IOrderInfoService;
import com.study.spring.service.mybatis.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jeffrey
 * @since 2017/1/9 15:50
 */
@RestController
@RequestMapping(value = "mybatis")
@MybatisEnv
public class MybatisController {

  @Autowired private IGoodsService goodsService;

  @Autowired private IOrderInfoService orderInfoService;

  @Autowired private IUserService userService;

  @RequestMapping(value = "/goods/{id}", method = RequestMethod.GET)
  public Goods findGoodsById(@PathVariable Integer id) {
    return goodsService.findById(id);
  }

  @RequestMapping(value = "/goods", method = RequestMethod.GET)
  public SearchResModel<List<Goods>> findAllGoods(
      @RequestParam(required = false) String description,
      @RequestParam(required = false, defaultValue = "0") Integer pageNum,
      @RequestParam(required = false, defaultValue = "0") Integer pageSize) {
    return goodsService.findLikeDes(description, pageNum, pageSize);
  }

  @RequestMapping(value = "/goods", method = RequestMethod.POST)
  public List<Goods> deleteGoodsByIds(@RequestBody ReqActionModel reqActionModel) {
    if (reqActionModel.getAction().equals(ReqAction.DELETE_BATCHES)) {
      List<Integer> ids = (List<Integer>) reqActionModel.getBody();
      return goodsService.deleteByIds(ids);
    }
    return null;
  }

  @RequestMapping(value = "/orderInfo/{id}", method = RequestMethod.GET)
  public OrderInfo findOrderInfoById(@PathVariable String id) {
    return orderInfoService.findById(id);
  }

  @RequestMapping(value = "/orderInfo", method = RequestMethod.GET)
  public List<OrderInfo> findAllOrderInfo() {
    return orderInfoService.findAll();
  }

  @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
  public User findUserById(@PathVariable Integer id) {
    return userService.findById(id);
  }

  @RequestMapping(value = "/user", method = RequestMethod.GET)
  public List<User> findAllUser() {
    return userService.findAll();
  }
}
