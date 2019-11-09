package com.huibo.mybatis.plus.demo.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.huibo.mybatis.plus.demo.common.ResponseResult;
import com.huibo.mybatis.plus.demo.constant.UserConstant;
import com.huibo.mybatis.plus.demo.entity.User;
import com.huibo.mybatis.plus.demo.service.IRoleService;
import com.huibo.mybatis.plus.demo.service.IUserService;
import com.huibo.mybatis.plus.demo.service.impl.UserServiceImpl;
import com.huibo.mybatis.plus.demo.utils.PageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author xingshuang
 * @since 2019-11-09
 */
@Slf4j
@RestController
@RequestMapping("/user")
@Api(tags = "用户接口")
public class UserController {

    private final IUserService userService;

    private final IRoleService roleService;


    @Autowired
    public UserController(IUserService userService,
                          IRoleService roleService) {
        Preconditions.checkNotNull(userService);
        Preconditions.checkNotNull(roleService);

        this.userService = userService;
        this.roleService = roleService;
    }

    /**
     * 用户信息
     */
    @ApiOperation(value = "根据用户Id查询用户信息", notes = "根据用户Id查询用户信息", httpMethod = "GET", response = ResponseResult.class)
    @GetMapping("/{userId}")
    public ResponseResult info(@PathVariable("userId") Long userId) {
        log.info("根据用户Id查询用户信息，userId={}", userId);
        User user = this.userService.getById(userId);

        //获取用户所属的角色列表
        List<Long> roleIdList = roleService.queryRoleIdList(userId);
        user.setRoleIdList(roleIdList);
        return ResponseResult.succeed(user);
    }

    /**
     * 所有用户列表
     */
    @ApiOperation(value = "按页查询用户信息", notes = "按页查询用户信息", httpMethod = "POST", response = ResponseResult.class)
    @PostMapping("/list")
    public ResponseResult list(@RequestParam Long page,
                               @RequestParam Long size,
                               @RequestParam Long userId,
                               @RequestParam(required = false) String username) {
        log.info("按页查询用户信息，page={}，size={}，userId={}，username={}", page, size, userId, username);
        //只有超级管理员，才能查看所有管理员列表
        PageUtils result = this.userService.queryByPage(page, size, userId, username);
        return ResponseResult.succeed(result);
    }

    /**
     * 保存用户
     */
    @ApiOperation(value = "按页查询用户信息", notes = "按页查询用户信息", httpMethod = "POST", response = ResponseResult.class)
    @PostMapping("/save")
    public ResponseResult save(@RequestBody User user) {
        log.info("保存用户信息，userId={}", user);

        user.setCreateUserId(1L);
        Boolean b = this.userService.saveUser(user);
        return b ? ResponseResult.succeed() : ResponseResult.fail();
    }
}
