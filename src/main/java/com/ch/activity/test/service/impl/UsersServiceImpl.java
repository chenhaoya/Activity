package com.ch.activity.test.service.impl;

import com.ch.activity.d2.mapper.UsersMapper;
import com.ch.activity.test.entity.Users;
import com.ch.activity.test.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2023-07-05
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    @Override
    public String getUser() {
        return this.baseMapper.getUser();
    }
}
