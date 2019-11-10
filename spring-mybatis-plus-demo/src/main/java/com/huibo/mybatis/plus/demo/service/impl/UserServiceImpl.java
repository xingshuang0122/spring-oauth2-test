package com.huibo.mybatis.plus.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.huibo.mybatis.plus.demo.constant.ExceptionMessage;
import com.huibo.mybatis.plus.demo.constant.UserConstant;
import com.huibo.mybatis.plus.demo.entity.User;
import com.huibo.mybatis.plus.demo.entity.UserRole;
import com.huibo.mybatis.plus.demo.exceptions.CustomException;
import com.huibo.mybatis.plus.demo.mapper.UserMapper;
import com.huibo.mybatis.plus.demo.service.IRoleService;
import com.huibo.mybatis.plus.demo.service.IUserRoleService;
import com.huibo.mybatis.plus.demo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huibo.mybatis.plus.demo.common.PageWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author xingshuang
 * @since 2019-11-09
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final IUserRoleService userRoleService;

    private final IRoleService roleService;

    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(BCryptPasswordEncoder encoder,
                           IUserRoleService userRoleService,
                           IRoleService roleService) {
        Preconditions.checkNotNull(encoder);
        Preconditions.checkNotNull(userRoleService);
        Preconditions.checkNotNull(roleService);

        this.encoder = encoder;
        this.userRoleService = userRoleService;
        this.roleService = roleService;
    }

    //region 公有接口方法

    @Override
    public PageWrapper queryByPage(Long page, Long size, Long userId, String username) {
        Preconditions.checkArgument(page > 0, ExceptionMessage.INVALID_PARAM + "[page]");
        Preconditions.checkArgument(size > 0, ExceptionMessage.INVALID_PARAM + "[size]");
        Preconditions.checkArgument(userId > 0, ExceptionMessage.INVALID_PARAM + "[size]");
        log.debug("按页查询用户信息，page={}，size={}，userId={}，username={}", page, size, userId, username);

        Page<User> p = new Page<>(page, size);
        LambdaQueryWrapper<User> condition = Wrappers.<User>lambdaQuery()
                // 只有超级管理员，才能查看所有管理员列表
                .eq(userId != UserConstant.SUPER_ADMIN, User::getCreateUserId, userId)
                // 如果有查询用户名，则针对该用户名进行查询
                .eq(!Strings.isNullOrEmpty(username), User::getUsername, username);
        return new PageWrapper(this.page(p, condition));
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveUser(User user) {
        Preconditions.checkNotNull(user, ExceptionMessage.NULL_POINT + "[user]");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(user.getPassword()), ExceptionMessage.INVALID_PARAM + "[password]");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(user.getUsername()), ExceptionMessage.INVALID_PARAM + "[username]");
        log.debug("保存用户信息，user={}", user);

        // 重名校验，已通过数据库唯一键的形式进行校验，无需采用代码验证
        user.setPassword(this.encoder.encode(user.getPassword()));
        // 保存用户
        this.save(user);
        // 校验添加角色是否都是该用户创建的
        this.checkRolesCreator(user);
        // 如果有角色id，就添加角色信息
        if (user.getRoleIdList() != null && !user.getRoleIdList().isEmpty()) {
            this.userRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean update(User user) {
        Preconditions.checkNotNull(user, ExceptionMessage.NULL_POINT + "[user]");
        Preconditions.checkNotNull(user.getUserId(), ExceptionMessage.NULL_POINT + "[userId]");
        log.debug("更新用户信息，user={}", user);

        // 重名校验，已通过数据库唯一键的形式进行校验，无需采用代码验证
        // 密码为空则不进行修改
        if (Strings.isNullOrEmpty(user.getPassword())) {
            user.setPassword(null);
        } else {
            user.setPassword(this.encoder.encode(user.getPassword()));
        }
        // 更新用户
        this.updateById(user);
        // 校验添加角色是否都是该用户创建的
        this.checkRolesCreator(user);
        // 如果有角色id，就添加角色信息
        if (user.getRoleIdList() != null && !user.getRoleIdList().isEmpty()) {
            this.userRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteBatch(List<Long> userIds) {
        Preconditions.checkNotNull(userIds, ExceptionMessage.NULL_POINT + "[userIds]");
        Preconditions.checkArgument(!userIds.isEmpty(), ExceptionMessage.INVALID_PARAM + "[userIds]");
        log.debug("批量删除用户信息，userIds={}", userIds);

        // 移除用户角色关联表信息
        this.userRoleService.remove(Wrappers.<UserRole>lambdaQuery().in(UserRole::getUserId, userIds));
        return this.removeByIds(userIds);
    }

    //endregion


    //region 私有方法

    /**
     * 校验添加角色是否都是该用户创建的，如果是超级管理员，则不要校验
     */
    private void checkRolesCreator(User user) {
        if (user.getRoleIdList() == null || user.getRoleIdList().isEmpty()) {
            return;
        }
        // 如果不是超级管理员，则需要判断用户的角色是否自己创建
        if (user.getCreateUserId() == UserConstant.SUPER_ADMIN) {
            return;
        }
        // 查询用户创建的角色列表
        List<Long> roleIdList = this.roleService.queryRoleIdList(user.getCreateUserId());
        // 判断是否越权
        if (!roleIdList.containsAll(user.getRoleIdList())) {
            throw new CustomException("新增用户所选角色，不是本人创建");
        }
    }

    //endregion
}
