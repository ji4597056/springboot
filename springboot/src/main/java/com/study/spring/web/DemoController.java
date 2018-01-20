package com.study.spring.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.study.spring.entity.Student;
import com.study.spring.util.JSONUtil;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Jeffrey
 * @since 2017/1/9 15:50
 */
@RestController
@RequestMapping(value = "demo")
public class DemoController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${server.port}")
    private String port;

    @GetMapping("rest")
    @ApiOperation(value = "test rest")
    public String testRest(@RequestParam String name, @RequestParam String namespace) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        headers.add("X-Persistent-Token", "f1c4de6970284d1eade81a38a51ea2fa");
        Map<String, Object> map = new HashMap<>(3);
        map.put("name", name);
        map.put("namespace", namespace);
        HttpEntity<String> entity = new HttpEntity<>(JSONUtil.obj2json(map), headers);
        String result = restTemplate
            .postForObject("http://172.24.6.223:30001/ci/projects", entity, String.class);
        return result;
    }

    @GetMapping("hello")
    @ApiOperation(value = "hello springboot")
    public String getHelloWorld() {
        return "Hello Springboot!";
    }

    @GetMapping("port")
    public String getPort() {
        return port;
    }

    @GetMapping("person")
    @ApiOperation(value = "查询person信息")
    public Student getPerson() {
        Student student = new Student(1, "张三");
        return student;
    }

    @PostMapping("person")
    @ApiOperation(value = "传入person信息并打印")
    public Student postPerson(@RequestBody Student student) {
        return student;
    }

    @GetMapping("persons")
    @ApiOperation(value = "查询所有person信息")
    public List<Student> getPersons() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "张三"));
        students.add(new Student(2, "李四"));
        return students;
    }

    @GetMapping("error")
    @ApiOperation(value = "打印错误信息")
    public String getError() {
        return "出错啦,你个笨蛋~";
    }
}
