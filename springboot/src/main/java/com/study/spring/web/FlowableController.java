package com.study.spring.web;

import com.study.spring.entity.jpa.Person;
import com.study.spring.service.flowable.FlowableService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.flowable.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jeffrey
 * @since 2017/07/21 15:53
 */
@RestController
@RequestMapping("flowable")
public class FlowableController {

    @Autowired
    private FlowableService flowableService;

    @RequestMapping(value = "/process", method = RequestMethod.POST)
    public void startProcessInstance(
        @RequestBody StartProcessRepresentation startProcessRepresentation) {
        flowableService.startProcess(startProcessRepresentation.getAssignee());
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TaskRepresentation> getTasks(@RequestParam String assignee) {
        List<Task> tasks = flowableService.getTasks(assignee);
        List<TaskRepresentation> dtos = new ArrayList<>();
        for (Task task : tasks) {
            dtos.add(new TaskRepresentation(task.getId(), task.getName()));
        }
        return dtos;
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public void createUsers(@RequestBody List<Person> persons) {
        flowableService.createUsers(persons);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<Person> findUsers(@RequestParam(required = false) String userName) {
        if (userName != null) {
            return Stream.of(flowableService.findUserByUserName(userName)).collect(
                Collectors.toList());
        } else {
            return flowableService.findAllUsers();
        }
    }

    @RequestMapping(value = "/user/{group}", method = RequestMethod.GET)
    public List<Person> findUserByGroup(@PathVariable String group) {
        return flowableService.findUserByGroup(group);
    }

    static class StartProcessRepresentation {

        private String assignee;

        public String getAssignee() {
            return assignee;
        }

        public void setAssignee(String assignee) {
            this.assignee = assignee;
        }
    }

    static class TaskRepresentation {

        private String id;
        private String name;

        public TaskRepresentation(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
