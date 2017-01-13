package com.study.spring.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;

import javax.sql.DataSource;

/**
 * @author Jeffrey
 * @since 2017/1/9 15:49
 */
@Order(value = 2)
public class StartupSecondRunner implements CommandLineRunner {

  private static final Logger logger = LoggerFactory.getLogger(StartupSecondRunner.class);

  @Autowired private DataSource dataSource;

  @Override
  public void run(String... strings) throws Exception {
    System.out.println("this is second runner!");
    logger.info("Datasource: {}", dataSource.toString());
  }
}
