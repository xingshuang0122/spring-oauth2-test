package com.huibo.mybatis.plus.demo.service.impl;

import com.huibo.mybatis.plus.demo.entity.RoleMenu;
import com.huibo.mybatis.plus.demo.mapper.RoleMenuMapper;
import com.huibo.mybatis.plus.demo.service.IRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色与菜单对应关系 服务实现类
 * </p>
 *
 * @author xingshuang
 * @since 2019-11-09
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

}
