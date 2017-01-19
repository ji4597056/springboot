package com.study.spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.annotations.ApiIgnore;

/**
 * admin Controller
 *
 * @author Jeffrey
 * @since 2017/01/16 12:33
 */
@Controller
@RequestMapping("/admin")
@ApiIgnore
public class AdminController {

  /**
   * 跳转到swagger-ui页面,该url不需要被swagger查询到
   *
   * @return
   */
  @RequestMapping(value = "api", method = RequestMethod.GET)
  public String redirectSwaggerApi() {
    return "redirect:/swagger-ui.html";
  }
}
