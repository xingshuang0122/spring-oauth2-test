package com.github.xingshuangs.h2.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.xingshuangs.h2.entity.Book;
import com.github.xingshuangs.h2.mapper.BookMapper;
import com.github.xingshuangs.h2.service.IBookService;
import org.springframework.stereotype.Service;

/**
 * @author xingshuang
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {
}
