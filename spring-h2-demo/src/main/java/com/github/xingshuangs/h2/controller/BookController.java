package com.github.xingshuangs.h2.controller;


import com.github.xingshuangs.h2.entity.Book;
import com.github.xingshuangs.h2.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.IntStream;

/**
 * @author xingshuang
 */
@RestController
public class BookController {

    private final IBookService iBookService;

    @Autowired
    public BookController(IBookService iBookService) {
        this.iBookService = iBookService;
    }

    @GetMapping("/test")
    public ResponseEntity<List<Book>> demoTest() {
        List<Book> list = this.iBookService.list();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/add")
    public ResponseEntity addData() {
        IntStream.range(0, 5).forEach(x -> {
            Book b = new Book();
            b.setName("test" + x);
            b.setPrice(x * 5);
            this.iBookService.save(b);
        });
        return ResponseEntity.ok("");
    }

}
