package com.github.xingshuangs.mybatis.plus.demo.service.impl;

import com.github.xingshuangs.mybatis.plus.demo.entity.Role;
import com.google.common.collect.Lists;
import com.github.xingshuangs.mybatis.plus.demo.service.IRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleServiceImplTest {

    @Autowired
    private IRoleService roleService;

    @Test
    public void update() {
        Role role  = new Role();
        role.setRoleId(4L);
        role.setRemark("哈哈");
        role.setMenuIdList(Lists.newArrayList(80L));
        Boolean result = this.roleService.update(role);
        System.out.println(result);
    }
}
