package com.huibo.mybatis.plus.demo.service.impl;

import com.huibo.mybatis.plus.demo.entity.Role;
import com.huibo.mybatis.plus.demo.mapper.RoleMapper;
import com.huibo.mybatis.plus.demo.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author xingshuang
 * @since 2019-11-09
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Override
    public List<Long> queryRoleIdList(Long userId) {
        return baseMapper.queryRoleIdList(userId);
    }
}
