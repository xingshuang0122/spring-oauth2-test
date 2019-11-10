package com.huibo.mybatis.plus.demo.service.impl;

import com.huibo.mybatis.plus.demo.entity.Role;
import com.huibo.mybatis.plus.demo.mapper.RoleMapper;
import com.huibo.mybatis.plus.demo.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Override
    public List<Long> queryRoleIdList(Long userId) {
        log.debug("根据用户id查询角色id列表，userId={}", userId);
        return this.baseMapper.queryRoleIdList(userId);
    }
}
