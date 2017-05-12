package com.study.spring.web;

import com.study.spring.annotation.profile.JpaEnv;
import com.study.spring.entity.jpa.Goods;
import com.study.spring.service.jpa.IGoodsService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jeffrey
 * @since 2017/1/9 15:50
 */
@RestController
@RequestMapping("jpa")
@JpaEnv
public class JpaController {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private IGoodsService goodsService;

    @GetMapping("data_source/class")
    @ApiOperation(value = "查询DataSource类型")
    public String getDataSourceClass() {
        return dataSource.getClass().toString();
    }

    @PostMapping("goods")
    @ApiOperation(value = "增加goods")
    public List<Goods> save(@RequestBody Goods goods) {
        return goodsService.save(goods);
    }

    @DeleteMapping("goods/{id}")
    @ApiOperation(value = "根据id删除goods")
    public List<Goods> deleteById(@PathVariable int id) {
        return goodsService.deleteById(id);
    }

    @GetMapping("goods")
    @ApiOperation(value = "查询所有goods")
    public List<Goods> findAll() {
        return goodsService.findAll();
    }
}
