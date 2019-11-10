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
}
