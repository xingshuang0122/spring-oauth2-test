package com.huibo.mybatis.plus.demo.mapper;

import com.huibo.mybatis.plus.demo.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author xingshuang
 * @since 2019-11-09
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 查询用户创建的角色ID列表
     *
     * @param createUserId 创建的userId
     */
    @Select("select role_id from sys_role where create_user_id = #{createUserId}")
    List<Long> queryRoleIdList(Long createUserId);
}
