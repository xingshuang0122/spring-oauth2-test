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

 Date: 10/11/2019 23:51:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `gmt_create` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`role_id`) USING BTREE,
  UNIQUE INDEX `uk_role_name`(`role_name`) USING BTREE COMMENT '角色名称必须唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (4, '测试', '无', 1, '2019-11-10 00:54:26', '2019-11-10 00:54:46');
INSERT INTO `sys_role` VALUES (5, '管理员', '无', 1, '2019-11-10 15:57:44', NULL);
INSERT INTO `sys_role` VALUES (8, '测试1', '测试', 1, '2019-11-10 17:59:09', NULL);
INSERT INTO `sys_role` VALUES (9, '测试2', '测试', 1, '2019-11-10 18:01:14', NULL);
INSERT INTO `sys_role` VALUES (10, '测试3', '测试', 1, '2019-11-10 18:22:04', NULL);
INSERT INTO `sys_role` VALUES (12, '测试4', '测试', 1, '2019-11-10 18:33:34', NULL);

SET FOREIGN_KEY_CHECKS = 1;
