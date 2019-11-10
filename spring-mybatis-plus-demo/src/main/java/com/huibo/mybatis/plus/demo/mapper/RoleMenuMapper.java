package com.huibo.mybatis.plus.demo.mapper;

import com.huibo.mybatis.plus.demo.entity.RoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 角色与菜单对应关系 Mapper 接口
 * </p>
 *
 * @author xingshuang
 * @since 2019-11-09
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    @Select("select menu_id from sys_role_menu where role_id = #{roleId}")
    List<Long> queryMenuIdList(Long roleId);
}
