/*
 Navicat Premium Data Transfer

 Source Server         : shuang
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : 127.0.0.1:3306
 Source Schema         : power

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 10/11/2019 23:51:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `real_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `gender` tinyint(4) NULL DEFAULT NULL COMMENT '性别  0：女，1：男',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `gmt_create` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `uk_username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$RSnXODLD3ibxLG42aOFAMu6HuVImoLlnYg8S4aTW1tlwLB7Eqz7My', NULL, 'root@renren.io', '13612345678', 1, 1, 1, '2016-11-11 11:11:11', '2019-11-09 21:04:27');
INSERT INTO `sys_user` VALUES (5, 'test', '$2a$10$BNoqRl6NgxZd6bv7PgK7B.n3uBwxhLxqowAGmu5gQxVTr8ksEl5JG', '测试1', 'xing@qq.com', '18712341234', 1, 1, 1, '2019-11-10 00:51:45', '2019-11-10 16:26:33');
INSERT INTO `sys_user` VALUES (7, 'string1', '$2a$10$uVdA3.M9OXN.M6mxs4i1uu80dLr62PFiRUTvHCrzej1vTqb7PrqE6', 'string', 'string', 'string', 0, 0, 1, '2019-11-10 01:08:51', '2019-11-10 13:10:46');
INSERT INTO `sys_user` VALUES (9, 'test2', '$2a$10$tLW7pAKax/4W5BDPvo2Mx.qehIyx/bHDBzMF7d8/U23AjTxNI7mQ6', '测试', 'xing@qq.com', '18712341234', 1, 1, 1, '2019-11-10 16:03:29', '2019-11-10 16:06:53');

SET FOREIGN_KEY_CHECKS = 1;
