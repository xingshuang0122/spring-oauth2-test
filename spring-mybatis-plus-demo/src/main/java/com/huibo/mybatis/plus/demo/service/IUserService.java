package com.huibo.mybatis.plus.demo.service;

import com.huibo.mybatis.plus.demo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huibo.mybatis.plus.demo.utils.PageUtils;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author xingshuang
 * @since 2019-11-09
 */
public interface IUserService extends IService<User> {

    PageUtils queryByPage(Long page, Long size, Long userId, String username);

    Boolean saveUser(User user);
}
