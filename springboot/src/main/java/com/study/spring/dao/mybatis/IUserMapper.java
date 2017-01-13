package com.study.spring.dao.mybatis;

import com.study.spring.entity.mybatis.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Jeffrey
 * @since 2017/1/9 15:46
 */
@Mapper
public interface IUserMapper {

  User findUserById(@Param("id") Integer id);

  List<User> findAllUsers();
}
