package com.study.spring.web;

import com.study.spring.annotation.profile.RedisEnv;
import com.study.spring.entity.Person;
import com.study.spring.service.redis.IPersonService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jeffrey
 * @since 2017/01/10 10:14
 */
@RestController
@RequestMapping("redis")
@RedisEnv
public class RedisController {

    @Autowired
    private IPersonService personService;

    @GetMapping("/person")
    @ApiOperation(value = "查询所有person信息,支持缓存功能")
    public List<Person> findPersons(
        @RequestParam(required = false, defaultValue = "true") boolean cache) {
        if (cache == false) {
            return personService.findPersonsNoCache();
        } else {
            return personService.findPersonsWithCache();
        }
    }

    @PostMapping("/person")
    @ApiOperation(value = "增加person信息,会刷新缓存")
    public void addPersons(@RequestBody List<Person> persons) {
        personService.addPersons(persons);
    }

    @DeleteMapping("person")
    @ApiOperation(value = "根据id批量删除person信息,不会刷新缓存")
    public void deletePersonsById(@RequestBody List<Integer> ids) {
        personService.deletePersonsById(ids);
    }
}
