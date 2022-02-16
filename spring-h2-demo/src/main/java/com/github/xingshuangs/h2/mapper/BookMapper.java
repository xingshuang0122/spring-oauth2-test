package com.github.xingshuangs.h2.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.xingshuangs.h2.entity.Book;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xingshuang
 */
@Mapper
public interface BookMapper extends BaseMapper<Book> {
}
