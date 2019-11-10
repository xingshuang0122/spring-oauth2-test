package com.huibo.mybatis.plus.demo.mapper;

import com.huibo.mybatis.plus.demo.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 菜单管理 Mapper 接口
 * </p>
 *
 * @author xingshuang
 * @since 2019-11-09
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Long> queryMenuIdList(Long roleId);
}
