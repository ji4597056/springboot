package com.study.spring.service.redis;

import com.study.spring.entity.Person;

import java.util.List;

/**
 * @author Jeffrey
 * @since 2017/01/11 16:09
 */
public interface IPersonService {

  List<Person> findPersonsNoCache();

  List<Person> findPersonsWithCache();

  void addPersons(List<Person> persons);

  void deletePersonsById(List<Integer> ids);
}
