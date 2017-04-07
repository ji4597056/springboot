package com.study.spring.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;

/**
 * @author Jeffrey
 * @since 2017/1/9 15:49
 */
@Order(value = 1)
public class StartupFirstRunner implements CommandLineRunner {

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("this is first runner!");
    }
}
