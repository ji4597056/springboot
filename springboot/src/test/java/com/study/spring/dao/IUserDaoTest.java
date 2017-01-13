package com.study.spring.dao;

import com.study.spring.dao.jpa.IUserDao;
import com.study.spring.entity.jpa.User;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/** Description: Author: Jeffrey Create: 2016/12/28 16:54 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("jpa")
public class IUserDaoTest {

  @Autowired private IUserDao userDao;

  /* base test */
  @Test
  public void testAdd() {
    User userA = new User("A", "123", "a", "a123@126.com");
    User userB = new User("B", "123", "b", "b123@126.com");
    User userC = new User("C", "123", "c", "c123@126.com");
    User userD = new User("D", "123", "d", null);
    List<User> users = new ArrayList<>();
    users.add(userA);
    users.add(userB);
    users.add(userC);
    users.add(userD);
    userDao.save(users);
  }

  @Test
  //    @Ignore
  public void testDeleteById() {
    int id = 1;
    userDao.delete(id);
  }

  @Test
  @Ignore
  public void testFindAll() {
    List<User> users = userDao.findAll();
    System.out.println(users);
  }

  @Test
  @Ignore
  public void testFindById() {
    int id = 2;
    User user = userDao.findOne(id);
    System.out.println(user);
  }

  @Test
  @Ignore
  public void testUpdate() {
    int id = 2;
    User user = userDao.findOne(id);
    user.setPassword("789");
    userDao.save(user);
  }

  /* upgrade test */
  @Test
  public void testFindByUserName() throws Exception {
    String userName = "B";
    System.out.println(userDao.findByUserName(userName));
  }

  @Test
  public void testFindByIdAndUserName() throws Exception {
    int id = 1;
    String userName = "A";
    System.out.println(userDao.findByIdAndUserName(id, userName));
  }

  @Test
  public void testFindByUserNameOrEmail() throws Exception {
    String userName = "C";
    String email = "a123@126.com";
    System.out.println(userDao.findByUserNameOrEmail(userName, email));
  }

  @Test
  public void testFindByIdBetween() throws Exception {
    int min = 1;
    int max = 2;
    System.out.println(userDao.findByIdBetween(min, max));
  }

  @Test
  public void testFindByIdLessThan() throws Exception {
    int id = 2;
    System.out.println(userDao.findByIdLessThan(id));
  }

  @Test
  public void testFindByIdGreaterThan() throws Exception {
    int id = 2;
    System.out.println(userDao.findByIdGreaterThan(id));
  }

  @Test
  public void testFindByEmailIsNull() throws Exception {
    System.out.println(userDao.findByEmailIsNull());
  }

  @Test
  public void testFindByEmailNotNull() throws Exception {
    System.out.println(userDao.findByEmailNotNull());
  }

  @Test
  public void testFindByUserNameLike() throws Exception {
    String userName = "A";
    System.out.println(userDao.findByUserNameLike(userName));
  }

  @Test
  public void testFindByUserNameNotLike() throws Exception {
    String userName = "B";
    System.out.println(userDao.findByUserNameNotLike(userName));
  }

  @Test
  public void testFindByIdIn() throws Exception {
    List<Integer> vals = new ArrayList<>();
    vals.add(1);
    vals.add(2);
    System.out.println(userDao.findByIdIn(vals));
  }

  @Test
  public void testFindByIdNotIn() throws Exception {
    List<Integer> vals = new ArrayList<>();
    vals.add(3);
    System.out.println(userDao.findByIdNotIn(vals));
  }

  @Test
  public void testFindByPasswordOrderByIdAsc() throws Exception {
    String password = "123";
    System.out.println(userDao.findByPasswordOrderByIdAsc(password));
  }

  @Test
  public void testFindByPasswordOrderByIdDesc() throws Exception {
    String password = "123";
    System.out.println(userDao.findByPasswordOrderByIdDesc(password));
  }
}
