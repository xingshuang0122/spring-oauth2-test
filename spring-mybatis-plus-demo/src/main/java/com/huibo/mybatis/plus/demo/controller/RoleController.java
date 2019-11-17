package com.huibo.mybatis.plus.demo.controller;


import com.google.common.base.Preconditions;
import com.huibo.mybatis.plus.demo.common.PageWrapper;
import com.huibo.mybatis.plus.demo.common.ResponseResult;
import com.huibo.mybatis.plus.demo.constant.ExceptionMessage;
import com.huibo.mybatis.plus.demo.entity.Role;
import com.huibo.mybatis.plus.demo.service.IRoleMenuService;
import com.huibo.mybatis.plus.demo.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 角色 前端控制器
 * </p>
 *
 * @author xingshuang
 * @since 2019-11-09
 */
@Slf4j
@RestController
@RequestMapping("/role")
@Api(tags = "角色接口")
public class RoleController {

    private final IRoleService roleService;

    private final IRoleMenuService roleMenuService;

    @Autowired
    public RoleController(IRoleService roleService,
                          IRoleMenuService roleMenuService) {
        Preconditions.checkNotNull(roleService);
        Preconditions.checkNotNull(roleMenuService);

        this.roleService = roleService;
        this.roleMenuService = roleMenuService;
    }

    /**
     * 查询角色信息
     *
     * @param roleId 角色Id
     * @return 响应结果
     */
    @ApiOperation(value = "根据角色Id查询角色信息", notes = "根据角色Id查询角色信息", httpMethod = "GET", response = ResponseResult.class)
    @ApiImplicitParam(name = "roleId", value = "角色id", required = true, dataType = "int", example = "5")
    @GetMapping("/{roleId}")
    public ResponseResult info(@PathVariable("roleId") Long roleId) {
        log.info("根据角色Id查询角色信息，roleId={}", roleId);
        Role role = this.roleService.getById(roleId);
        if (role == null) {
            return ResponseResult.fail(ExceptionMessage.NOT_FOUND);
        }
        //获取角色所属的角色列表
        List<Long> menuIdList = this.roleMenuService.queryMenuIdList(roleId);
        role.setMenuIdList(menuIdList);
        return ResponseResult.succeed(role);
    }

    /**
     * 所有角色列表，按页查询
     *
     * @param page     页面索引
     * @param size     页面大小
     * @param roleName 待查询的角色名
     * @return 响应结果
     */
    @ApiOperation(value = "按页查询角色信息", notes = "按页查询角色信息", httpMethod = "POST", response = ResponseResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码", required = true, dataType = "int", example = "1"),
            @ApiImplicitParam(name = "size", value = "显示个数", required = true, dataType = "int", example = "10"),
            @ApiImplicitParam(name = "roleName", value = "角色名称", required = false, dataType = "string", example = "")
    })
    @PostMapping("/list/page")
    public ResponseResult list(@RequestParam Long page,
                               @RequestParam Long size,
                               @RequestParam(required = false) String roleName) {
        log.info("按页查询角色信息，page={}，size={}，roleId={}，roleName={}", page, size, roleName);

        // TODO:根据登录的角色查询数据，会伴随着用户id
        PageWrapper result = this.roleService.queryByPage(page, size, roleName);
        return ResponseResult.succeed(result);
    }

    /**
     * 保存角色，也是添加角色
     *
     * @param role 角色信息
     * @return 响应结果
     */
    @ApiOperation(value = "保存角色信息", notes = "保存角色信息", httpMethod = "POST", response = ResponseResult.class)
    @PostMapping("/")
    public ResponseResult save(@RequestBody Role role) {
        log.info("保存角色信息，roleId={}", role);

        // TODO: 设定修改的角色Id，登录之后才有这个用户ID
        role.setCreateUserId(1L);
        Boolean b = this.roleService.saveRole(role);
        return b ? ResponseResult.succeed() : ResponseResult.fail();
    }
//
//    /**
//     * 更新角色信息
//     *
//     * @param roleId 角色Id
//     * @param role   角色信息
//     * @return 响应结果
//     */
//    @ApiOperation(value = "更新角色信息", notes = "更新角色信息", httpMethod = "PUT", response = ResponseResult.class)
//    @PutMapping("/{roleId}")
//    public ResponseResult update(@PathVariable("roleId") Long roleId, @RequestBody Role role) {
//        log.info("根据roleId更新角色信息，roleId={}，role={}", roleId, role);
//
//        // TODO: 设定修改的角色Id，登录之后才有这个角色ID
//        role.setCreateroleId(1L);
//        role.setroleId(roleId);
//        Boolean b = this.roleService.update(role);
//        return b ? ResponseResult.succeed() : ResponseResult.fail();
//    }
//
//    /**
//     * 删除角色
//     *
//     * @param roleIds 角色id列表
//     * @return 响应结果
//     */
//    @DeleteMapping("/list")
//    public ResponseResult delete(@RequestBody List<Long> roleIds) {
//        if (roleIds.contains(1L)) {
//            return ResponseResult.fail("系统管理员不能删除");
//        }
//
//        // TODO:无法删除自己，登录之后才能获取这个ID
//        boolean b = this.roleService.deleteBatch(roleIds);
//        return b ? ResponseResult.succeed() : ResponseResult.fail();
//    }
}
