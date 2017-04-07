package com.study.spring.service.mybatis;

import com.study.spring.entity.mybatis.User;
import java.util.List;

/**
 * @author Jeffrey
 * @since 2017/1/9 15:50
 */
public interface IUserService {

    default User findById(Integer id) {
        return null;
    }

    default List<User> findAll() {
        return null;
    }
}
