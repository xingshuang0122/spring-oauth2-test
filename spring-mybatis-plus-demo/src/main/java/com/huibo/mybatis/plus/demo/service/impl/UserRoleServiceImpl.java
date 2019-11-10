package com.huibo.mybatis.plus.demo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.huibo.mybatis.plus.demo.constant.ExceptionMessage;
import com.huibo.mybatis.plus.demo.entity.User;
import com.huibo.mybatis.plus.demo.entity.UserRole;
import com.huibo.mybatis.plus.demo.mapper.UserRoleMapper;
import com.huibo.mybatis.plus.demo.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户与角色对应关系 服务实现类
 * </p>
 *
 * @author xingshuang
 * @since 2019-11-09
 */
@Slf4j
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Override
    public Boolean saveOrUpdate(Long userId, List<Long> roleIdList) {
        Preconditions.checkNotNull(roleIdList, ExceptionMessage.NULL_POINT + "[roleIdList]");
        Preconditions.checkArgument(!roleIdList.isEmpty(), ExceptionMessage.COUNT_ZERO + "[roleIdList]");
        log.debug("保存或更新，userId={}，roleIdList={}", userId, roleIdList);

        this.remove(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getUserId, userId));
        List<UserRole> userRoleList = roleIdList.stream().map(item -> {
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(item);
            return userRole;
        }).collect(Collectors.toList());
        return this.saveOrUpdateBatch(userRoleList);
    }
}
