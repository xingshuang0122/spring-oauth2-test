package com.huibo.mybatis.plus.demo.service;

import com.huibo.mybatis.plus.demo.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author xingshuang
 * @since 2019-11-09
 */
public interface IRoleService extends IService<Role> {

    /**
     * 根据用户Id查询角色Id
     *
     * @param userId 用户id
     * @return 角色id列表
     */
    List<Long> queryRoleIdList(Long userId);
}
