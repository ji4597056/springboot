package com.study.spring.tmp;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Jeffrey
 * @since 2017/01/22 10:48
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(value = {"mybatis", "druid"})
public class MybatisTest {

  @Autowired private SqlSessionFactory[] sqlSessionFactorys;

  @Test
  public void testSqlSessionFactoryLength() {
    Assert.assertEquals(true, sqlSessionFactorys.length >= 1);
  }
}
