-- 这里我注释掉了，因为我配置的是持久数据库，后面自己通过mybatis-plus添加测试数据
-- 如果创建的是内存数据库的话才可能需要每次启动应用自动插入数据

DELETE FROM book;
INSERT INTO book (id, name, price) VALUES
(1, '<demo1>', 88),
(2, '<demo2>', 444),
(3, '<demo3>', 444),
(4, '<demo4>', 666),
(5, '<demo5>', 7);

