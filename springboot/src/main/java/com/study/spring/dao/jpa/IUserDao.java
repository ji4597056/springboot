package com.study.spring.dao.jpa;

import com.study.spring.annotation.profile.JpaEnv;
import com.study.spring.entity.jpa.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jeffrey
 * @since 2017/1/9 15:46
 */
@JpaEnv
public interface IUserDao extends JpaRepository<User, Integer> {

    List<User> findByUserName(String userName);

    List<User> findByIdAndUserName(int id, String userName);

    List<User> findByUserNameOrEmail(String userName, String email);

    List<User> findByIdBetween(int min, int max);

    List<User> findByIdLessThan(int val);

    List<User> findByIdGreaterThan(int val);

    List<User> findByEmailIsNull();

    List<User> findByEmailNotNull();

    List<User> findByUserNameLike(String userName);

    List<User> findByUserNameNotLike(String userName);

    List<User> findByIdIn(List<Integer> vals);

    List<User> findByIdNotIn(List<Integer> vals);

    List<User> findByPasswordOrderByIdAsc(String password);

    List<User> findByPasswordOrderByIdDesc(String password);
}
