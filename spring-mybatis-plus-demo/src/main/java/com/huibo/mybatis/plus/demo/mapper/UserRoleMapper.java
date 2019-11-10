package com.huibo.mybatis.plus.demo.mapper;

import com.huibo.mybatis.plus.demo.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 用户与角色对应关系 Mapper 接口
 * </p>
 *
 * @author xingshuang
 * @since 2019-11-09
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 根据用户ID，获取角色ID列表
     */
    @Select("select role_id from sys_user_role where user_id = #{userId}")
    List<Long> queryRoleIdList(Long userId);
}
