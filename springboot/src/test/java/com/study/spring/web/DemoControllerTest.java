package com.study.spring.web;

import com.study.spring.Application;
import com.study.spring.entity.Student;
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
public class DemoControllerTest {

  @Value("${test.name}")
  private String name;

  @Autowired private DemoController demoController;

  @Autowired private TestRestTemplate restTemplate;

  @Test
  public void getHelloWorld() throws Exception {
    String result = demoController.getHelloWorld();
    Assert.assertEquals("Hello World!", result);
  }

  @Test
  public void getPerson() throws Exception {
    ResponseEntity<Student> entity = restTemplate.getForEntity("/hello/student", Student.class);
    Student student = entity.getBody();
    Assert.assertNotNull(student);
  }

  @Test
  public void postPerson() throws Exception {
    Student postStudent = new Student(1, "张三");
    Student resultStudent = demoController.postPerson(postStudent);
    Assert.assertEquals(postStudent, resultStudent);
  }

  @Test
  public void getPersons() throws Exception {
    List<Student> students = demoController.getPersons();
    Assert.assertEquals(3, students.size());
  }

  @Test
  public void getName() throws Exception {
    Assert.assertEquals("张三丰", name);
  }
}
