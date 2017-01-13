package com.study.spring.web;

import com.study.spring.Application;
import com.study.spring.entity.Person;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/** Description: Author: Jeffrey Create: 2016/12/27 15:53 */
@RunWith(SpringRunner.class)
@SpringBootTest(
  classes = Application.class,
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
//@ActiveProfiles("dev")
public class HelloControllerTest {

  @Value("${test.name}")
  private String name;

  @Autowired private HelloController helloController;

  @Autowired private TestRestTemplate restTemplate;

  @Test
  public void getHelloWorld() throws Exception {
    String result = helloController.getHelloWorld();
    Assert.assertEquals("Hello World!", result);
  }

  @Test
  public void getPerson() throws Exception {
    ResponseEntity<Person> entity = restTemplate.getForEntity("/hello/person", Person.class);
    Person person = entity.getBody();
    Assert.assertNotNull(person);
  }

  @Test
  public void postPerson() throws Exception {
    Person postPerson = new Person(1, "张三");
    Person resultPerson = helloController.postPerson(postPerson);
    Assert.assertEquals(postPerson, resultPerson);
  }

  @Test
  public void getPersons() throws Exception {
    List<Person> persons = helloController.getPersons();
    Assert.assertEquals(3, persons.size());
  }

  @Test
  public void getName() throws Exception {
    Assert.assertEquals("张三丰", name);
  }
}
