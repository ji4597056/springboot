package com.study.spring.filter;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Druid过滤器
 * @author Jeffrey
 * @since 2017/1/9 15:48
 */
@WebFilter(
  filterName = "druidWebStatFilter",
  urlPatterns = "/*",
  initParams = {
    @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")
  }
)
public class DruidStatFilter extends WebStatFilter {}
