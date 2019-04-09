/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80013
Source Host           : localhost:3306
Source Database       : graduation_project

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2019-04-09 14:15:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `category` varchar(255) DEFAULT NULL,
  `author` varchar(255) NOT NULL,
  `introduction` varchar(255) NOT NULL,
  `main_text` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `create_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('4', '123', 'JavaScript', '1', '123', '<p>123</p>', '2019-04-04 10:00:57');
INSERT INTO `article` VALUES ('5', '12', 'JavaScript', ' q', '123', '<p>123</p>', '2019-04-04 10:00:57');
INSERT INTO `article` VALUES ('6', '1111', '操作系统', ' q', '123', '<h1>123123123</h1><p><span style=\"font-weight: bold;\">123123</span><br></p><p><span style=\"text-decoration-line: underline;\">123123</span><br></p><ol><li><span style=\"text-decoration-line: line-through;\">123123</span></li><li><span style=\"color: rgb(249, 150, 59);\">123123</span></li><li><span style=\"color: rgb(249, 150, 59); background-color: rgb(70, 172, 200);\">123</span></li></ol><div><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/40/pcmoren_tian_org.png\" alt=\"[舔屏]\" data-w-e=\"1\"><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/3c/pcmoren_wu_org.png\" alt=\"[污]\" data-w-e=\"1\"><span style=\"color: rgb(249, 150, 59);\"><br></span></div>', '2019-04-04 10:04:08');

-- ----------------------------
-- Table structure for article_comment_list
-- ----------------------------
DROP TABLE IF EXISTS `article_comment_list`;
CREATE TABLE `article_comment_list` (
  `article_id` int(11) NOT NULL,
  `comment_list_id` int(11) NOT NULL,
  UNIQUE KEY `UK_df89pt9cpjvmar1d73u4lfqha` (`comment_list_id`),
  KEY `FK8mcmqcp12nqufbcnt3crbyj5y` (`article_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of article_comment_list
-- ----------------------------

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of collect
-- ----------------------------
INSERT INTO `collect` VALUES ('5', '4', '1');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `main` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `authority` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('5', 'ROLE_TEST', '测试');
INSERT INTO `role` VALUES ('2', 'ROLE_admin', '管理员');
INSERT INTO `role` VALUES ('3', 'ROLE_normal', '普通用户');
INSERT INTO `role` VALUES ('4', 'ROLE_limit', '限制用户');
INSERT INTO `role` VALUES ('1', 'ROLE_administrator', '超级管理员');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `username` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `actual_name` varchar(255) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `introduction` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `sex` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '$2a$10$mxM3d4XceiIIyU2Qs5ozCuZZP35/LdQiNQLj59CN9SINsfvwu8by.', 'root', '1', '1960-02-01', '1', '1', '1');
INSERT INTO `user` VALUES ('2', '$2a$10$y9oX7TRSTUvzGk1QCL48RetaiGvI3AMfbRKMPBLzuHf9pyU9m7iim', 'admin', 'q', '1960-02-01', 'q', ' q', '1');
INSERT INTO `user` VALUES ('7', '$2a$10$ui.yB8EX/mn.YF1Pj1/gH.f7c1I7pLraNfg5IfO3jk7KGf5jpPf2S', 'test', null, null, null, null, '0');
INSERT INTO `user` VALUES ('9', '$2a$10$VHihQPy8H7Cu7BnE8oWWbucqiLs3Ia0Q0jdOV.eoePDI9G4BO8vp.', 'limit', null, null, null, null, '0');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  KEY `FK859n2jvi8ivhui0rl0esws6o` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1');
INSERT INTO `user_role` VALUES ('1', '2');
INSERT INTO `user_role` VALUES ('1', '3');
INSERT INTO `user_role` VALUES ('2', '2');
INSERT INTO `user_role` VALUES ('2', '3');
INSERT INTO `user_role` VALUES ('7', '3');
INSERT INTO `user_role` VALUES ('9', '4');
