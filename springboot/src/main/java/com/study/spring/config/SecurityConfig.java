package com.study.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * spring security配置
 *
 * @author Jeffrey
 * @since 2017/01/16 14:17
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // 禁用csrf防御
    http.csrf()
        .disable()
        .authorizeRequests()
        .antMatchers("/admin/**")
        .hasRole("ADMIN")
        .anyRequest()
        .permitAll()
        .and()
        .httpBasic()
        .and()
        .logout()
        .deleteCookies("JSESSIONID")
        .permitAll();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
        .withUser("admin")
        .password("admin")
        .roles("ADMIN")
        .and()
        .withUser("user")
        .password("user")
        .roles("USER");
  }
}
