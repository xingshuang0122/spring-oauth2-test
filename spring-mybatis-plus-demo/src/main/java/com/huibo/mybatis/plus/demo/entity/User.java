package com.huibo.mybatis.plus.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统用户
 * </p>
 *
 * @author xingshuang
 * @since 2019-11-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
@ApiModel(description = "用户")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(hidden = true)
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 用户名
     */
    @ApiModelProperty(name = "用户名", example = "test")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(name = "密码", example = "123456")
    private String password;

    /**
     * 真实姓名
     */
    @ApiModelProperty(name = "真实姓名", example = "测试")
    private String realName;

    /**
     * 邮箱
     */
    @ApiModelProperty(name = "邮箱", example = "xing@qq.com")
    private String email;

    /**
     * 手机号
     */
    @ApiModelProperty(name = "手机号", example = "18712341234")
    private String mobile;

    /**
     * 性别 0：女，1：男
     */
    @ApiModelProperty(name = "性别", example = "1")
    private Integer gender;

    /**
     * 状态  0：禁用   1：正常
     */
    @ApiModelProperty(name = "状态", example = "1")
    private Integer status;

    /**
     * 创建者ID
     */
    @ApiModelProperty(hidden = true)
    private Long createUserId;

    /**
     * 创建时间
     */
    @ApiModelProperty(hidden = true)
    private LocalDateTime gmtCreate;

    /**
     * 更新时间
     */
    @ApiModelProperty(hidden = true)
    private LocalDateTime gmtModified;

    /**
     * 角色ID列表
     */
    @TableField(exist = false)
    @ApiModelProperty(name = "角色ID列表", example = "[4,5]")
    private List<Long> roleIdList;
}
