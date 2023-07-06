package com.ch.activity.test.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2023-07-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("users")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "username", type = IdType.ASSIGN_ID)
    private String username;

    @TableField("password")
    private String password;

    @TableField("enabled")
    private Boolean enabled;


}
