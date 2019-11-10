package com.huibo.mybatis.plus.demo.service;

import com.huibo.mybatis.plus.demo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huibo.mybatis.plus.demo.common.PageWrapper;

import java.util.List;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author xingshuang
 * @since 2019-11-09
 */
public interface IUserService extends IService<User> {

    /**
     * 按页查询用户信息
     *
     * @param page     页面索引
     * @param size     页面大小
     * @param userId   用户ID
     * @param username 用户名称
     * @return 页面查询结果信息
     */
    PageWrapper queryByPage(Long page, Long size, Long userId, String username);

    /**
     * 保存用户信息，即在数据库中插入一条数据
     *
     * @param user 用户的信息
     * @return 是否执行成功，true：成功，false：失败
     */
    Boolean saveUser(User user);

    /**
     * 更新用户信息
     *
     * @param user 用户信息
     * @return 是否执行成功，true：成功，false：失败
     */
    Boolean update(User user);

    /**
     * 批量删除用户信息
     * @param userIds 用户的ids
     * @return 是否执行成功
     */
    Boolean deleteBatch(List<Long> userIds);
}
