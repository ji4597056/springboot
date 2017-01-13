package com.study.spring.web;

import com.study.spring.annotation.profile.RedisEnv;
import com.study.spring.entity.Person;
import com.study.spring.service.redis.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
  public List<Person> findPersons(
      @RequestParam(required = false, defaultValue = "true") boolean cache) {
    if (cache == false) {
        return personService.findPersonsNoCache();
    }else {
        return personService.findPersonsWithCache();
    }
  }

  @RequestMapping(value = "/person", method = RequestMethod.POST)
  public void addPersons(@RequestBody List<Person> persons) {
      personService.addPersons(persons);
  }

  @RequestMapping(value = "/person", method = RequestMethod.DELETE)
  public void deletePersonsById(@RequestBody List<Integer> ids) {
      personService.deletePersonsById(ids);
  }
}
