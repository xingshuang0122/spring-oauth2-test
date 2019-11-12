package com.huibo.mybatis.plus.demo.controller;


import com.google.common.base.Preconditions;
import com.huibo.mybatis.plus.demo.common.ResponseResult;
import com.huibo.mybatis.plus.demo.constant.ExceptionMessage;
import com.huibo.mybatis.plus.demo.entity.User;
import com.huibo.mybatis.plus.demo.service.IRoleService;
import com.huibo.mybatis.plus.demo.service.IUserRoleService;
import com.huibo.mybatis.plus.demo.service.IUserService;
import com.huibo.mybatis.plus.demo.common.PageWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    private final IUserRoleService userRoleService;


    @Autowired
    public UserController(IUserService userService,
                          IUserRoleService userRoleService) {
        Preconditions.checkNotNull(userService);
        Preconditions.checkNotNull(userRoleService);

        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    /**
     * 查询用户信息
     *
     * @param userId 用户Id
     * @return 响应结果
     */
    @ApiOperation(value = "根据用户Id查询用户信息", notes = "根据用户Id查询用户信息", httpMethod = "GET", response = ResponseResult.class)
    @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "int", example = "1")
    @GetMapping("/{userId}")
    public ResponseResult info(@PathVariable("userId") Long userId) {
        long start = System.currentTimeMillis();
        log.info("根据用户Id查询用户信息，userId={}", userId);
        User user = this.userService.getById(userId);
        if (user == null) {
            return ResponseResult.fail(ExceptionMessage.NOT_FOUND);
        }
        //获取用户所属的角色列表
        List<Long> roleIdList = this.userRoleService.queryRoleIdList(userId);
        user.setRoleIdList(roleIdList);
        log.info("耗时：{}", System.currentTimeMillis() - start);
        return ResponseResult.succeed(user);
    }

    /**
     * 所有用户列表，按页查询
     *
     * @param page     页面索引
     * @param size     页面大小
     * @param userId   用户id
     * @param username 待查询的用户名
     * @return 响应结果
     */
    @ApiOperation(value = "按页查询用户信息", notes = "按页查询用户信息", httpMethod = "POST", response = ResponseResult.class)
    @PostMapping("/list")
    public ResponseResult list(@RequestParam Long page,
                               @RequestParam Long size,
                               @RequestParam Long userId,
                               @RequestParam(required = false) String username) {
        log.info("按页查询用户信息，page={}，size={}，userId={}，username={}", page, size, userId, username);

        PageWrapper result = this.userService.queryByPage(page, size, userId, username);
        return ResponseResult.succeed(result);
    }

    /**
     * 保存用户，也是添加用户
     *
     * @param user 用户信息
     * @return 响应结果
     */
    @ApiOperation(value = "保存用户信息", notes = "保存用户信息", httpMethod = "POST", response = ResponseResult.class)
    @PostMapping("/")
    public ResponseResult save(@RequestBody User user) {
        log.info("保存用户信息，userId={}", user);

        // TODO: 设定修改的用户Id，登录之后才有这个用户ID
        user.setCreateUserId(1L);
        Boolean b = this.userService.saveUser(user);
        return b ? ResponseResult.succeed() : ResponseResult.fail();
    }

    /**
     * 更新用户信息
     *
     * @param userId 用户Id
     * @param user   用户信息
     * @return 响应结果
     */
    @ApiOperation(value = "更新用户信息", notes = "更新用户信息", httpMethod = "PUT", response = ResponseResult.class)
    @PutMapping("/{userId}")
    public ResponseResult update(@PathVariable("userId") Long userId, @RequestBody User user) {
        log.info("根据userId更新用户信息，userId={}，user={}", userId, user);

        // TODO: 设定修改的用户Id，登录之后才有这个用户ID
        user.setCreateUserId(1L);
        user.setUserId(userId);
        Boolean b = this.userService.update(user);
        return b ? ResponseResult.succeed() : ResponseResult.fail();
    }

    /**
     * 删除用户
     *
     * @param userIds 用户id列表
     * @return 响应结果
     */
    @DeleteMapping("/list")
    public ResponseResult delete(@RequestBody List<Long> userIds) {
        if (userIds.contains(1L)) {
            return ResponseResult.fail("系统管理员不能删除");
        }

        // TODO:无法删除自己，登录之后才能获取这个ID
        boolean b = this.userService.deleteBatch(userIds);
        return b ? ResponseResult.succeed() : ResponseResult.fail();
    }
}
