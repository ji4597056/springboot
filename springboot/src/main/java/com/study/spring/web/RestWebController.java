package com.study.spring.web;

import com.study.spring.entity.Person;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Jeffrey
 * @since 2017/04/17 17:00
 */
@RestController
@RequestMapping("rest")
public class RestWebController {

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("base")
    @ApiOperation(value = "restTemplate被转发请求")
    public ResponseEntity base() {
        return ResponseEntity.ok(new Person(1, "测试"));
    }

    @GetMapping("/test/get")
    @ApiOperation(value = "rest转发请求")
    public ResponseEntity test() throws IOException {
        return restTemplate
            .getForEntity("http://localhost:8888/rest/base", Person.class, null, null);
    }

    @GetMapping("/test/exchange")
    @ApiOperation(value = "rest转发请求")
    public ResponseEntity test1() throws IOException {
        return restTemplate.exchange("http://localhost:8888/demo/hello",
            HttpMethod.GET, null, String.class);
    }
}
