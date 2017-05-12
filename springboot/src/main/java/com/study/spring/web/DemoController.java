package com.study.spring.web;

import com.study.spring.entity.Person;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jeffrey
 * @since 2017/1/9 15:50
 */
@RestController
@RequestMapping(value = "demo")
public class DemoController {

    @GetMapping("hello")
    @ApiOperation(value = "hello springboot")
    public String getHelloWorld() {
        return "Hello Springboot!";
    }

    @GetMapping("person")
    @ApiOperation(value = "查询person信息")
    public Person getPerson() {
        Person person = new Person(1, "张三");
        return person;
    }

    @PostMapping("person")
    @ApiOperation(value = "传入person信息并打印")
    public Person postPerson(@RequestBody Person person) {
        return person;
    }

    @GetMapping("persons")
    @ApiOperation(value = "查询所有person信息")
    public List<Person> getPersons() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1, "张三"));
        persons.add(new Person(2, "李四"));
        return persons;
    }

    @GetMapping("error")
    @ApiOperation(value = "打印错误信息")
    public String getError() {
        return "出错啦,你个笨蛋~";
    }
}
