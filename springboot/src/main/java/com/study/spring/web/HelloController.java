package com.study.spring.web;

import com.study.spring.entity.Person;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jeffrey
 * @since 2017/1/9 15:50
 */
@RestController
@RequestMapping(value = "hello")
public class HelloController {

  @RequestMapping(value = "hello", method = RequestMethod.GET)
  public String getHelloWorld() {
    return "Hello World!";
  }

  @RequestMapping(value = "person", method = RequestMethod.GET)
  public Person getPerson() {
    Person Person = new Person(1, "张三");
    return Person;
  }

  @RequestMapping(value = "person", method = RequestMethod.POST)
  public Person postPerson(@RequestBody Person Person) {
    return Person;
  }

  @RequestMapping(value = "persons", method = RequestMethod.GET)
  public List<Person> getPersons() {
    List<Person> Persons = new ArrayList<>();
    Persons.add(new Person(1, "张三"));
    Persons.add(new Person(2, "李四"));
    return Persons;
  }

  @RequestMapping(value = "error", method = RequestMethod.GET)
  public String getError() {
    return "出错啦,你个笨蛋~";
  }
}
