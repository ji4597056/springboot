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
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jeffrey
 * @since 2017/1/9 15:50
 */
@RestController
@RequestMapping(value = "mybatis")
@MybatisEnv
public class MybatisController {

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private IOrderInfoService orderInfoService;

    @Autowired
    private IUserService userService;

    @GetMapping("/goods/{id}")
    @ApiOperation(value = "根据id查询goods")
    public Goods findGoodsById(@PathVariable Integer id) {
        return goodsService.findById(id);
    }

    @GetMapping("/goods")
    @ApiOperation(value = "查询所有goods(支持分页,description模糊查询)")
    public SearchResModel<List<Goods>> findAllGoods(
        @RequestParam(required = false) String description,
        @RequestParam(required = false, defaultValue = "0") Integer pageNum,
        @RequestParam(required = false, defaultValue = "0") Integer pageSize) {
        return goodsService.findLikeDes(description, pageNum, pageSize);
    }

    @PostMapping("/goods")
    @ApiOperation(value = "根据id批量删除goods")
    public List<Goods> deleteGoodsByIds(@RequestBody ReqActionModel<List<Integer>> reqActionModel) {
        if (reqActionModel.getAction().equals(ReqAction.DELETE_BATCHES)) {
            List<Integer> ids = (List<Integer>) reqActionModel.getBody();
            return goodsService.deleteByIds(ids);
        }
        return null;
    }

    @GetMapping("/order_info/{id}")
    @ApiOperation(value = "根据id查询orderInfo")
    public OrderInfo findOrderInfoById(@PathVariable String id) {
        return orderInfoService.findById(id);
    }

    @GetMapping("/order_info")
    @ApiOperation(value = "查询所有orderInfo")
    public List<OrderInfo> findAllOrderInfo() {
        return orderInfoService.findAll();
    }

    @GetMapping("/user/{id}")
    @ApiOperation(value = "根据id查询user")
    public User findUserById(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @GetMapping("/user")
    @ApiOperation(value = "查询所有user")
    public List<User> findAllUser() {
        return userService.findAll();
    }
}
