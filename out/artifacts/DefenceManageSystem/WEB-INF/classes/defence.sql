/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50562
 Source Host           : localhost:3306
 Source Schema         : defence2

 Target Server Type    : MySQL
 Target Server Version : 50562
 File Encoding         : 65001

 Date: 24/06/2021 00:01:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for checkwork
-- ----------------------------
DROP TABLE IF EXISTS `checkwork`;
CREATE TABLE `checkwork`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `groupid` int(11) NULL DEFAULT NULL,
  `start` int(11) NULL DEFAULT NULL,
  `end` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `groupid`(`groupid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of checkwork
-- ----------------------------

-- ----------------------------
-- Table structure for defence_result
-- ----------------------------
DROP TABLE IF EXISTS `defence_result`;
CREATE TABLE `defence_result`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sno` int(11) NOT NULL,
  `context` int(11) NULL DEFAULT NULL COMMENT '论文内容得分50',
  `innovate` int(11) NULL DEFAULT NULL COMMENT '创新分数10',
  `defence` int(11) NULL DEFAULT NULL COMMENT '答辩得分30',
  `time` int(11) NULL DEFAULT NULL COMMENT '所用时间得分10',
  `grade` int(11) NULL DEFAULT NULL COMMENT '总分100',
  `tno` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sno`(`sno`) USING BTREE,
  CONSTRAINT `sno` FOREIGN KEY (`sno`) REFERENCES `s_user` (`sno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of defence_result
-- ----------------------------

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `defencetime` datetime NULL DEFAULT NULL COMMENT '答辩时间',
  `leaderid` int(11) NULL DEFAULT NULL COMMENT '组长id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `leaderid`(`leaderid`) USING BTREE,
  CONSTRAINT `leaderid` FOREIGN KEY (`leaderid`) REFERENCES `t_user` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of group
-- ----------------------------
INSERT INTO `group` VALUES (-1, '未分组', '1970-01-01 16:06:06', NULL);

-- ----------------------------
-- Table structure for s_user
-- ----------------------------
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ssex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sno` int(11) NOT NULL,
  `groupid` int(11) NULL DEFAULT -1,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '123456',
  `paperurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `snoun`(`sno`) USING BTREE,
  INDEX `sno`(`sno`) USING BTREE,
  INDEX `sgroupid`(`groupid`) USING BTREE,
  CONSTRAINT `sgroupid` FOREIGN KEY (`groupid`) REFERENCES `group` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of s_user
-- ----------------------------
INSERT INTO `s_user` VALUES (1, '赵', '男', 201809003, -1, '123456', NULL);
INSERT INTO `s_user` VALUES (2, '钱', '女', 201809004, -1, '123456', NULL);
INSERT INTO `s_user` VALUES (3, '孙', '男', 201809005, -1, '123456', NULL);
INSERT INTO `s_user` VALUES (4, '李', '女', 201809006, -1, '123456', NULL);
INSERT INTO `s_user` VALUES (5, '周', '男', 201809007, -1, '123456', NULL);
INSERT INTO `s_user` VALUES (6, '吴', '男', 201809008, -1, '123456', NULL);
INSERT INTO `s_user` VALUES (7, '郑', '男', 201809009, -1, '123456', NULL);
INSERT INTO `s_user` VALUES (8, '王', '女', 201809010, -1, '123456', NULL);
INSERT INTO `s_user` VALUES (9, '冯', '男', 201809011, -1, '123456', NULL);
INSERT INTO `s_user` VALUES (10, '陈', '女', 201809012, -1, '123456', NULL);
INSERT INTO `s_user` VALUES (11, '褚', '男', 201809013, -1, '123456', NULL);
INSERT INTO `s_user` VALUES (12, '卫', '男', 201809014, -1, '123456', NULL);
INSERT INTO `s_user` VALUES (13, '蒋', '女', 201809015, -1, '123456', NULL);
INSERT INTO `s_user` VALUES (14, '沈', '女', 201809016, -1, '123456', NULL);
INSERT INTO `s_user` VALUES (15, '韩', '男', 201809017, -1, '123456', NULL);
INSERT INTO `s_user` VALUES (16, '杨', '女', 201809018, -1, '123456', NULL);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tsno` int(11) NOT NULL,
  `tsex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `group_id` int(11) NULL DEFAULT -1,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'teacher' COMMENT '身份（辅导员，管理员）',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '456789',
  `ischeck` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `tsno`(`tsno`) USING BTREE,
  INDEX `tgroupid`(`group_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (3, '赵老师', 201802001, '男', -1, 'teacher', '456789', '');
INSERT INTO `t_user` VALUES (4, '钱老师', 201802002, '男', -1, 'teacher', '456789', '');
INSERT INTO `t_user` VALUES (5, '孙老师', 201802003, '男', -1, 'teacher', '456789', '');
INSERT INTO `t_user` VALUES (6, '李老师', 201802004, '男', -1, 'teacher', '456789', '');
INSERT INTO `t_user` VALUES (7, '周老师', 201802005, '女', -1, 'teacher', '456789', '');
INSERT INTO `t_user` VALUES (8, '吴老师', 201802006, '男', -1, 'teacher', '456789', '');
INSERT INTO `t_user` VALUES (9, '郑老师', 201802007, '男', -1, 'teacher', '456789', '');
INSERT INTO `t_user` VALUES (10, '王老师', 201802008, '女', -1, 'teacher', '456789', '');
INSERT INTO `t_user` VALUES (11, '冯老师', 201802009, '男', -1, 'teacher', '456789', '');
INSERT INTO `t_user` VALUES (12, '陈老师', 201802010, '女', -1, 'teacher', '456789', '');
INSERT INTO `t_user` VALUES (13, '褚老师', 201802011, '女', -1, 'teacher', '456789', '');
INSERT INTO `t_user` VALUES (14, '卫老师', 201802012, '男', -1, 'teacher', '456789', '');
INSERT INTO `t_user` VALUES (15, '蒋老师', 201802013, '女', -1, 'teacher', '456789', '');
INSERT INTO `t_user` VALUES (16, '沈老师', 201802014, '男', -1, 'teacher', '456789', '');
INSERT INTO `t_user` VALUES (17, '韩老师', 201802015, '男', -1, 'teacher', '456789', '');
INSERT INTO `t_user` VALUES (18, '杨老师', 201802016, '男', -1, 'teacher', '456789', '');
INSERT INTO `t_user` VALUES (21, '管理员老师', 201801001, '男', -1, 'manager', '456789', NULL);

SET FOREIGN_KEY_CHECKS = 1;
