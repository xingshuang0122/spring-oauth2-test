package com.github.xingshuangs.mybatis.plus.demo.mapper;

import com.github.xingshuangs.mybatis.plus.demo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 系统用户 Mapper 接口
 * </p>
 *
 * @author xingshuang
 * @since 2019-11-09
 */
public interface UserMapper extends BaseMapper<User> {

    @Select("select distinct rm.menu_id from sys_user_role ur LEFT JOIN sys_role_menu rm on ur.role_id = rm.role_id where ur.user_id = #{userId}")
    List<Long> queryAllMenuId(Long userId);
}
