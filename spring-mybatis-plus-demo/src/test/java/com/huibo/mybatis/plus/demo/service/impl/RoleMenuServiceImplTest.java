package com.huibo.mybatis.plus.demo.service.impl;

import com.huibo.mybatis.plus.demo.service.IRoleMenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleMenuServiceImplTest {

    @Autowired
    private IRoleMenuService iRoleMenuService;

    @Test
    public void queryMenuIdList() {
        List<Long> list = this.iRoleMenuService.queryMenuIdList(4L);
        list.forEach(System.out::println);
    }
}