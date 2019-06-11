/*
 Navicat Premium Data Transfer

 Source Server         : Mysql
 Source Server Type    : MySQL
 Source Server Version : 50173
 Source Host           : localhost:3306
 Source Schema         : medicine

 Target Server Type    : MySQL
 Target Server Version : 50173
 File Encoding         : 65001

 Date: 11/06/2019 13:11:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for medicine
-- ----------------------------
DROP TABLE IF EXISTS `medicine`;
CREATE TABLE `medicine`  (
  `id1` int(255) NOT NULL AUTO_INCREMENT,
  `medicineId` int(11) NULL DEFAULT NULL,
  `medicineName` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `medicinetype` int(255) NULL DEFAULT NULL,
  `inventory` int(255) NULL DEFAULT NULL,
  `producer` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `purchasingPrice` float(10, 2) NULL DEFAULT NULL,
  `sellingPrice` float(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`id1`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of medicine
-- ----------------------------
INSERT INTO `medicine` VALUES (6, 20190418, '感冒冲剂', 1, 3, '哈药集团', 12.00, 20.00);
INSERT INTO `medicine` VALUES (7, 908, '阿么西林', 1, 5, '哈药集团', 12.00, 22.00);

-- ----------------------------
-- Table structure for order1
-- ----------------------------
DROP TABLE IF EXISTS `order1`;
CREATE TABLE `order1`  (
  `id1` int(255) NOT NULL AUTO_INCREMENT,
  `orderId` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `staffId` int(11) NULL DEFAULT NULL,
  `money` float(255, 0) NULL DEFAULT NULL,
  `createDate` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `paytype` int(255) NULL DEFAULT NULL,
  `ordertype` int(255) NULL DEFAULT NULL,
  `medi` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `num` int(11) NULL DEFAULT NULL,
  `purch` int(11) NULL DEFAULT NULL,
  `sell` float(10, 0) NULL DEFAULT NULL,
  PRIMARY KEY (`id1`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of order1
-- ----------------------------
INSERT INTO `order1` VALUES (23, '20190420204206', 20163655, 67, '20190420', 0, 0, '阿么西林', 1, 12, 16);
INSERT INTO `order1` VALUES (26, '20190420214342', 20163655, 20, '20190420', 0, 1, '感冒冲剂', 1, 12, 20);
INSERT INTO `order1` VALUES (27, '20190420214834', 20163655, 0, '20190420', 0, 0, '感冒冲剂', 1, 12, 20);
INSERT INTO `order1` VALUES (28, '20190420220055', 20163655, 48, '20190420', 0, 0, '阿么西林', 4, 12, 22);

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff`  (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `staffId` int(11) NULL DEFAULT NULL,
  `staffName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `duty` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `workingAge` float(255, 0) NULL DEFAULT NULL,
  `born` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `major` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `edu` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `degree1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `loca` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pict` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of staff
-- ----------------------------
INSERT INTO `staff` VALUES (1, 20163655, '马佳慧', '0', 22, '18203377789', '前台售货员', 1, '1997.03', '汉族', '计算机', '本科', '学士', '安徽省,合肥市,瑶海区', '2274556995@qq.com', 'C:马佳慧的ecworkspaceMedicineStoreWebRootima3.jpg');

-- ----------------------------
-- Table structure for userinfor
-- ----------------------------
DROP TABLE IF EXISTS `userinfor`;
CREATE TABLE `userinfor`  (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userpaw` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of userinfor
-- ----------------------------
INSERT INTO `userinfor` VALUES (1, 'mjh', '123');
INSERT INTO `userinfor` VALUES (3, 'mjh', '123');

SET FOREIGN_KEY_CHECKS = 1;
