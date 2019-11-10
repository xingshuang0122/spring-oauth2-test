package com.huibo.mybatis.plus.demo.service;

import com.huibo.mybatis.plus.demo.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户与角色对应关系 服务类
 * </p>
 *
 * @author xingshuang
 * @since 2019-11-09
 */
public interface IUserRoleService extends IService<UserRole> {

    /**
     * 根据用户Id查询角色Id
     *
     * @param userId 用户id
     * @return 角色id列表
     */
    List<Long> queryRoleIdList(Long userId);

    Boolean saveOrUpdate(Long userId, List<Long> roleIdList);
}
