package com.huibo.mybatis.plus.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author xingshuang
 * @since 2019-11-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role")
@ApiModel(description = "角色")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(hidden = true)
    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;

    /**
     * 角色名称
     */
    @ApiModelProperty(name = "角色名称", example = "测试")
    private String roleName;

    /**
     * 备注
     */
    @ApiModelProperty(name = "备注", example = "测试")
    private String remark;

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
     * 修改时间
     */
    @ApiModelProperty(hidden = true)
    private LocalDateTime gmtModified;

    @TableField(exist=false)
    @ApiModelProperty(name = "菜单ID列表", example = "[4,5]")
    private List<Long> menuIdList;
}
