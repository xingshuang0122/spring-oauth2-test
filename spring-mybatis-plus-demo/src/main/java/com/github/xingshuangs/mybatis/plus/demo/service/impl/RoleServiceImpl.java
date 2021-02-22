package com.github.xingshuangs.mybatis.plus.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xingshuangs.mybatis.plus.demo.constant.ExceptionMessage;
import com.github.xingshuangs.mybatis.plus.demo.entity.Role;
import com.github.xingshuangs.mybatis.plus.demo.exceptions.CustomException;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.github.xingshuangs.mybatis.plus.demo.common.PageWrapper;
import com.github.xingshuangs.mybatis.plus.demo.constant.UserConstant;
import com.github.xingshuangs.mybatis.plus.demo.mapper.RoleMapper;
import com.github.xingshuangs.mybatis.plus.demo.service.IRoleMenuService;
import com.github.xingshuangs.mybatis.plus.demo.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.xingshuangs.mybatis.plus.demo.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author xingshuang
 * @since 2019-11-09
 */
@Slf4j
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    private final IUserService userService;

    private final IRoleMenuService roleMenuService;

    @Autowired
    public RoleServiceImpl(IUserService userService,
                           IRoleMenuService roleMenuService) {
        Preconditions.checkNotNull(userService);
        Preconditions.checkNotNull(roleMenuService);

        this.userService = userService;
        this.roleMenuService = roleMenuService;
    }

    //region 公有方法

    @Override
    public PageWrapper queryByPage(Long page, Long size, String roleName) {
        Preconditions.checkArgument(page > 0, ExceptionMessage.INVALID_PARAM + "[page]");
        Preconditions.checkArgument(size > 0, ExceptionMessage.INVALID_PARAM + "[size]");
        log.debug("按页查询角色信息，page={}，size={}，roleName={}", page, size, roleName);

        Page<Role> p = new Page<>(page, size);
        LambdaQueryWrapper<Role> condition = Wrappers.<Role>lambdaQuery()
                // 如果有查询用户名，则针对该用户名进行查询
                .like(!Strings.isNullOrEmpty(roleName), Role::getRoleName, roleName);
        return new PageWrapper(this.page(p, condition));

    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveRole(Role role) {
        Preconditions.checkNotNull(role, ExceptionMessage.NULL_POINT + "[role]");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(role.getRoleName()), ExceptionMessage.INVALID_PARAM + "[roleName]");
        log.debug("保存用户信息，role={}", role);

        // 保存用户
        this.save(role);

        // 如果有菜单id，就添加菜单信息
        if (role.getMenuIdList() != null && !role.getMenuIdList().isEmpty()) {
            // 校验添加菜单是否都是该用户创建的
            this.checkPermsCreator(role);
            this.roleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean update(Role role) {
        Preconditions.checkNotNull(role, ExceptionMessage.NULL_POINT + "[role]");
        Preconditions.checkNotNull(role.getRoleId(), ExceptionMessage.NULL_POINT + "[roleId]");
        log.debug("更新角色信息，role={}", role);

        this.updateById(role);

        // 如果有菜单id，就添加菜单信息
        if (role.getMenuIdList() != null && !role.getMenuIdList().isEmpty()) {
            // 校验添加菜单是否都是该用户创建的
//            this.checkPermsCreator(role);
            this.roleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
        }
        return true;
    }

    //endregion


    //region 私有方法

    /**
     * 校验添加菜单是否都是该用户创建的，如果是超级管理员，则不要校验
     */
    private void checkPermsCreator(Role role) {
        // 如果不是超级管理员，则需要判断用户的角色是否自己创建
        if (role.getCreateUserId() == UserConstant.SUPER_ADMIN) {
            return;
        }
        // 查询用户创建的菜单列表
        List<Long> menuIdList = this.userService.queryAllMenuId(role.getCreateUserId());
        // 判断是否越权
        if (!menuIdList.containsAll(role.getMenuIdList())) {
            throw new CustomException("新增角色的权限，已超出你的权限范围");
        }
    }

    //endregion
}
