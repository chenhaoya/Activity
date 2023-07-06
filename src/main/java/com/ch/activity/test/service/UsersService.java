package com.ch.activity.test.service;

import com.ch.activity.test.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2023-07-05
 */
public interface UsersService extends IService<Users> {

    String getUser();
}
