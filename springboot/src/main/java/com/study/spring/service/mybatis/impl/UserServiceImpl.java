package com.study.spring.service.mybatis.impl;

import com.study.spring.annotation.profile.MybatisEnv;
import com.study.spring.dao.mybatis.IUserMapper;
import com.study.spring.entity.mybatis.User;
import com.study.spring.service.mybatis.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Jeffrey
 * @since 2017/1/9 15:50
 */
@Service
@Transactional
@MybatisEnv
public class UserServiceImpl implements IUserService {

  @Autowired private IUserMapper userMapper;

  @Override
  public User findById(Integer id) {
    return userMapper.findUserById(id);
  }

  @Override
  public List<User> findAll() {
    return userMapper.findAllUsers();
  }
}
