package com.ch.activity.testOracle.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.sql.Blob;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ch
 * @since 2023-07-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ACT_GE_BYTEARRAY")
public class ActGeBytearray implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID_")
    private String id;

    @TableField("REV_")
    private BigDecimal rev;

    @TableField("NAME_")
    private String name;

    @TableField("DEPLOYMENT_ID_")
    private String deploymentId;

    @TableField("BYTES_")
    private Blob bytes;

    @TableField("GENERATED_")
    private Integer generated;


}
