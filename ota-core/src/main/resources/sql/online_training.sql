/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : online_training

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-03-11 14:54:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for data_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `data_dictionary`;
CREATE TABLE `data_dictionary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `remark` varchar(1000) DEFAULT NULL,
  `action_by` bigint(20) DEFAULT NULL,
  `action_time` datetime DEFAULT NULL,
  `uplink` bigint(20) DEFAULT NULL,
  `ctrl_id` bigint(20) DEFAULT NULL,
  `code_type` varchar(200) DEFAULT NULL,
  `sub_code_type` varchar(200) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `global_seq` bigint(20) DEFAULT NULL,
  `value_str` varchar(100) DEFAULT NULL,
  `value_rule` varchar(200) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `rank` bigint(20) DEFAULT NULL,
  `pic_path` varchar(255) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK7F1484B6F3B0E0A` (`uplink`),
  KEY `FK7F1484BE2AB2864` (`ctrl_id`),
  CONSTRAINT `FK7F1484B6F3B0E0A` FOREIGN KEY (`uplink`) REFERENCES `data_dictionary` (`id`),
  CONSTRAINT `FK7F1484BE2AB2864` FOREIGN KEY (`ctrl_id`) REFERENCES `data_dictionary` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ota_event
-- ----------------------------
DROP TABLE IF EXISTS `ota_event`;
CREATE TABLE `ota_event` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '新闻事件表',
  `name` varchar(100) NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `org_id` int(11) DEFAULT NULL COMMENT '发布组织id',
  `user_id` int(11) NOT NULL COMMENT '发布人id',
  `start_time` datetime DEFAULT NULL COMMENT '事件开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '事件结束时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `org_id` (`org_id`),
  KEY `user_id` (`user_id`),
  KEY `start_time` (`start_time`),
  KEY `end_time` (`end_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ota_event_resource
-- ----------------------------
DROP TABLE IF EXISTS `ota_event_resource`;
CREATE TABLE `ota_event_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '新闻发布案例表',
  `event_id` int(11) NOT NULL COMMENT '事件id',
  `resource_id` int(11) NOT NULL COMMENT '学习资源id',
  `event_time` datetime DEFAULT NULL COMMENT '事件中新闻发布时间',
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `event_id` (`event_id`),
  KEY `resource_id` (`resource_id`),
  KEY `event_time` (`event_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ota_learning_resource
-- ----------------------------
DROP TABLE IF EXISTS `ota_learning_resource`;
CREATE TABLE `ota_learning_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学习资源表',
  `name` varchar(100) NOT NULL,
  `type` tinyint(4) DEFAULT NULL COMMENT '资料类型。1：视频讲座；2：音频讲座；3：图文',
  `url` varchar(255) DEFAULT NULL COMMENT '资料url',
  `description` varchar(1000) DEFAULT NULL COMMENT '资料描述',
  `lecturer_id` int(11) DEFAULT NULL COMMENT '讲师id',
  `lecturer_name` varchar(100) DEFAULT NULL COMMENT '讲师名称',
  `org_id` int(11) DEFAULT NULL COMMENT '发布组织id',
  `level` tinyint(4) DEFAULT NULL COMMENT '等级。1：国家级；2：省部级；3：司厅局级；4：县初级:5：乡镇级',
  `type_id` bigint(20) DEFAULT NULL COMMENT '条线。关联 data_dictionary',
  `user_id` int(11) DEFAULT NULL COMMENT '发布人id',
  `score` tinyint(4) DEFAULT NULL COMMENT '学分',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint(4) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `org_id` (`org_id`),
  KEY `user_id` (`user_id`),
  KEY `type` (`type`),
  KEY `create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ota_learning_resource_auth
-- ----------------------------
DROP TABLE IF EXISTS `ota_learning_resource_auth`;
CREATE TABLE `ota_learning_resource_auth` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学习资料权限表',
  `recource_id` int(11) NOT NULL COMMENT '学习资料id',
  `level_auth` tinyint(4) DEFAULT '1' COMMENT '层级权限。1：仅本层级；2：本级及下级；3：本级及上级；4：所有层级；5：自定义',
  `type_auth` tinyint(4) DEFAULT '1' COMMENT '条线权限。1：仅本条线；2：所有条线；3：自定义',
  `level_ids` varchar(255) DEFAULT NULL COMMENT '当层级权限为自定义时，该字段存储开放的层级id。以分号分隔',
  `type_ids` varchar(255) DEFAULT NULL COMMENT '当条线权限为自定义时，该字段存储开放条线id。以分号分隔',
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `recource_id` (`recource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ota_learning_resource_question
-- ----------------------------
DROP TABLE IF EXISTS `ota_learning_resource_question`;
CREATE TABLE `ota_learning_resource_question` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学习资源内容控制表',
  `resource_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL COMMENT '题目id',
  `time` varchar(20) DEFAULT NULL COMMENT '题目要求作答时间。格式为hh:mm:ss',
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `resource_id` (`resource_id`),
  KEY `question_id` (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ota_lecturer
-- ----------------------------
DROP TABLE IF EXISTS `ota_lecturer`;
CREATE TABLE `ota_lecturer` (
  `id` int(11) NOT NULL COMMENT '讲师表',
  `lecturer_name` varchar(100) NOT NULL COMMENT '讲师名称',
  `lecturer_introduction` varchar(1000) DEFAULT NULL,
  `level` tinyint(4) DEFAULT NULL COMMENT '组织等级。1：国家级；2：省部级；3：司厅局级；4：县初级:5：乡镇级',
  `type_id` bigint(20) DEFAULT NULL COMMENT '条线。关联 data_dictionary',
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ota_organization
-- ----------------------------
DROP TABLE IF EXISTS `ota_organization`;
CREATE TABLE `ota_organization` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '全称',
  `short_name` varchar(50) DEFAULT NULL COMMENT '简称',
  `user_id` int(11) DEFAULT NULL COMMENT '负责人id',
  `area_id` bigint(20) DEFAULT NULL COMMENT '区域id',
  `level` tinyint(4) NOT NULL COMMENT '组织等级。1：国家级；2：省部级；3：司厅局级；4：县初级:5：乡镇级',
  `type_id` bigint(20) DEFAULT NULL COMMENT '条线。关联 data_dictionary',
  `phone` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL COMMENT '父级组织',
  `creator_id` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `level` (`level`),
  KEY `type` (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ota_training_user
-- ----------------------------
DROP TABLE IF EXISTS `ota_training_user`;
CREATE TABLE `ota_training_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '培训成员表',
  `traning_id` int(11) NOT NULL COMMENT '培训id',
  `user_id` int(11) NOT NULL COMMENT '学员id',
  `required_score` tinyint(4) DEFAULT NULL COMMENT '必修学分',
  `optional_socre` tinyint(4) DEFAULT NULL,
  `certificate_no` varchar(100) DEFAULT NULL COMMENT '证书号',
  `certificate_photo` varchar(255) DEFAULT NULL COMMENT '证书照片',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `stauts` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `traning_id` (`traning_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ota_traning
-- ----------------------------
DROP TABLE IF EXISTS `ota_traning`;
CREATE TABLE `ota_traning` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '培训表',
  `name` varchar(100) NOT NULL COMMENT '培训名',
  `org_id` int(11) DEFAULT NULL COMMENT '发起组织',
  `user_id` int(11) DEFAULT NULL COMMENT '发起人',
  `required_score` tinyint(4) DEFAULT NULL COMMENT '必修学分',
  `optional_socre` tinyint(4) DEFAULT NULL COMMENT '选修学分',
  `start_time` datetime DEFAULT NULL COMMENT '培训开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '培训结束时间',
  `sign_start_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '培训报名开始时间',
  `sign_end_time` datetime DEFAULT NULL COMMENT '培训报名截止时间',
  `level` tinyint(4) DEFAULT NULL COMMENT '等级。1：国家级；2：省部级；3：司厅局级；4：县初级:5：乡镇级',
  `type_id` bigint(20) DEFAULT NULL COMMENT '条线。关联 data_dictionary',
  `description` varchar(1000) DEFAULT NULL COMMENT '培训描述',
  `exam_id` int(11) DEFAULT NULL COMMENT '考试id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `org_id` (`org_id`),
  KEY `user_id` (`user_id`),
  KEY `exam_id` (`exam_id`),
  KEY `create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ota_traning_resource
-- ----------------------------
DROP TABLE IF EXISTS `ota_traning_resource`;
CREATE TABLE `ota_traning_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '培训课程表',
  `training_id` int(11) NOT NULL COMMENT '培训id',
  `resource_id` int(11) NOT NULL COMMENT '学习资源id',
  `required` tinyint(4) DEFAULT NULL COMMENT '是否必修。0：非必修；1：必修',
  `score` tinyint(4) DEFAULT NULL COMMENT '学分',
  `status` tinyint(4) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ota_user
-- ----------------------------
DROP TABLE IF EXISTS `ota_user`;
CREATE TABLE `ota_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '姓名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `phone` varchar(50) DEFAULT NULL COMMENT '手机号',
  `email` varchar(50) DEFAULT NULL,
  `org_id` int(11) DEFAULT NULL COMMENT '组织id',
  `area_id` bigint(20) DEFAULT NULL COMMENT '区域id',
  `type` tinyint(4) NOT NULL COMMENT '用户类型。0：平台用户；1：个人用户；2：单位用户',
  `level` tinyint(4) DEFAULT NULL COMMENT '等级',
  `type_id` bigint(20) DEFAULT NULL COMMENT '条线',
  `create_time` datetime DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `org_id` (`org_id`),
  KEY `type` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ota_user_apply
-- ----------------------------
DROP TABLE IF EXISTS `ota_user_apply`;
CREATE TABLE `ota_user_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户审批表',
  `user_id` int(11) NOT NULL COMMENT '申请人id',
  `org_id` int(11) NOT NULL COMMENT '申请组织id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '申请时间',
  `approve_time` datetime DEFAULT NULL COMMENT '审批时间',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '审批状态。1：申请中；0：拒绝；2：通过',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `org_id` (`org_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
