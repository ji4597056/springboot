package com.study.spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
     */
    @GetMapping("api")
    public String redirectSwaggerApi() {
        return "redirect:/swagger-ui.html";
    }
}
