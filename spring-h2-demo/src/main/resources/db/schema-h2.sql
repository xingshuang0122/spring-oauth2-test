-- 每次启动应用都重建新表
-- DROP TABLE IF EXISTS book;

-- CREATE TABLE liquor
SET MODE=MYSQL;  -- 兼容mysql语法
-- 以下是只最初创建一次表，而不是一启动应用就重建
CREATE TABLE  IF NOT EXISTS  book
(
  id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID' ,
  name VARCHAR(128) NULL DEFAULT NULL COMMENT '名称',
  price INT(11) NULL DEFAULT NULL COMMENT '价格',
  PRIMARY KEY (id)
);