package com.ch.activity;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ActivityApplicationTests {

    @Test
    void contextLoads() {
        // 1、获取ProcessEngine对象
        // 它会加载classpath目录下的activiti.cfg.xml文件
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        System.out.println(defaultProcessEngine);
    }


    @Test
    private void b() {
        // 2、 手动配置数据源
        // 虽然自动装配以及替我们完成了此项工作，但是也可以通过手动配置，可以不依赖自动配置
        ProcessEngine processEngine = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration()
                .setJdbcDriver("com.mysql.jdbc.Driver")
                .setJdbcUrl("jdbc:mysql://localhost/activity?useUnicode=true&characterEncoding=UTF-8&useSSL=false&nullCatalogMeansCurrent=true")
                .setJdbcUsername("root")
                .setJdbcPassword("mysql@1")
                // 自动维护表 详情见笔记
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE)
                .buildProcessEngine();


    }

}
