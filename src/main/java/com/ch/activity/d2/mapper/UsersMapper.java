package com.ch.activity.d2.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ch.activity.test.entity.Users;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2023-07-05
 */
@Mapper
@DS("nacos_config")
public interface UsersMapper extends BaseMapper<Users> {


    String getUser();
}
