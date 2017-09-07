package com.study.spring.runner;

import com.study.spring.service.flowable.FlowableService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * flowable runner
 *
 * @author Jeffrey
 * @since 2017/07/21 15:22
 */
@Component
@Order(3)
public class FlowableRunner implements CommandLineRunner {

//    @Autowired
//    private RepositoryService repositoryService;
//
//    @Autowired
//    private RuntimeService runtimeService;
//
//    @Autowired
//    private TaskService taskService;

    @Autowired
    private FlowableService flowableService;

    @Override
    public void run(String... args) throws Exception {
        // create flowable demo user
        flowableService.createDemoUsers();
//        System.out.println(
//            "Number of process definitions : " + repositoryService.createProcessDefinitionQuery()
//                .count());
//        System.out.println("Number of tasks : " + taskService.createTaskQuery().count());
//        runtimeService.startProcessInstanceByKey("oneTaskProcess");
//        System.out.println(
//            "Number of tasks after process start: " + taskService.createTaskQuery().count());
    }
}
