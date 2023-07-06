package com.ch.activity.testOracle.controller;


import com.ch.activity.testOracle.entity.ActGeBytearray;
import com.ch.activity.testOracle.service.IActGeBytearrayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ch
 * @since 2023-07-06
 */
@Slf4j
@RestController
@RequestMapping("/testOracle/act-ge-bytearray")
public class ActGeBytearrayController {

    @Resource
    private IActGeBytearrayService iActGeBytearrayService;

    @GetMapping("/a")
    public ActGeBytearray getActGeBytearray() {
        ActGeBytearray actGeBytearray = new ActGeBytearray().setId("62a2c32d-c1fa-11ea-b7f1-000c297d8a0a");
        ActGeBytearray byId = this.iActGeBytearrayService.getById(actGeBytearray);
        log.info("oracle查询结果：{}",byId);
        return byId;
    }
}
