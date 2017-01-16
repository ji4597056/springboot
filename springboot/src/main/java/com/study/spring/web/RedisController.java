package com.study.spring.web;

import com.study.spring.annotation.profile.RedisEnv;
import com.study.spring.entity.Person;
import com.study.spring.service.redis.IPersonService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.MediaSize;
import java.util.List;

/**
 * @author Jeffrey
 * @since 2017/01/10 10:14
 */
@RestController
@RequestMapping("redis")
@RedisEnv
public class RedisController {

  @Autowired private IPersonService personService;

  @RequestMapping(value = "/person", method = RequestMethod.GET)
  @ApiOperation(value = "查询所有person信息,支持缓存功能")
  public List<Person> findPersons(
      @RequestParam(required = false, defaultValue = "true") boolean cache) {
    if (cache == false) {
      return personService.findPersonsNoCache();
    } else {
      return personService.findPersonsWithCache();
    }
  }

  @RequestMapping(value = "/person", method = RequestMethod.POST)
  @ApiOperation(value = "增加person信息,会刷新缓存")
  public void addPersons(@RequestBody List<Person> persons) {
    personService.addPersons(persons);
  }

  @RequestMapping(value = "/person", method = RequestMethod.DELETE)
  @ApiOperation(value = "根据id批量删除person信息,不会刷新缓存")
  public void deletePersonsById(@RequestBody List<Integer> ids) {
    personService.deletePersonsById(ids);
  }
}
