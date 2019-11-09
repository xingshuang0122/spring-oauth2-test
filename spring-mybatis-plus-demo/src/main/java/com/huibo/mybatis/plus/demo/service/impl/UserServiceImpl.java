package com.huibo.mybatis.plus.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.huibo.mybatis.plus.demo.constant.ExceptionMessage;
import com.huibo.mybatis.plus.demo.entity.User;
import com.huibo.mybatis.plus.demo.exceptions.CustomException;
import com.huibo.mybatis.plus.demo.mapper.UserMapper;
import com.huibo.mybatis.plus.demo.service.IUserRoleService;
import com.huibo.mybatis.plus.demo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huibo.mybatis.plus.demo.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author xingshuang
 * @since 2019-11-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private IUserRoleService userRoleService;

    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(BCryptPasswordEncoder encoder,
                           IUserRoleService userRoleService) {
        Preconditions.checkNotNull(encoder);
        Preconditions.checkNotNull(userRoleService);

        this.encoder = encoder;
        this.userRoleService = userRoleService;
    }

    @Override
    public PageUtils queryByPage(Long page, Long size, Long userId, String username) {
        Page<User> p = new Page<>(page, size);
        LambdaQueryWrapper<User> condition = Wrappers.<User>lambdaQuery()
                .eq(userId != 1, User::getUserId, userId)
                .eq(!Strings.isNullOrEmpty(username), User::getUsername, username);
        return new PageUtils(this.page(p, condition));
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean saveUser(User user) {
        Preconditions.checkNotNull(user, ExceptionMessage.INVALID_PARAM + "[user]");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(user.getPassword()), ExceptionMessage.INVALID_PARAM + "[password]");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(user.getUsername()), ExceptionMessage.INVALID_PARAM + "[username]");
        // 重名校验
        int count = this.count(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));
        if (count == 1) {
            throw new CustomException(ExceptionMessage.USERNAME_EXIST);
        }
        // 保存用户
        user.setPassword(this.encoder.encode(user.getPassword()));
        this.save(user);
        // 如果有角色id，就添加角色信息
        if (user.getRoleIdList() != null && !user.getRoleIdList().isEmpty()) {
            this.userRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
        }
        return true;
    }
}
