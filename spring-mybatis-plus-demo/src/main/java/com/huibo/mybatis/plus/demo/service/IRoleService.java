package com.huibo.mybatis.plus.demo.service;

import com.huibo.mybatis.plus.demo.common.PageWrapper;
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

    PageWrapper queryByPage(Long page, Long size, String roleName);

    Boolean saveRole(Role role);

    /**
     * 更新角色信息
     *
     * @param role 角色
     * @return 是否成功，true：成功，false：失败
     */
    Boolean update(Role role);
}
