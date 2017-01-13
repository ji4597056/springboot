package com.study.spring.config;

import com.study.spring.annotation.profile.IgnoreEnv;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

/**
 * 已经自动配置，无需自己配置
 *
 * @author Jeffrey
 * @since 2017/1/9 15:44
 */
@Configuration
@AutoConfigureAfter(value = DataSource.class)
@IgnoreEnv
public class MybatisConfiguation {

  @Autowired private DataSource dataSource;

  @Bean
  public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource);
    ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    sqlSessionFactoryBean.setMapperLocations(
        resolver.getResources("classpath:mybatis/mapper/*.xml"));
    return sqlSessionFactoryBean.getObject();
  }
}
