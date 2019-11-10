package com.huibo.mybatis.plus.demo.service;

import com.huibo.mybatis.plus.demo.entity.RoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色与菜单对应关系 服务类
 * </p>
 *
 * @author xingshuang
 * @since 2019-11-09
 */
public interface IRoleMenuService extends IService<RoleMenu> {

    List<Long> queryMenuIdList(Long roleId);

    Boolean saveOrUpdate(Long roleId, List<Long> menuIdList);

}
