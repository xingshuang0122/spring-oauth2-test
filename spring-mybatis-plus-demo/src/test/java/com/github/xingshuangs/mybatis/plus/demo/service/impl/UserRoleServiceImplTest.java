package com.github.xingshuangs.mybatis.plus.demo.service.impl;

import com.github.xingshuangs.mybatis.plus.demo.service.IUserRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRoleServiceImplTest {

    @Autowired
    private IUserRoleService userRoleService;

    @Test
    public void queryRoleIdList() {
        List<Long> list = this.userRoleService.queryRoleIdList(9L);
        list.forEach(System.out::println);
    }
}
