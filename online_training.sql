/*
 Navicat MySQL Data Transfer

 Source Server         : spokenman
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : online_training

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 30/08/2018 17:13:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for data_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `data_dictionary`;
CREATE TABLE `data_dictionary`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `remark` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `action_by` bigint(20) NULL DEFAULT NULL,
  `action_time` datetime(0) NULL DEFAULT NULL,
  `uplink` bigint(20) NULL DEFAULT NULL,
  `ctrl_id` bigint(20) NULL DEFAULT NULL,
  `code_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sub_code_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `level` int(11) NULL DEFAULT NULL,
  `global_seq` bigint(20) NULL DEFAULT NULL,
  `value_str` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `value_rule` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `link` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `rank` bigint(20) NULL DEFAULT NULL,
  `pic_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` smallint(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE,
  INDEX `FK7F1484B6F3B0E0A`(`uplink`) USING BTREE,
  INDEX `FK7F1484BE2AB2864`(`ctrl_id`) USING BTREE,
  CONSTRAINT `FK7F1484B6F3B0E0A` FOREIGN KEY (`uplink`) REFERENCES `data_dictionary` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK7F1484BE2AB2864` FOREIGN KEY (`ctrl_id`) REFERENCES `data_dictionary` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKa0mp1vn773jvaf6q92mvwjy19` FOREIGN KEY (`uplink`) REFERENCES `data_dictionary` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKo19lo6wksyn5xg52o4al8qltq` FOREIGN KEY (`ctrl_id`) REFERENCES `data_dictionary` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of data_dictionary
-- ----------------------------
INSERT INTO `data_dictionary` VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, '1', 0, NULL, '组织条线', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `data_dictionary` VALUES (2, NULL, NULL, NULL, 1, 1, NULL, NULL, 1, NULL, '教育', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `data_dictionary` VALUES (3, NULL, NULL, NULL, 1, 1, NULL, NULL, 1, NULL, '安全', NULL, NULL, NULL, NULL, 1);

-- ----------------------------
-- Table structure for ota_event
-- ----------------------------
DROP TABLE IF EXISTS `ota_event`;
CREATE TABLE `ota_event`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '新闻事件表',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `org_id` int(11) NULL DEFAULT NULL COMMENT '发布组织id',
  `user_id` int(11) NOT NULL COMMENT '发布人id',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '事件开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '事件结束时间',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `org_id`(`org_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `start_time`(`start_time`) USING BTREE,
  INDEX `end_time`(`end_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ota_event_resource
-- ----------------------------
DROP TABLE IF EXISTS `ota_event_resource`;
CREATE TABLE `ota_event_resource`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '新闻发布案例表',
  `event_id` int(11) NOT NULL COMMENT '事件id',
  `resource_id` int(11) NOT NULL COMMENT '学习资源id',
  `event_time` datetime(0) NULL DEFAULT NULL COMMENT '事件中新闻发布时间',
  `status` tinyint(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `event_id`(`event_id`) USING BTREE,
  INDEX `resource_id`(`resource_id`) USING BTREE,
  INDEX `event_time`(`event_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ota_learning_resource
-- ----------------------------
DROP TABLE IF EXISTS `ota_learning_resource`;
CREATE TABLE `ota_learning_resource`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学习资源表',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` tinyint(4) NULL DEFAULT NULL COMMENT '资料类型。1：视频讲座；2：音频讲座；3：图文',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资料url',
  `description` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资料描述',
  `lecturer_id` int(11) NULL DEFAULT NULL COMMENT '讲师id',
  `lecturer_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '讲师名称',
  `org_id` int(11) NULL DEFAULT NULL COMMENT '发布组织id',
  `level` tinyint(4) NULL DEFAULT NULL COMMENT '等级。1：国家级；2：省部级；3：司厅局级；4：县初级:5：乡镇级',
  `type_id` bigint(20) NULL DEFAULT NULL COMMENT '条线。关联 data_dictionary',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '发布人id',
  `score` tinyint(4) NULL DEFAULT NULL COMMENT '学分',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint(4) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `user_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `auth_type` tinyint(10) NULL DEFAULT NULL COMMENT '资源权限类型。1：所有等级可见；0:下级不可见',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `org_id`(`org_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `type`(`type`) USING BTREE,
  INDEX `create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ota_learning_resource
-- ----------------------------
INSERT INTO `ota_learning_resource` VALUES (1, '新闻讲座视频', 1, NULL, NULL, NULL, NULL, 1, 2, 1, 1, 40, '2018-08-30 16:49:42', '2018-08-30 16:49:42', 1, NULL, 'admin', 1);

-- ----------------------------
-- Table structure for ota_learning_resource_auth
-- ----------------------------
DROP TABLE IF EXISTS `ota_learning_resource_auth`;
CREATE TABLE `ota_learning_resource_auth`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学习资料权限表',
  `recource_id` int(11) NOT NULL COMMENT '学习资料id',
  `level_auth` tinyint(4) NULL DEFAULT 1 COMMENT '层级权限。1：仅本层级；2：本级及下级；3：本级及上级；4：所有层级；5：自定义',
  `type_auth` tinyint(4) NULL DEFAULT 1 COMMENT '条线权限。1：仅本条线；2：所有条线；3：自定义',
  `level_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '当层级权限为自定义时，该字段存储开放的层级id。以分号分隔',
  `type_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '当条线权限为自定义时，该字段存储开放条线id。以分号分隔',
  `status` tinyint(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `recource_id`(`recource_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ota_learning_resource_question
-- ----------------------------
DROP TABLE IF EXISTS `ota_learning_resource_question`;
CREATE TABLE `ota_learning_resource_question`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学习资源内容控制表',
  `resource_id` int(11) NOT NULL,
  `content` int(11) NOT NULL COMMENT '题干',
  `answer` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '题目答案。多选题以 && 分隔选项',
  `pic1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pic2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pic3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type_id` bigint(20) NOT NULL COMMENT '题型 id',
  `time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '题目要求作答时间。格式为hh:mm:ss',
  `status` tinyint(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `resource_id`(`resource_id`) USING BTREE,
  INDEX `question_id`(`content`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ota_lecturer
-- ----------------------------
DROP TABLE IF EXISTS `ota_lecturer`;
CREATE TABLE `ota_lecturer`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '讲师表',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '讲师名称',
  `introduction` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `level` tinyint(4) NULL DEFAULT NULL COMMENT '组织等级。1：国家级；2：省部级；3：司厅局级；4：县初级:5：乡镇级',
  `type_id` bigint(20) NULL DEFAULT NULL COMMENT '条线。关联 data_dictionary',
  `status` tinyint(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ota_organization
-- ----------------------------
DROP TABLE IF EXISTS `ota_organization`;
CREATE TABLE `ota_organization`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '全称',
  `short_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简称',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '负责人id',
  `area_id` bigint(20) NULL DEFAULT NULL COMMENT '区域id',
  `level` tinyint(4) NOT NULL COMMENT '组织等级。1：国家级；2：省部级；3：司厅局级；4：县初级:5：乡镇级',
  `type_id` bigint(20) NULL DEFAULT NULL COMMENT '条线。关联 data_dictionary',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父级组织',
  `creator_id` int(11) NULL DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `level`(`level`) USING BTREE,
  INDEX `type`(`type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ota_organization
-- ----------------------------
INSERT INTO `ota_organization` VALUES (1, '平台组织', '平台', 1, NULL, 1, NULL, NULL, NULL, NULL, 1, '2018-03-31 14:56:36', 1);
INSERT INTO `ota_organization` VALUES (6, '杭州教育局', '杭教局', 4, NULL, 1, 1, '1258745987', 'zxc@qq.com', NULL, 1, '2018-08-30 16:36:27', 1);
INSERT INTO `ota_organization` VALUES (7, '台州卫生局', '卫生局', NULL, 331002, 1, 1, '125455', 'qwe@qq.com', NULL, 1, '2018-08-30 16:39:46', NULL);

-- ----------------------------
-- Table structure for ota_training
-- ----------------------------
DROP TABLE IF EXISTS `ota_training`;
CREATE TABLE `ota_training`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '培训表',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '培训名',
  `org_id` int(11) NULL DEFAULT NULL COMMENT '发起组织',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '发起人',
  `required_score` tinyint(4) NULL DEFAULT NULL COMMENT '必修学分',
  `optional_socre` tinyint(4) NULL DEFAULT NULL COMMENT '选修学分',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '培训开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '培训结束时间',
  `sign_start_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '培训报名开始时间',
  `sign_end_time` datetime(0) NULL DEFAULT NULL COMMENT '培训报名截止时间',
  `level` tinyint(4) NULL DEFAULT NULL COMMENT '等级。1：国家级；2：省部级；3：司厅局级；4：县初级:5：乡镇级',
  `type_id` bigint(20) NULL DEFAULT NULL COMMENT '条线。关联 data_dictionary',
  `description` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '培训描述',
  `exam_id` int(11) NULL DEFAULT NULL COMMENT '考试id',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `org_id`(`org_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `exam_id`(`exam_id`) USING BTREE,
  INDEX `create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ota_training_resource
-- ----------------------------
DROP TABLE IF EXISTS `ota_training_resource`;
CREATE TABLE `ota_training_resource`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '培训课程表',
  `training_id` int(11) NOT NULL COMMENT '培训id',
  `resource_id` int(11) NOT NULL COMMENT '学习资源id',
  `required` tinyint(4) NULL DEFAULT NULL COMMENT '是否必修。0：非必修；1：必修',
  `score` tinyint(4) NULL DEFAULT NULL COMMENT '学分',
  `status` tinyint(4) NULL DEFAULT NULL,
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ota_training_user
-- ----------------------------
DROP TABLE IF EXISTS `ota_training_user`;
CREATE TABLE `ota_training_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '培训成员表',
  `training_id` int(11) NOT NULL COMMENT '培训id',
  `user_id` int(11) NOT NULL COMMENT '学员id',
  `required_score` tinyint(4) NULL DEFAULT NULL COMMENT '必修学分',
  `optional_socre` tinyint(4) NULL DEFAULT NULL,
  `certificate_no` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证书号',
  `certificate_photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证书照片',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `stauts` tinyint(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `training_id`(`training_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ota_user
-- ----------------------------
DROP TABLE IF EXISTS `ota_user`;
CREATE TABLE `ota_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '姓名',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `org_id` int(11) NULL DEFAULT NULL COMMENT '组织id',
  `area_id` bigint(20) NULL DEFAULT NULL COMMENT '区域id',
  `type` tinyint(4) NOT NULL COMMENT '用户类型。0：平台用户；1：个人用户；2：单位用户',
  `level` tinyint(4) NULL DEFAULT NULL COMMENT '等级',
  `type_id` bigint(20) NULL DEFAULT NULL COMMENT '条线',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint(4) NULL DEFAULT NULL,
  `org_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织名称',
  `creator_id` int(11) NULL DEFAULT NULL COMMENT '创建人id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `org_id`(`org_id`) USING BTREE,
  INDEX `type`(`type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ota_user
-- ----------------------------
INSERT INTO `ota_user` VALUES (1, 'admin', '123456', NULL, NULL, 1, NULL, 0, NULL, NULL, '2018-08-30 15:16:33', 1, NULL, NULL);
INSERT INTO `ota_user` VALUES (2, '陈深', '123456', '15896241785', '445679589@qq.com', 1, NULL, 1, NULL, NULL, '2018-08-30 16:16:16', 1, '平台组织', 1);
INSERT INTO `ota_user` VALUES (3, '李四', '123456', '15896147852', '445679589@qq.com', 1, NULL, 1, NULL, NULL, '2018-08-30 16:24:49', 1, '平台组织', 1);
INSERT INTO `ota_user` VALUES (4, '王五', '123456', '14785236547', '123@qq.com', 6, NULL, 2, 1, 1, '2018-08-30 16:36:27', 1, '杭州教育局', 1);

-- ----------------------------
-- Table structure for ota_user_apply
-- ----------------------------
DROP TABLE IF EXISTS `ota_user_apply`;
CREATE TABLE `ota_user_apply`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户审批表',
  `user_id` int(11) NOT NULL COMMENT '申请人id',
  `org_id` int(11) NOT NULL COMMENT '申请组织id',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '申请时间',
  `approve_time` datetime(0) NULL DEFAULT NULL COMMENT '审批时间',
  `status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '审批状态。1：申请中；0：拒绝；2：通过',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `org_id`(`org_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ota_user_resource
-- ----------------------------
DROP TABLE IF EXISTS `ota_user_resource`;
CREATE TABLE `ota_user_resource`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户资源查看记录表',
  `user_id` int(11) NOT NULL,
  `resource_id` int(11) NOT NULL,
  `time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上次观看至视频时间',
  `score` tinyint(4) NULL DEFAULT 0 COMMENT '获得学分，学习完成后获得',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '1：学习中；2：学习完成',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `resource_id`(`resource_id`) USING BTREE,
  INDEX `user_id_2`(`user_id`, `resource_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ota_user_resource_question
-- ----------------------------
DROP TABLE IF EXISTS `ota_user_resource_question`;
CREATE TABLE `ota_user_resource_question`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户培训资源答题记录',
  `user_id` int(11) NOT NULL,
  `resource_question_id` int(11) NOT NULL COMMENT '学习资源问题 id',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `resource_question_id`(`resource_question_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tannouncement
-- ----------------------------
DROP TABLE IF EXISTS `tannouncement`;
CREATE TABLE `tannouncement`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `attachment` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `closure_date` datetime(0) NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `read_count` int(11) NULL DEFAULT NULL,
  `status` smallint(6) NULL DEFAULT NULL,
  `text` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` smallint(6) NULL DEFAULT NULL,
  `create_by` int(11) NULL DEFAULT NULL,
  `source_organization_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKgfxg34gh4y7ww8fyii57pmgy1`(`create_by`) USING BTREE,
  INDEX `FKdvj0nj1gqpe95btyhft42ikoo`(`source_organization_id`) USING BTREE,
  CONSTRAINT `FKdvj0nj1gqpe95btyhft42ikoo` FOREIGN KEY (`source_organization_id`) REFERENCES `torganization` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKgfxg34gh4y7ww8fyii57pmgy1` FOREIGN KEY (`create_by`) REFERENCES `tmember` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tdriver
-- ----------------------------
DROP TABLE IF EXISTS `tdriver`;
CREATE TABLE `tdriver`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birth` date NULL DEFAULT NULL,
  `birth_place` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `drive_licence_end_time` date NULL DEFAULT NULL,
  `drive_licence_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `drive_licence_start_time` date NULL DEFAULT NULL,
  `drive_lincence_photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `emergency_contact` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `emergency_contact_mobile` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` smallint(6) NULL DEFAULT NULL,
  `mobile` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ota_user_id` int(11) NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `postcode` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `safe_centificate_end_time` date NULL DEFAULT NULL,
  `safe_centificate_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `safe_centificate_photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `safe_centificate_start_time` date NULL DEFAULT NULL,
  `sfz_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `star_time` datetime(0) NULL DEFAULT NULL,
  `status` smallint(6) NOT NULL,
  `train_centificate_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `train_centificate_photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `org_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_gqd92dfueg131bat78rakwvim`(`sfz_no`) USING BTREE,
  INDEX `FK1co1qi5wth8wlgt8uu1aquhw9`(`org_id`) USING BTREE,
  CONSTRAINT `FK1co1qi5wth8wlgt8uu1aquhw9` FOREIGN KEY (`org_id`) REFERENCES `torganization` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for texam
-- ----------------------------
DROP TABLE IF EXISTS `texam`;
CREATE TABLE `texam`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NOT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `duration` int(11) NOT NULL,
  `exam_end_time` datetime(0) NULL DEFAULT NULL,
  `exam_start_time` datetime(0) NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `signup_end_time` datetime(0) NULL DEFAULT NULL,
  `signup_start_time` datetime(0) NULL DEFAULT NULL,
  `status` smallint(6) NOT NULL,
  `type` smallint(6) NOT NULL,
  `announcement_id` int(11) NULL DEFAULT NULL,
  `org_id` int(11) NOT NULL,
  `paper_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKrll1wrk89cllko74ssicwc3rb`(`announcement_id`) USING BTREE,
  INDEX `FK8u1c6c8l7enuex3k8gvenlo7u`(`org_id`) USING BTREE,
  INDEX `FKk6cxyha4wqi46d29rdy8puvvr`(`paper_id`) USING BTREE,
  CONSTRAINT `FK8u1c6c8l7enuex3k8gvenlo7u` FOREIGN KEY (`org_id`) REFERENCES `torganization` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKk6cxyha4wqi46d29rdy8puvvr` FOREIGN KEY (`paper_id`) REFERENCES `tpaper` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKrll1wrk89cllko74ssicwc3rb` FOREIGN KEY (`announcement_id`) REFERENCES `tannouncement` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for texam_result
-- ----------------------------
DROP TABLE IF EXISTS `texam_result`;
CREATE TABLE `texam_result`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `answer` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `marks` double NULL DEFAULT NULL,
  `sequence` int(11) NOT NULL,
  `submit_time` datetime(0) NOT NULL,
  `driver_id` int(11) NOT NULL,
  `exam_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK9che2n46siqgmg1teltx3tifk`(`driver_id`) USING BTREE,
  INDEX `FKdmia1dcmf1t36iiisdrgfc5an`(`exam_id`) USING BTREE,
  INDEX `FKtm8eub9mx9hgvgnqn9u0r8mg6`(`question_id`) USING BTREE,
  CONSTRAINT `FK9che2n46siqgmg1teltx3tifk` FOREIGN KEY (`driver_id`) REFERENCES `tdriver` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKdmia1dcmf1t36iiisdrgfc5an` FOREIGN KEY (`exam_id`) REFERENCES `texam` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKtm8eub9mx9hgvgnqn9u0r8mg6` FOREIGN KEY (`question_id`) REFERENCES `tquestion` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for texam_signup
-- ----------------------------
DROP TABLE IF EXISTS `texam_signup`;
CREATE TABLE `texam_signup`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `certificate_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `certificate_photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `final_num` int(11) NULL DEFAULT NULL,
  `final_score` double NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `signup_time` datetime(0) NOT NULL,
  `status` smallint(6) NOT NULL,
  `take_exam_time` datetime(0) NULL DEFAULT NULL,
  `driver_id` int(11) NOT NULL,
  `exam_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK8nhwf2voamowk2gne9bm4vsff`(`driver_id`) USING BTREE,
  INDEX `FKrt67r1vj9tog9651x7sxnoepd`(`exam_id`) USING BTREE,
  CONSTRAINT `FK8nhwf2voamowk2gne9bm4vsff` FOREIGN KEY (`driver_id`) REFERENCES `tdriver` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKrt67r1vj9tog9651x7sxnoepd` FOREIGN KEY (`exam_id`) REFERENCES `texam` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tmember
-- ----------------------------
DROP TABLE IF EXISTS `tmember`;
CREATE TABLE `tmember`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NOT NULL,
  `login_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ota_user_id` int(11) NULL DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sfz_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` smallint(6) NOT NULL,
  `create_by` int(11) NULL DEFAULT NULL,
  `org_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKllykrejj1bvbr6vuuhcy6fwn6`(`create_by`) USING BTREE,
  INDEX `FKfybte0u9abj7nyp23n19opary`(`org_id`) USING BTREE,
  CONSTRAINT `FKfybte0u9abj7nyp23n19opary` FOREIGN KEY (`org_id`) REFERENCES `torganization` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKllykrejj1bvbr6vuuhcy6fwn6` FOREIGN KEY (`create_by`) REFERENCES `tmember` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for torganization
-- ----------------------------
DROP TABLE IF EXISTS `torganization`;
CREATE TABLE `torganization`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `linkman_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ota_org_id` int(11) NULL DEFAULT NULL,
  `region_id` bigint(20) NULL DEFAULT NULL,
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `short_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` smallint(6) NOT NULL,
  `tel` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` smallint(6) NOT NULL,
  `link_person_id` int(11) NULL DEFAULT NULL,
  `parent_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK5ynoyq8vklj7b41mt4j0lw70e`(`link_person_id`) USING BTREE,
  INDEX `FKhanty7s710icfmfsugb5dk3ec`(`parent_id`) USING BTREE,
  CONSTRAINT `FK5ynoyq8vklj7b41mt4j0lw70e` FOREIGN KEY (`link_person_id`) REFERENCES `tmember` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKhanty7s710icfmfsugb5dk3ec` FOREIGN KEY (`parent_id`) REFERENCES `torganization` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of torganization
-- ----------------------------
INSERT INTO `torganization` VALUES (1, NULL, 'qwe@qq.com', NULL, '台州卫生局', 7, 331002, NULL, '卫生局', 1, '125455', 3, NULL, NULL);

-- ----------------------------
-- Table structure for tpaper
-- ----------------------------
DROP TABLE IF EXISTS `tpaper`;
CREATE TABLE `tpaper`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `difficulty` double NULL DEFAULT NULL,
  `easy_percent` double NOT NULL,
  `hard_percent` double NOT NULL,
  `medium_percent` double NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pass_num` int(11) NULL DEFAULT NULL,
  `pass_score` int(11) NULL DEFAULT NULL,
  `status` smallint(6) NOT NULL,
  `type` smallint(6) NOT NULL,
  `org_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKpk60kjinu56qtq90jtyyg6u01`(`org_id`) USING BTREE,
  CONSTRAINT `FKpk60kjinu56qtq90jtyyg6u01` FOREIGN KEY (`org_id`) REFERENCES `torganization` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tpaper_design
-- ----------------------------
DROP TABLE IF EXISTS `tpaper_design`;
CREATE TABLE `tpaper_design`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `num` int(11) NOT NULL,
  `question_subject_id` bigint(20) NOT NULL,
  `question_type_id` bigint(20) NOT NULL,
  `paper_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKo66mew0csklciwx5o8i1q6oyd`(`paper_id`) USING BTREE,
  CONSTRAINT `FKo66mew0csklciwx5o8i1q6oyd` FOREIGN KEY (`paper_id`) REFERENCES `tpaper` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tpaper_list
-- ----------------------------
DROP TABLE IF EXISTS `tpaper_list`;
CREATE TABLE `tpaper_list`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sequence` int(11) NULL DEFAULT NULL,
  `paper_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK5sygy5kaq8w99wstvwyepaqki`(`paper_id`) USING BTREE,
  INDEX `FKdcpkj12featq2cd7y8wmaqyec`(`question_id`) USING BTREE,
  CONSTRAINT `FK5sygy5kaq8w99wstvwyepaqki` FOREIGN KEY (`paper_id`) REFERENCES `tpaper` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKdcpkj12featq2cd7y8wmaqyec` FOREIGN KEY (`question_id`) REFERENCES `tquestion` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tquestion
-- ----------------------------
DROP TABLE IF EXISTS `tquestion`;
CREATE TABLE `tquestion`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `answer` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime(0) NOT NULL,
  `difficulty` smallint(6) NULL DEFAULT NULL,
  `marks` double NOT NULL,
  `operator_id` int(11) NULL DEFAULT NULL,
  `pic1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pic2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pic3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `required` smallint(6) NOT NULL,
  `status` smallint(6) NOT NULL,
  `subject_id` bigint(20) NOT NULL,
  `type_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tquestion_items
-- ----------------------------
DROP TABLE IF EXISTS `tquestion_items`;
CREATE TABLE `tquestion_items`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选项正文',
  `pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选项图片',
  `sequence` smallint(6) NOT NULL COMMENT '选项序列',
  `question_id` int(11) NULL DEFAULT NULL COMMENT '原考试系统题目id',
  `resource_question_id` int(11) NULL DEFAULT NULL COMMENT 'learning_resource_question 表 id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE,
  INDEX `FK4709A79B9B9DEE39`(`question_id`) USING BTREE,
  CONSTRAINT `FK4709A79B9B9DEE39` FOREIGN KEY (`question_id`) REFERENCES `tquestion` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK9fp1c8j8g2di45kgbvf6j6yy1` FOREIGN KEY (`question_id`) REFERENCES `tquestion` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
