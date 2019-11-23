package com.huibo.mybatis.plus.demo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.base.Preconditions;
import com.huibo.mybatis.plus.demo.constant.ExceptionMessage;
import com.huibo.mybatis.plus.demo.entity.RoleMenu;
import com.huibo.mybatis.plus.demo.entity.UserRole;
import com.huibo.mybatis.plus.demo.mapper.RoleMenuMapper;
import com.huibo.mybatis.plus.demo.service.IRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色与菜单对应关系 服务实现类
 * </p>
 *
 * @author xingshuang
 * @since 2019-11-09
 */
@Slf4j
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

    @Override
    public List<Long> queryMenuIdList(Long roleId) {
        log.debug("根据角色id查询菜单id列表，roleId={}", roleId);

        List<RoleMenu> userRoles = this.baseMapper.selectList(Wrappers.<RoleMenu>lambdaQuery()
                .select(RoleMenu::getMenuId)
                .eq(RoleMenu::getRoleId, roleId));
        return userRoles.stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
    }

    @Override
    public Boolean saveOrUpdate(Long roleId, List<Long> menuIdList) {
        Preconditions.checkNotNull(menuIdList, ExceptionMessage.NULL_POINT + "[menuIdList]");
        Preconditions.checkArgument(!menuIdList.isEmpty(), ExceptionMessage.COUNT_ZERO + "[menuIdList]");
        log.debug("保存或更新角色和菜单关系表，roleId={}，menuIdList={}", roleId, menuIdList);

        // 移除原来的相关数据
        this.remove(Wrappers.<RoleMenu>lambdaQuery().eq(RoleMenu::getRoleId, roleId));
        List<RoleMenu> userRoleList = menuIdList.stream()
                .map(menuId -> new RoleMenu(roleId, menuId)).collect(Collectors.toList());
        return this.saveOrUpdateBatch(userRoleList);
    }
}
