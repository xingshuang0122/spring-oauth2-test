package com.github.xingshuangs.h2.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author xingshuang
 */
@Data
public class Book {
    /**
     * id自增
     */
    @TableId(type = IdType.AUTO)
    private int id;
    private String name;
    private Integer price;
}
