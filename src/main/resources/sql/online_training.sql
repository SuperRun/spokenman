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
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '讲师表',
  `name` varchar(100) NOT NULL COMMENT '讲师名称',
  `introduction` varchar(1000) DEFAULT NULL,
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


INSERT INTO `ota_organization` VALUES ('1', '平台组织', '平台', '1', null, '1', null, null, null, null, '1', '2018-03-31 14:56:36', '1');

-- ----------------------------
-- Table structure for ota_training_user
-- ----------------------------
DROP TABLE IF EXISTS `ota_training_user`;
CREATE TABLE `ota_training_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '培训成员表',
  `training_id` int(11) NOT NULL COMMENT '培训id',
  `user_id` int(11) NOT NULL COMMENT '学员id',
  `required_score` tinyint(4) DEFAULT NULL COMMENT '必修学分',
  `optional_socre` tinyint(4) DEFAULT NULL,
  `certificate_no` varchar(100) DEFAULT NULL COMMENT '证书号',
  `certificate_photo` varchar(255) DEFAULT NULL COMMENT '证书照片',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `stauts` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `training_id` (`training_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ota_training
-- ----------------------------
DROP TABLE IF EXISTS `ota_training`;
CREATE TABLE `ota_training` (
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
-- Table structure for ota_training_resource
-- ----------------------------
DROP TABLE IF EXISTS `ota_training_resource`;
CREATE TABLE `ota_training_resource` (
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
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `org_id` (`org_id`),
  KEY `type` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `ota_user` VALUES ('1', 'admin', '123456', '17826801239', '123@163.com', '1', '平台', null, '0', null, null, '2018-03-26 22:35:51', '1', null, null, null, null, null);

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


-- 讲师表添加 orgId、areaId
ALTER TABLE ota_lecturer ADD COLUMN area_id BIGINT COMMENT '区域id，国标码',
ADD COLUMN org_id INT COMMENT '组织id';

-- 用户表添加 组织名称，用户类型添加讲师 -sdy -2018年3月26日21:33:59
ALTER TABLE ota_user ADD COLUMN org_name VARCHAR(100) COMMENT '组织名称',
MODIFY COLUMN type TINYINT COMMENT '用户类型。0：平台用户；1：个人用户；2：单位用户；3：讲师' NOT NULL;

-- 讲师表添加 用户id，开始、结束时间，组织名称 -sdy -2018年3月26日22:18:03
ALTER TABLE ota_lecturer ADD COLUMN user_id INT COMMENT '用户id' NOT NULL,
ADD COLUMN start_time DATETIME COMMENT '开始时间' DEFAULT CURRENT_TIMESTAMP,
ADD COLUMN end_time DATETIME,ADD COLUMN org_name VARCHAR(100) COMMENT '组织名称';

-- 学习资源表添加 权限类型、发布人姓名 -sdy -2018年3月28日21:36:29
ALTER TABLE ota_learning_resource ADD COLUMN auth_type TINYINT COMMENT '权限类型；1：所有等级可见。0：下级不可见' DEFAULT 1,
ADD COLUMN user_name VARCHAR(100) COMMENT '发布人名称' NOT NULL;

-- 用户表添加 创建人id -sdy -2018年3月31日15:40:53
ALTER TABLE ota_user ADD COLUMN creator_id INT(11) COMMENT '创建人id';

-- 用户表姓名改为可空 -sdy -2018年4月8日22:07:28
ALTER TABLE ota_user MODIFY COLUMN name VARCHAR(100) COMMENT '姓名';

-- 学习资源表添加 时间 -sdy -2018年4月12日17:27:04
ALTER TABLE ota_learning_resource ADD COLUMN time DATETIME COMMENT '学习资源时间';

-- 培训表添加 发布人姓名 -sdy -2018年4月12日22:32:36
ALTER TABLE ota_training ADD COLUMN user_name VARCHAR(100) COMMENT '发布人姓名';

-- 添加个人学习相关表 -sdy -2018年4月20日22:19:39
DROP TABLE IF EXISTS `ota_user_resource`;
CREATE TABLE `ota_user_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户资源查看记录表',
  `user_id` int(11) NOT NULL,
  `resource_id` int(11) NOT NULL,
  `time` varchar(20) DEFAULT NULL COMMENT '上次观看至视频时间',
  `score` tinyint(4) DEFAULT '0' COMMENT '获得学分，学习完成后获得',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint(4) DEFAULT NULL COMMENT '1：学习中；2：学习完成',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `resource_id` (`resource_id`),
  KEY `user_id_2` (`user_id`,`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `ota_user_resource_question`;
CREATE TABLE `ota_user_resource_question` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户培训资源答题记录',
  `user_id` int(11) NOT NULL,
  `resource_question_id` int(11) NOT NULL COMMENT '学习资源问题 id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `resource_question_id` (`resource_question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 更改培训资源题目表结构，添加题目选项表 -sdy -2018年4月22日23:01:47
DROP TABLE IF EXISTS `ota_learning_resource_question`;
CREATE TABLE `ota_learning_resource_question` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学习资源内容控制表',
  `resource_id` int(11) NOT NULL,
  `content` int(11) NOT NULL COMMENT '题干',
  `answer` varchar(100) NOT NULL COMMENT '题目答案。多选题以 && 分隔选项',
  `pic1` varchar(255) DEFAULT NULL,
  `pic2` varchar(255) DEFAULT NULL,
  `pic3` varchar(255) DEFAULT NULL,
  `type_id` bigint(20) NOT NULL COMMENT '题型 id',
  `time` varchar(20) NOT NULL COMMENT '题目要求作答时间。格式为hh:mm:ss',
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `resource_id` (`resource_id`),
  KEY `question_id` (`content`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tquestion_items
-- ----------------------------
DROP TABLE IF EXISTS `tquestion_items`;
CREATE TABLE `tquestion_items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(100) DEFAULT NULL COMMENT '选项正文',
  `pic` varchar(255) DEFAULT NULL COMMENT '选项图片',
  `sequence` smallint(6) NOT NULL COMMENT '选项序列',
  `question_id` int(11) DEFAULT NULL COMMENT '原考试系统题目id',
  `resource_question_id` int(11) DEFAULT NULL COMMENT 'learning_resource_question 表 id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK4709A79B9B9DEE39` (`question_id`),
  CONSTRAINT `FK4709A79B9B9DEE39` FOREIGN KEY (`question_id`) REFERENCES `tquestion` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 学习资源题目表题干字段类型改为 varchar -sdy -2018年5月1日20:11:21
ALTER TABLE ota_learning_resource_question MODIFY COLUMN content VARCHAR(1000) COMMENT '题目题干';

-- 用户培训表添加用户名称、培训名称 -sdy -2018年5月5日17:36:42
ALTER TABLE ota_training_user ADD COLUMN user_name VARCHAR(100) COMMENT '用户名称',
ADD COLUMN training_name VARCHAR(100) COMMENT '培训名称';

-- 修改培训表选修学分名称 -sdy -2018年5月8日18:12:38
ALTER TABLE ota_training CHANGE optional_socre optional_score TINYINT COMMENT '选修学分';

-- 修改用户培训表选修学分、状态名称 -sdy -2018年5月8日20:04:36
ALTER TABLE ota_training_user CHANGE optional_socre optional_score TINYINT COMMENT '选修学分';
ALTER TABLE ota_training_user CHANGE stauts status TINYINT COMMENT '用户培训状态';

-- 用户表添加身份证号、身份证照、组织证明照字段 -sdy -2018年5月10日14:03:30
ALTER TABLE ota_user ADD COLUMN id_card VARCHAR(50) COMMENT '身份证号',
ADD COLUMN pic_front VARCHAR(255) COMMENT '身份证正面照',
ADD COLUMN pic_back VARCHAR(255) COMMENT '身份证反面照',
ADD COLUMN pic_org VARCHAR(255) COMMENT '组织证明照';

-- 培训资源表添加资源搜索相关字段 -sdy -2018年5月14日19:52:10
ALTER TABLE ota_training_resource ADD COLUMN lecturer_name VARCHAR(100) COMMENT '资源讲师姓名',
ADD COLUMN type_id BIGINT COMMENT '资源条线 id',
ADD COLUMN level TINYINT COMMENT '资源等级',
ADD COLUMN resource_name VARCHAR(100) COMMENT '资源名称';

-- 培训用户表添加用户搜索相关字段 -sdy -2018年5月14日20:14:34
ALTER TABLE ota_training_user ADD COLUMN user_area_id BIGINT COMMENT '用户区域id',
ADD COLUMN user_type_id BIGINT COMMENT '用户条线id',
ADD COLUMN user_level TINYINT COMMENT '用户等级';

-- 学习资源添加宣传图片字段 -sdy -2018年5月16日13:51:00
ALTER TABLE ota_learning_resource ADD COLUMN pic VARCHAR(255) COMMENT '宣传图片';

-- 培训表添加宣传图片字段 -sdy -2018年5月16日13:51:13
ALTER TABLE ota_training ADD COLUMN pic VARCHAR(255) COMMENT '宣传图片';

-- 学习资源表添加时长字段 -sdy -2018年5月19日14:13:40
ALTER TABLE ota_learning_resource ADD COLUMN duration INT COMMENT '视频时长，单位：秒';