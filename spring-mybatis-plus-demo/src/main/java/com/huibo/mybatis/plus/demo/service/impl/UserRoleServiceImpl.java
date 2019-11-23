package com.huibo.mybatis.plus.demo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Preconditions;
import com.huibo.mybatis.plus.demo.constant.ExceptionMessage;
import com.huibo.mybatis.plus.demo.entity.UserRole;
import com.huibo.mybatis.plus.demo.mapper.UserRoleMapper;
import com.huibo.mybatis.plus.demo.service.IUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    public List<Long> queryRoleIdList(Long userId) {
        log.debug("根据用户id查询角色id列表，userId={}", userId);

        List<UserRole> userRoles = this.baseMapper.selectList(Wrappers.<UserRole>lambdaQuery()
                .select(UserRole::getRoleId)
                .eq(UserRole::getUserId, userId));
        return userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
    }

    @Override
    public Boolean saveOrUpdate(Long userId, List<Long> roleIdList) {
        Preconditions.checkNotNull(roleIdList, ExceptionMessage.NULL_POINT + "[roleIdList]");
        Preconditions.checkArgument(!roleIdList.isEmpty(), ExceptionMessage.COUNT_ZERO + "[roleIdList]");
        log.debug("保存或更新用户和角色关系表信息，userId={}，roleIdList={}", userId, roleIdList);

        this.remove(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getUserId, userId));
        List<UserRole> userRoleList = roleIdList.stream()
                .map(roleId -> new UserRole(userId, roleId)).collect(Collectors.toList());
        return this.saveOrUpdateBatch(userRoleList);
    }
}
