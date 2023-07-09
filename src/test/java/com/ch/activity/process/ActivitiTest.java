package com.ch.activity.process;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ChenHao
 * @date 2023/7/9 17:08
 */
@Slf4j
@SpringBootTest
public class ActivitiTest {

    private final String assignee = "zhangsan";

    // 注入流程部署服务接口
    @Resource
    private RepositoryService repositoryService;

    @Test
    public void deployProcess() {
        //【1】 流程部署
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("process/test.bpmn20.xml")
                .name("请假审批流程")
                .deploy();

        System.out.println(deployment.getId());
        System.out.println(deployment.getName());
    }

    @Resource
    private RuntimeService runtimeService;

    //【2】 启动流程实例  可以多个实例 一个请假一个实例
    @Test
    public void startProcess() {
        // test 就是配置文件里面 process 标签的 id
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("test");
        log.info("流程定义id：{}", processInstance.getProcessDefinitionId());
        log.info("流程实例id：{}", processInstance.getId());
        log.info("当前获取id：{}", processInstance.getActivityId());
    }

    @Resource
    private TaskService taskService;

    // 任务启动后  相关负责人可以查看审批任务
    @Test
    public void findTasks() {
        //【3】 查询zhangsan待办的任务
        List<Task> list = taskService.createTaskQuery()
                .taskAssignee(assignee)
                .list();
        list.forEach((e) -> {
            log.info("流程实例id：{}", e.getProcessInstanceId());
            log.info("任务id：{}", e.getId());
            log.info("任务负责人id：{}", e.getAssignee());
            log.info("任务名称：{}", e.getName());
        });
    }

    //【4】 处理当前任务
    @Test
    public void completeTask() {
        // 查询负责人需要处理的任务
        // 返回一条数据
        Task task = taskService.createTaskQuery()
                .taskAssignee(assignee)
                .singleResult();

        // 完成任务   参数为任务id
        taskService.complete(task.getId());
        log.info("处理完成");
        // 任务完成后到下一个节点
    }

    //【5】查询zhnagsan以处理的任务
    @Resource
    private HistoryService historyService;

    @Test
    public void findCompleteTask() {
        List<HistoricTaskInstance> historicTaskInstances = historyService
                .createHistoricTaskInstanceQuery()
                .taskAssignee(assignee)
                .finished()
                .list();

        historicTaskInstances.forEach(e -> {
            log.info("流程实例id：{}", e.getProcessInstanceId());
            log.info("任务id：{}", e.getId());
            log.info("任务负责人id：{}", e.getAssignee());
            log.info("任务名称：{}", e.getName());
        });
    }

    // 【6】查询流程定义信息
    @Test
    public void findProcessDefinitionList() {
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery()
                .orderByProcessDefinitionVersion()
                .desc()
                .list();
        // 流程定义信息
        list.forEach(e -> {
            log.info("流程定义 id：{}", e.getId());
            log.info("流程定义 name：{}", e.getName());
            log.info("流程定义 key：{}", e.getKey());
            log.info("流程定义 version：{}", e.getVersion());
            log.info("流程部署 id：{}", e.getDeploymentId());
        });
    }

    // 【7】删除流程定义
    @Test
    public void deleteDeployment() {
        // 部署id
        String deploymentId = "e4195a6e-1e39-11ee-a741-80fa5b684e6e";
        // 删除流程定义，如果该流程定义已经有流程实例启动则删除时 报错
        repositoryService.deleteDeployment(deploymentId);
        // 设置true 级联删除流程定义，即该流程有流程实例启动也可以删除，设为false非级联删除方式 【默认false
//        repositoryService.deleteDeployment(deploymentId,true);
    }

}