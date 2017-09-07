package com.study.spring.service.redis.impl;

import com.study.spring.annotation.profile.RedisEnv;
import com.study.spring.dao.redis.IPersonMapper;
import com.study.spring.entity.Student;
import com.study.spring.service.redis.IPersonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jeffrey
 * @since 2017/01/11 16:25
 */
@Service
@Transactional
@RedisEnv
public class PersonServiceImpl implements IPersonService {

    @Autowired
    private IPersonMapper personMapper;

    @Override
    public List<Student> findPersonsNoCache() {
        return personMapper.findPersons();
    }

    @Override
    @Cacheable(value = "personCache", key = "'person'", unless = "#result==null")
    public List<Student> findPersonsWithCache() {
        return personMapper.findPersons();
    }

    @Override
    @CacheEvict(value = "personCache", key = "'person'", condition = "#students!=null", beforeInvocation = true)
    public void addPersons(List<Student> students) {
        personMapper.addPersons(students);
    }

    @Override
    public void deletePersonsById(List<Integer> ids) {
        personMapper.deletePersonByIds(ids);
    }
}
