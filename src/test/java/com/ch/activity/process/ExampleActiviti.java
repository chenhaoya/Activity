package com.ch.activity.process;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author ChenHao
 * @date 2023/7/9 23:01
 */
@Slf4j
@SpringBootTest
public class ExampleActiviti {

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private RuntimeService runtimeService;

    // 【1】创建流程实例，同时指定流程管理的BusinessKey
    @Test
    public void startUpPrecessAddBusinessKey() {
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey("test", "1001");
        log.info("业务key：{}", processInstance.getBusinessKey());
    }

    // 【2】挂起全部流程
    @Test
    public void suspendProcessInstanceAll() {
        log.info("------start------");
        // 1、获取流程定义对象
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("test")
                .singleResult();
        // 2、调用流程定义对象方法 查看状态：挂起/激活
        if (processDefinition.isSuspended()) {
            // 3、挂起-》激活
            // 参数：流程定义id、是否激活、激活日期
            repositoryService.activateProcessDefinitionById(
                    processDefinition.getId(),
                    true,
                    null);
            log.info("激活流程：{}",processDefinition.getId());
        } else {
            // 4、激活-》挂起
            // 参数：流程定义id、是否挂起、挂起时间
            repositoryService.suspendProcessDefinitionById(
                    processDefinition.getId(),
                    true,
                    null
            );
            log.info("挂起流程：{}",processDefinition.getId());
        }
        log.info("------end------");
    }


}