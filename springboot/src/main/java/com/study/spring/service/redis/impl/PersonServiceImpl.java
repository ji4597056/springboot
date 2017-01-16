package com.study.spring.service.redis.impl;

import com.study.spring.annotation.profile.RedisEnv;
import com.study.spring.dao.redis.IPersonMapper;
import com.study.spring.entity.Person;
import com.study.spring.service.redis.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Jeffrey
 * @since 2017/01/11 16:25
 */
@Service
@Transactional
@RedisEnv
public class PersonServiceImpl implements IPersonService {

  @Autowired private IPersonMapper personMapper;

  @Override
  public List<Person> findPersonsNoCache() {
    return personMapper.findPersons();
  }

  @Override
  @Cacheable(value = "personCache", key = "'person'", unless = "#result==null")
  public List<Person> findPersonsWithCache() {
    return personMapper.findPersons();
  }

  @Override
  @CacheEvict(value = "personCache", key = "'person'", condition = "#persons!=null")
  public void addPersons(List<Person> persons) {
    personMapper.addPersons(persons);
  }

  @Override
  public void deletePersonsById(List<Integer> ids) {
    personMapper.deletePersonByIds(ids);
  }
}
