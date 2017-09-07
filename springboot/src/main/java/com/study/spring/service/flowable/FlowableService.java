package com.study.spring.service.flowable;

import com.study.spring.dao.jpa.PersonRepository;
import com.study.spring.entity.jpa.Person;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.impl.persistence.entity.ExecutionEntity;
import org.flowable.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jeffrey
 * @since 2017/07/21 15:52
 */
@Service
@Transactional
public class FlowableService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    public void startProcess(String assignee) {
        Person person = personRepository.findByUserName(assignee);
        Map<String, Object> variables = new HashMap<>();
        variables.put("person", person);
        runtimeService.startProcessInstanceByKey("oneTaskProcess", variables);
    }

    public List<Task> getTasks(String assignee) {
        return taskService.createTaskQuery().taskAssignee(assignee).list();
    }

    public void createDemoUsers() {
        if (personRepository.findAll().size() == 0) {
            personRepository.save(new Person("user1", "group1", "Joram", "Barrez", new Date()));
            personRepository.save(new Person("user2", "group2", "Tijs", "Rademakers", new Date()));
        }
    }

    public void createUsers(List<Person> persons) {
        personRepository.save(persons);
    }

    public List<Person> findAllUsers() {
        return personRepository.findAll();
    }

    public Person findUserByUserName(String userName) {
        return personRepository.findByUserName(userName);
    }

    public List<Person> findUserByGroup(String userGroup) {
        return personRepository.findByUserGroup(userGroup);
    }

}
