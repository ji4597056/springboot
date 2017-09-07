package com.study.spring.web;

import com.study.spring.entity.Student;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jeffrey
 * @since 2017/1/9 15:50
 */
@RestController
@RequestMapping(value = "demo")
public class DemoController {

    @Value("${server.port}")
    private String port;

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
