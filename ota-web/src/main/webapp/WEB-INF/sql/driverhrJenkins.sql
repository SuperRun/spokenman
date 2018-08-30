/*
Navicat MySQL Data Transfer

Source Server         : driverhr_online
Source Server Version : 50712
Source Host           : 121.40.140.223:3306
Source Database       : driverhr

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2016-10-31 17:51:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for data_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `data_dictionary`;
CREATE TABLE `data_dictionary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `remark` varchar(100) DEFAULT NULL,
  `action_by` bigint(20) DEFAULT NULL,
  `action_time` datetime DEFAULT NULL,
  `uplink` bigint(20) DEFAULT NULL COMMENT '上级的id，如果为-1表示为该数据项的根，即名称',
  `ctrl_id` bigint(20) DEFAULT NULL COMMENT '根节点',
  `code_type` varchar(200) DEFAULT NULL COMMENT '数据项的编码，同一组数据项编码相同',
  `sub_code_type` varchar(200) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `global_seq` bigint(20) DEFAULT NULL,
  `value_str` varchar(100) DEFAULT NULL COMMENT '数据项的值(字符串)',
  `value_rule` varchar(200) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `rank` bigint(20) DEFAULT NULL,
  `pic_path` varchar(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `uplink` (`uplink`),
  KEY `ctrl_id` (`ctrl_id`),
  CONSTRAINT `data_dictionary_ibfk_1` FOREIGN KEY (`uplink`) REFERENCES `data_dictionary` (`id`),
  CONSTRAINT `data_dictionary_ibfk_2` FOREIGN KEY (`ctrl_id`) REFERENCES `data_dictionary` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=647 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of data_dictionary
-- ----------------------------
INSERT INTO `data_dictionary` VALUES ('623', '', null, '2016-08-17 09:29:21', null, null, 'driver_honour_type', '', '0', '6230000', ' 荣誉类型', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('626', '', null, '2016-08-17 14:39:55', '623', '623', null, '', '1', '6230100', '劳动模范', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('627', '', null, '2016-08-17 14:40:50', '623', '623', null, '', '1', '6230100', '效率达人', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('628', '', null, '2016-08-17 14:41:19', '623', '623', null, '', '1', '6230100', '认真恪守', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('629', '', null, '2016-08-19 16:08:59', null, null, 'driver_reward_type', '', '0', '6290000', '奖惩类型', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('630', '', null, '2016-08-19 16:13:45', '629', '629', null, '', '1', '6290100', '扣日工资', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('631', '', null, '2016-08-19 16:14:07', '629', '629', null, '', '1', '6290100', '扣周工资', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('632', '', null, '2016-08-19 16:14:38', '629', '629', null, '', '1', '6290100', '千元提成', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('633', '', null, '2016-08-19 16:15:04', '629', '629', null, '', '1', '6290100', '奖励证书', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('634', '', null, '2016-08-22 11:50:16', null, null, 'traffic_violation_type', '', '0', '6340000', '交通违章类型', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('635', '', null, '2016-08-22 11:50:41', '634', '634', null, '', '1', '6340100', '追尾', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('636', '', null, '2016-08-22 11:51:31', '634', '634', null, '', '1', '6340100', '剐蹭', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('637', '', null, '2016-08-22 11:52:28', '634', '634', null, '', '1', '6340100', '侧翻', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('638', '', null, '2016-08-22 16:48:51', null, null, 'compensation_type', '', '0', '6380000', '理赔类型', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('639', '', null, '2016-08-22 16:50:01', '638', '638', null, '', '1', '6380100', '给付型', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('640', '', null, '2016-08-22 16:50:08', '638', '638', null, '', '1', '6380100', '补偿型', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('641', '', null, '2016-09-29 16:52:48', null, null, 'driverhr_question_type', '', '0', '6410000', '题目题型', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('642', '', null, '2016-09-29 16:53:13', '641', '641', null, '', '1', '6410100', '选择题', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('643', '', null, '2016-09-29 16:53:42', '641', '641', null, '', '1', '6410100', '判断题', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('644', '', null, '2016-09-29 16:54:05', null, null, 'driverhr_question_subject', '', '0', '6440000', '题目知识点', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('645', '', null, '2016-09-29 16:54:45', '644', '644', null, '', '1', '6440100', '行车安全', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('646', '', null, '2016-09-29 16:55:49', '644', '644', null, '', '1', '6440100', '职业素养', null, '', null, '', '1');

-- ----------------------------
-- Table structure for tannouncement
-- ----------------------------
DROP TABLE IF EXISTS `tannouncement`;
CREATE TABLE `tannouncement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `attachment` varchar(255) DEFAULT NULL,
  `title` varchar(100) NOT NULL COMMENT '标题',
  `text` varchar(2000) DEFAULT NULL COMMENT '正文',
  `type` varchar(11) DEFAULT NULL,
  `source_organization_id` int(11) NOT NULL COMMENT '创建人所在的org，也就是公告来源',
  `create_by` int(11) NOT NULL COMMENT '创建人',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `closure_date` timestamp NULL DEFAULT NULL COMMENT '有效期，若为空  有效期就是无限大',
  `read_count` int(11) NOT NULL DEFAULT '0' COMMENT '阅读次数',
  PRIMARY KEY (`id`),
  KEY `source_org_idx` (`source_organization_id`),
  KEY `creater_idx` (`create_by`),
  CONSTRAINT `creater` FOREIGN KEY (`create_by`) REFERENCES `tmember` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `source_org` FOREIGN KEY (`source_organization_id`) REFERENCES `torganization` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='公告表';

-- ----------------------------
-- Records of tannouncement
-- ----------------------------
INSERT INTO `tannouncement` VALUES ('1', '', '水泥车安检', '2016年铁路和公路客运安检规定，除了易燃、易爆、毒害性、腐蚀性、放射性、传染病病原体等物质以及枪支、弹药、管制刀 具等可能危害公共安全的物品以外，不在管制刀具范围内的菜刀、餐刀、大型水果刀、工艺品刀等也被禁止随身携带。可能危及旅客人身安全的菜刀、餐刀、屠宰刀、斧子等利器、钝器；射钉枪、防卫器、弓、弩等其他器具都禁止携带进站上车。\r\n　　此外，对安全火柴、普通打火机等可以限量携带的物品，对其限制携带数量进行了调整。例如，旅客可携带的摩丝、发胶 、杀虫剂、空气清新剂等自喷压力容器由600ml压缩为120ml，指甲油、去光剂、染发剂仍为20ml。安全火柴限带两小盒；普通 打火机只能带两个。铁路执行安全新规禁带剪刀、锤子、打火机限带两个。\r\n　　另据了解，新目录还增加了危害行车运行安全或公共卫生的禁带物品，包括可能干扰到列车信号的强磁化物、有强烈刺激 性气味的物品，有恶臭等异味的物品，特别将宠物、家禽等“活动物”单独列出，但导盲犬除外。', '1,2,3,4', '1', '1', '2016-08-31 08:55:06', '2016-08-24 08:53:53', '0');
INSERT INTO `tannouncement` VALUES ('2', null, '换轮胎', '轮胎是否需要更换无法仅通过行驶里程或者年限判断，出现以下情况就需要换胎了：\r\n\r\n　　1、轮胎磨损至磨损极限标志时必须被停止使用（即胎面花纹深度低于1.6毫米）。\r\n\r\n　　2、轮胎老化。\r\n\r\n　　3、胎面变形或胎圈损伤的轮胎，缺气行驶造成胎侧被碾压受损的轮胎，被化学品腐蚀的轮胎不得继续使用。\r\n\r\n　　4、其它受损若影响轮胎使用安全也不得继续使用\r\n\r\n　　车辆一年该做几次定位是由车况和行驶的路况决定的,从常规来讲,车每行驶8000到10000公里左右就该重新做定位检查,所以一年至少要做两次定位检查。至少一个月一次检查轮胎磨损的情况。\r\n\r\n　　如果轮胎磨损不均匀，例如:轮胎一侧胎肩磨损快于胎面其余部分。或者如果您发觉车辆过度抖动或跑偏的话，您的车辆可能定位不良或不平衡。这些情况不仅缩短轮胎寿命，而且影响车辆的操控性，可能出现危险。如果您发觉不规则磨损或车辆抖动或跑偏，请马上检查车轮定位和平衡。', '4', '1', '1', '2016-08-31 08:58:57', '2016-08-30 08:58:53', '0');
INSERT INTO `tannouncement` VALUES ('3', '', '新通知', '新通知新通知新通知新通知新通知', '3', '1', '1', '2016-10-08 19:42:44', null, '0');

-- ----------------------------
-- Table structure for tauth
-- ----------------------------
DROP TABLE IF EXISTS `tauth`;
CREATE TABLE `tauth` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(200) DEFAULT NULL,
  `is_menu` smallint(6) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `seq` int(11) DEFAULT NULL,
  `type` smallint(6) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK690841C893854F2` (`pid`),
  CONSTRAINT `FK690841C893854F2` FOREIGN KEY (`pid`) REFERENCES `tauth` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tauth
-- ----------------------------

-- ----------------------------
-- Table structure for tauth_group
-- ----------------------------
DROP TABLE IF EXISTS `tauth_group`;
CREATE TABLE `tauth_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `status` smallint(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tauth_group
-- ----------------------------

-- ----------------------------
-- Table structure for tauth_group_list
-- ----------------------------
DROP TABLE IF EXISTS `tauth_group_list`;
CREATE TABLE `tauth_group_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `auth_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK3D6F126162AD7679` (`auth_id`),
  KEY `FK3D6F1261C086E233` (`group_id`),
  CONSTRAINT `FK3D6F126162AD7679` FOREIGN KEY (`auth_id`) REFERENCES `tauth` (`id`),
  CONSTRAINT `FK3D6F1261C086E233` FOREIGN KEY (`group_id`) REFERENCES `tauth_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tauth_group_list
-- ----------------------------

-- ----------------------------
-- Table structure for tdepartment
-- ----------------------------
DROP TABLE IF EXISTS `tdepartment`;
CREATE TABLE `tdepartment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` date NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `status` smallint(6) NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `create_by` int(11) NOT NULL,
  `org_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FKAA2F42C653A82C48` (`org_id`),
  KEY `FKAA2F42C64D5B3B13` (`create_by`),
  KEY `FKAA2F42C6F45C2EA1` (`parent_id`),
  CONSTRAINT `FKAA2F42C64D5B3B13` FOREIGN KEY (`create_by`) REFERENCES `tmember` (`id`),
  CONSTRAINT `FKAA2F42C653A82C48` FOREIGN KEY (`org_id`) REFERENCES `torganization` (`id`),
  CONSTRAINT `FKAA2F42C6F45C2EA1` FOREIGN KEY (`parent_id`) REFERENCES `tdepartment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tdepartment
-- ----------------------------
INSERT INTO `tdepartment` VALUES ('1', '2016-09-27', '1323123ddddd', '12312312', '1', null, '1', '1');
INSERT INTO `tdepartment` VALUES ('2', '2016-09-28', '312312312', '1231231212', '1', null, '108', '31');

-- ----------------------------
-- Table structure for tdriver
-- ----------------------------
DROP TABLE IF EXISTS `tdriver`;
CREATE TABLE `tdriver` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `birth_place` varchar(50) NOT NULL,
  `birth` date DEFAULT NULL,
  `gender` smallint(6) DEFAULT NULL,
  `sfz_no` varchar(20) NOT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `postcode` varchar(10) DEFAULT NULL,
  `mobile` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `org_id` int(11) NOT NULL,
  `emergency_contact` varchar(50) DEFAULT NULL,
  `emergency_contact_mobile` varchar(50) DEFAULT NULL,
  `drive_licence_no` varchar(20) DEFAULT NULL,
  `drive_lincence_photo` varchar(255) DEFAULT NULL,
  `drive_licence_start_time` date DEFAULT NULL,
  `drive_licence_end_time` date DEFAULT NULL,
  `safe_centificate_no` varchar(20) DEFAULT NULL,
  `safe_centificate_photo` varchar(255) DEFAULT NULL,
  `safe_centificate_start_time` date DEFAULT NULL,
  `safe_centificate_end_time` date DEFAULT NULL,
  `status` smallint(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `sfz_no` (`sfz_no`),
  KEY `FKA96F719C53A82C48` (`org_id`),
  CONSTRAINT `FKA96F719C53A82C48` FOREIGN KEY (`org_id`) REFERENCES `torganization` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tdriver
-- ----------------------------
INSERT INTO `tdriver` VALUES ('1', '张三', '浙江', '1994-06-17', '0', '123456', null, null, null, '18800008888', '123456', null, '18', null, null, null, null, null, null, null, null, null, null, '1');
INSERT INTO `tdriver` VALUES ('2', '李四', '浙江', '1993-11-18', '0', '234567', null, null, null, '123456', '234567', null, '18', null, null, null, null, null, null, null, null, null, null, '1');
INSERT INTO `tdriver` VALUES ('3', '王五', '浙江', '1990-06-27', '1', '324555', null, null, null, null, null, null, '18', null, null, null, null, null, null, null, null, null, null, '1');
INSERT INTO `tdriver` VALUES ('4', '梁朝伟', '香港', '1972-10-12', '1', '145024197210123456', '/driverhr/uploadFile/hr/driver/driver_photo/null_1472101631591.jpg', '上海', '050000', '13930445556', '123456', 'lcw@163.com', '1', '刘嘉玲', '13933055000', '339182771891778261', '/driverhr/uploadFile/hr/driver/drive_lincence_photo/null_1472105465636.jpg', '2012-01-01', '2018-12-31', '6672002364', '/driverhr/uploadFile/hr/driver/safe_centificate_photo/null_1472105469973.jpg', '2012-01-01', '2018-10-26', '1');
INSERT INTO `tdriver` VALUES ('12', '张三', '浙江省', '2016-02-02', '1', '142623199410261234', null, null, null, '18800008888', '261234', null, '1', null, null, '339182771891778269', null, null, null, null, null, null, null, '1');
INSERT INTO `tdriver` VALUES ('13', 'liy', 'hb', null, '0', '123456798112123456', null, null, null, '12312312312', '123456', null, '1', null, null, '339182771891778261', null, null, null, null, null, null, null, '0');
INSERT INTO `tdriver` VALUES ('14', 'liy', 'hb', null, '0', '123456798114123456', null, null, null, '12312312312', '123456', null, '1', null, null, '339182771891778261', null, null, null, null, null, null, null, '-2');
INSERT INTO `tdriver` VALUES ('15', 'lidong', '河北', null, '0', '142623197410261234', null, null, null, '12312312312', '261234', null, '1', null, null, '339182771891778261', null, null, null, null, null, null, null, '-1');
INSERT INTO `tdriver` VALUES ('16', '马德华', '香港', null, '1', '145269855365123456', null, null, null, '12312312316', '123456', null, '1', null, null, '339182771891778261', null, null, null, null, null, null, null, '1');
INSERT INTO `tdriver` VALUES ('18', '想得美', '香港', null, '0', '145264632365123456', null, null, null, '77733123163', '123456', null, '1', null, null, '339182771891778261', null, null, null, null, null, null, null, '0');
INSERT INTO `tdriver` VALUES ('19', '薛之谦', '大陆', null, '1', '432264632365123456', null, null, null, '13233123163', '123456', null, '1', null, null, '339182771891778261', null, null, null, null, null, null, null, '0');
INSERT INTO `tdriver` VALUES ('20', '杨颖', '大陆', null, '0', '142262332365123456', null, null, null, '15033123163', '123456', null, '1', null, null, '339182771891778261', null, null, null, null, null, null, null, '1');
INSERT INTO `tdriver` VALUES ('21', '张韶涵', '大陆', '3928-05-12', '0', '142262392365123456', '/driverhr/uploadFile/hr/driver/driver_photo/null_1472114176551.png', '', '', '15063123163', '123456', '', '1', '', '', '339182771891778261', '', null, null, '', '', null, null, '1');
INSERT INTO `tdriver` VALUES ('22', '吴亦凡', '大陆', '9638-05-12', '1', '456123963541123456', '/driverhr/uploadFile/hr/driver/driver_photo/null_1472114044399.jpg', '', '', '13865547841', '123456', '', '1', '', '', '339182771891778261', '', null, null, '', '', null, null, '1');
INSERT INTO `tdriver` VALUES ('23', '鹿晗', '大陆', null, '1', '456633963541123456', null, null, null, '13864447841', '123456', null, '1', null, null, '339182771891778261', null, null, null, null, null, null, null, '1');
INSERT INTO `tdriver` VALUES ('24', '刘德华', '香港', null, '1', '144152655365123456', null, null, null, '12312312316', '123456', null, '1', null, null, '339182771891778261', null, null, null, null, null, null, null, '1');
INSERT INTO `tdriver` VALUES ('25', '周星驰', '香港', '8558-05-12', '1', '145264197210123456', '/driverhr/uploadFile/hr/driver/driver_photo/null_1472104755415.jpg', '', '', '12312312316', '123456', '', '1', '', '', '339182771891778261', '/driverhr/uploadFile/hr/driver/drive_lincence_photo/null_1472104963543.jpg', null, null, '400830027', '/driverhr/uploadFile/hr/driver/safe_centificate_photo/null_1472104968162.jpg', null, null, '1');
INSERT INTO `tdriver` VALUES ('26', '孙红雷', '大陆', '1966-10-12', '1', '145264196610123456', '/driverhr/uploadFile/hr/driver/driver_photo/null_1472113973367.jpg', '', '', '13800001111', '123456', '', '1', '', '', '', '', null, null, '', '', null, null, '1');
INSERT INTO `tdriver` VALUES ('27', '唐尼', '英国', '1972-10-12', '1', '145263197210123456', '/driverhr/uploadFile/hr/driver/driver_photo/null_1472115360292.jpg', '', '', '15022228888', '123456', '', '1', '', '', '', '', null, null, '', '', null, null, '1');
INSERT INTO `tdriver` VALUES ('28', 'qwe', 'w', '1994-10-12', '1', '154152199410123459', null, '123', '123', '13855552222', '123459', '123@126.com', '1', '123', '123', '123', null, '2008-12-29', '2014-06-10', '123', null, '2009-06-08', '2014-06-04', '0');
INSERT INTO `tdriver` VALUES ('29', '阿斯蒂芬', '大陆', '1966-10-20', '0', '145694196610203456', '/driverhr/uploadFile/hr/driver/driver_photo/null_1472099434027.png', '', '', '13811100213', '203456', '', '1', '', '', '', null, null, null, '', null, null, null, '0');
INSERT INTO `tdriver` VALUES ('30', 'qewss', '大陆', '1966-10-20', '0', '145694196610203485', '/driverhr/uploadFile/hr/driver/driver_photo/null_1472101331286.jpg', '', '', '13800001156', '203485', '', '1', '', '', '123123123333', '/driverhr/uploadFile/hr/driver/drive_lincence_photo/null_1472102452671.jpg', null, null, '123123123', '/driverhr/uploadFile/hr/driver/safe_centificate_photo/null_1472102457885.jpg', null, null, '1');

-- ----------------------------
-- Table structure for tdriver_honour
-- ----------------------------
DROP TABLE IF EXISTS `tdriver_honour`;
CREATE TABLE `tdriver_honour` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '驾驶员荣誉记录',
  `driver_id` int(11) NOT NULL COMMENT '驾驶员id',
  `honour_type_id` bigint(20) NOT NULL COMMENT '预定义的荣誉类型',
  `honour_time` date DEFAULT NULL COMMENT '荣誉授予时间',
  `honour_org_id` int(11) DEFAULT NULL COMMENT '荣誉授予机构',
  `remark` varchar(255) DEFAULT NULL COMMENT '荣誉备注',
  `status` smallint(6) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `driver_id` (`driver_id`),
  KEY `honour_org_id` (`honour_org_id`),
  CONSTRAINT `tdriver_honour_ibfk_1` FOREIGN KEY (`driver_id`) REFERENCES `tdriver` (`id`),
  CONSTRAINT `tdriver_honour_ibfk_2` FOREIGN KEY (`honour_org_id`) REFERENCES `torganization` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tdriver_honour
-- ----------------------------
INSERT INTO `tdriver_honour` VALUES ('1', '4', '627', '2016-08-16', '1', '继续努力', '0');
INSERT INTO `tdriver_honour` VALUES ('2', '4', '628', '2016-08-05', '1', '表现良好', '0');
INSERT INTO `tdriver_honour` VALUES ('3', '4', '626', '2016-08-12', '1', '去玩儿', '0');
INSERT INTO `tdriver_honour` VALUES ('8', '16', '628', '2016-08-18', '1', null, '0');
INSERT INTO `tdriver_honour` VALUES ('9', '16', '626', '2016-08-18', '1', null, '0');
INSERT INTO `tdriver_honour` VALUES ('10', '16', '627', '2016-08-18', '1', null, '0');
INSERT INTO `tdriver_honour` VALUES ('11', '24', '626', '2016-08-19', '1', null, '0');

-- ----------------------------
-- Table structure for tdriver_insurance_compensation
-- ----------------------------
DROP TABLE IF EXISTS `tdriver_insurance_compensation`;
CREATE TABLE `tdriver_insurance_compensation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `insurance_id` varchar(50) DEFAULT NULL COMMENT '保险编号',
  `compensation_id` varchar(50) DEFAULT NULL COMMENT '理赔编号',
  `insurance_company` varchar(50) DEFAULT NULL COMMENT '保险公司名称',
  `police_id` varchar(50) DEFAULT NULL COMMENT '对应的交警数据库中的违章编号',
  `driver_id` int(11) DEFAULT NULL COMMENT '驾驶员id',
  `car_no` varchar(10) DEFAULT NULL COMMENT '车牌号',
  `compensation_type_id` bigint(20) DEFAULT NULL COMMENT '理赔类型',
  `compensation_fee` double DEFAULT NULL COMMENT '理赔费用',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` smallint(6) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `driver_id` (`driver_id`),
  CONSTRAINT `tdriver_insurance_compensation_ibfk_1` FOREIGN KEY (`driver_id`) REFERENCES `tdriver` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tdriver_insurance_compensation
-- ----------------------------
INSERT INTO `tdriver_insurance_compensation` VALUES ('1', '030107', '010023', '中国人寿', '110022', '4', '浙A88888', '640', '3200', 'ok', '0');
INSERT INTO `tdriver_insurance_compensation` VALUES ('2', '020105', '010049', '平安保险', '110033', '4', '浙A00000', '640', '5700', 'no', '0');
INSERT INTO `tdriver_insurance_compensation` VALUES ('3', '020508', '010097', '中国人寿', '110012', '4', '冀A66666', '639', '4388', null, '0');

-- ----------------------------
-- Table structure for tdriver_reward
-- ----------------------------
DROP TABLE IF EXISTS `tdriver_reward`;
CREATE TABLE `tdriver_reward` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '驾驶员奖惩',
  `driver_id` int(11) NOT NULL COMMENT '驾驶员id',
  `reward_type_id` bigint(20) NOT NULL COMMENT '预定义的奖惩id',
  `reward_time` date DEFAULT NULL COMMENT '奖惩时间',
  `reward_org_id` int(11) DEFAULT NULL COMMENT '奖惩机构',
  `remark` varchar(255) DEFAULT NULL COMMENT '奖惩备注',
  `status` smallint(6) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `driver_id` (`driver_id`),
  KEY `reward_org_id` (`reward_org_id`),
  CONSTRAINT `tdriver_reward_ibfk_1` FOREIGN KEY (`driver_id`) REFERENCES `tdriver` (`id`),
  CONSTRAINT `tdriver_reward_ibfk_2` FOREIGN KEY (`reward_org_id`) REFERENCES `torganization` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tdriver_reward
-- ----------------------------
INSERT INTO `tdriver_reward` VALUES ('1', '4', '632', '2016-08-19', '1', '还没发', '0');
INSERT INTO `tdriver_reward` VALUES ('2', '4', '633', '2016-08-16', '1', 'ok', '0');
INSERT INTO `tdriver_reward` VALUES ('3', '4', '630', '2016-08-01', '1', 'no', '0');
INSERT INTO `tdriver_reward` VALUES ('4', '4', '630', '2016-08-22', '1', '', '0');
INSERT INTO `tdriver_reward` VALUES ('8', '4', '632', '2016-08-24', '1', '去玩儿1', '0');

-- ----------------------------
-- Table structure for tdriver_star
-- ----------------------------
DROP TABLE IF EXISTS `tdriver_star`;
CREATE TABLE `tdriver_star` (
  `id` int(11) NOT NULL,
  `judge_time` tinyblob,
  `new_start` int(11) DEFAULT NULL,
  `old_start` int(11) DEFAULT NULL,
  `operator_id` int(11) DEFAULT NULL,
  `plan_id` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `driver_id` tinyblob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tdriver_star
-- ----------------------------

-- ----------------------------
-- Table structure for tdriver_traffic_violation
-- ----------------------------
DROP TABLE IF EXISTS `tdriver_traffic_violation`;
CREATE TABLE `tdriver_traffic_violation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '驾驶员交通违章记录',
  `police_id` varchar(50) DEFAULT NULL COMMENT '对应交警数据库中的违章编号',
  `driver_id` int(11) NOT NULL COMMENT '驾驶员id',
  `car_no` varchar(10) DEFAULT NULL COMMENT '车牌号',
  `traffic_violation_type_id` bigint(20) NOT NULL COMMENT '交通违章类型id（来自数据字典）',
  `occur_place` varchar(100) DEFAULT NULL COMMENT '事故发生地点',
  `occur_time` datetime DEFAULT NULL COMMENT '事故发生时间',
  `speed` double DEFAULT NULL COMMENT '违章发生时的速度',
  `death_num` int(11) DEFAULT NULL COMMENT '死亡人数',
  `injured_num` int(11) DEFAULT NULL COMMENT '受伤人数',
  `data1` varchar(10) DEFAULT NULL COMMENT '直行还是转弯',
  `data2` varchar(10) DEFAULT NULL COMMENT '主动还是被动',
  `data3` varchar(10) DEFAULT NULL COMMENT '头部还是尾部',
  `data4` varchar(10) DEFAULT NULL COMMENT '碰撞前制动还是之后制动',
  `data5` varchar(10) DEFAULT NULL COMMENT '碰撞对象（行人、自行车、电动车等）',
  `remark` varchar(255) DEFAULT NULL,
  `status` smallint(6) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `driver_id` (`driver_id`),
  CONSTRAINT `tdriver_traffic_violation_ibfk_1` FOREIGN KEY (`driver_id`) REFERENCES `tdriver` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tdriver_traffic_violation
-- ----------------------------
INSERT INTO `tdriver_traffic_violation` VALUES ('1', '110033', '4', null, '635', '文一路', '2015-08-16 00:00:00', '60', '0', '2', '转弯', '被动', '尾部', '前', '汽车', 'ok', '0');
INSERT INTO `tdriver_traffic_violation` VALUES ('3', '110022', '4', null, '637', '三环', '2013-05-04 00:00:00', '88', '4', '2', '转弯', '主动', '尾部', '后', '桥', 'no', '0');
INSERT INTO `tdriver_traffic_violation` VALUES ('5', '135131', '4', null, '635', '三环', '2014-06-10 00:00:00', '35', '1', '1', '转弯', '主动', '尾部', '后', '桥', '去玩儿22', '0');
INSERT INTO `tdriver_traffic_violation` VALUES ('6', '1233', '4', null, '635', '', '2014-06-11 00:00:00', '12', '1', '1', '', '', '', '', '', '', '0');

-- ----------------------------
-- Table structure for tdriver_work_history
-- ----------------------------
DROP TABLE IF EXISTS `tdriver_work_history`;
CREATE TABLE `tdriver_work_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '驾驶员工作经历',
  `driver_id` int(11) NOT NULL COMMENT '驾驶员id',
  `start_date` date DEFAULT NULL COMMENT '经历开始日期',
  `end_date` date DEFAULT NULL COMMENT '经历截止日期',
  `org_id` int(11) DEFAULT NULL COMMENT '所在车企id',
  `remark` varchar(255) DEFAULT NULL COMMENT '经历备注',
  `level` int(11) DEFAULT NULL COMMENT '经历等级-以备分级查询',
  `status` smallint(6) NOT NULL,
  `org_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `driver_id` (`driver_id`),
  KEY `org_id` (`org_id`),
  CONSTRAINT `tdriver_work_history_ibfk_1` FOREIGN KEY (`driver_id`) REFERENCES `tdriver` (`id`),
  CONSTRAINT `tdriver_work_history_ibfk_2` FOREIGN KEY (`org_id`) REFERENCES `torganization` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tdriver_work_history
-- ----------------------------
INSERT INTO `tdriver_work_history` VALUES ('1', '4', '2016-08-09', '2016-08-12', '1', null, '1', '0', null);
INSERT INTO `tdriver_work_history` VALUES ('2', '4', '2016-08-15', '2016-08-16', '1', null, '1', '0', null);
INSERT INTO `tdriver_work_history` VALUES ('3', '4', '2015-10-14', '2015-12-02', '1', null, '1', '0', null);
INSERT INTO `tdriver_work_history` VALUES ('5', '25', '2014-08-03', '2014-08-04', '1', '', '1', '0', null);
INSERT INTO `tdriver_work_history` VALUES ('6', '25', '2016-08-03', '2016-08-04', '1', '', '1', '0', null);
INSERT INTO `tdriver_work_history` VALUES ('7', '25', '2016-08-05', '2016-08-07', '1', '', '1', '0', null);
INSERT INTO `tdriver_work_history` VALUES ('8', '4', '2016-08-04', '2016-08-06', '1', '', '1', '0', null);
INSERT INTO `tdriver_work_history` VALUES ('9', '4', '2016-08-03', '2016-08-05', '1', '', '1', '0', null);
INSERT INTO `tdriver_work_history` VALUES ('10', '4', '2014-06-10', '2017-09-18', '1', '去玩儿3', '2', '0', null);

-- ----------------------------
-- Table structure for texam
-- ----------------------------
DROP TABLE IF EXISTS `texam`;
CREATE TABLE `texam` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '考试表（正式考试、模拟考试）',
  `name` varchar(50) NOT NULL COMMENT '考试名称',
  `description` varchar(255) DEFAULT NULL COMMENT '考试描述',
  `create_time` datetime NOT NULL,
  `org_id` int(11) NOT NULL,
  `paper_id` int(11) DEFAULT NULL,
  `signup_start_time` datetime DEFAULT NULL COMMENT '报名开始时间',
  `signup_end_time` datetime DEFAULT NULL COMMENT '报名结束时间',
  `exam_start_time` datetime DEFAULT NULL COMMENT '考试开始时间',
  `exam_end_time` datetime DEFAULT NULL COMMENT '考试结束时间',
  `status` smallint(6) NOT NULL COMMENT '考试状态，-1：已取消；0：已发布；1：报名中；2：待考试；3：考试中；4：阅卷中；5：证书录入中；6：已完成',
  `type` smallint(6) NOT NULL COMMENT '考试类型，0：线上正式考试；1：模拟考试；2：线下正式考试',
  `duration` int(11) NOT NULL COMMENT '考试持续时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK6925E9353A82C48` (`org_id`),
  KEY `FK6925E93E10B685B` (`paper_id`),
  CONSTRAINT `FK6925E9353A82C48` FOREIGN KEY (`org_id`) REFERENCES `torganization` (`id`),
  CONSTRAINT `FK6925E93E10B685B` FOREIGN KEY (`paper_id`) REFERENCES `tpaper` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of texam
-- ----------------------------
INSERT INTO `texam` VALUES ('1', '测试模拟考试', '模拟考试', '2016-10-30 21:51:54', '1', '1', null, null, null, null, '1', '1', '100');
INSERT INTO `texam` VALUES ('2', '测试线上考试', '', '2016-10-30 21:53:19', '1', '2', '2016-10-30 00:00:00', '2016-10-30 21:55:00', '2016-10-30 22:00:00', '2016-10-30 22:20:00', '6', '0', '60');
INSERT INTO `texam` VALUES ('3', '测试线下考试', '线下考试', '2016-10-30 21:56:27', '1', '3', '2016-10-30 00:00:00', '2016-10-30 22:00:00', '2016-10-30 22:05:00', '2016-10-30 22:25:00', '5', '2', '4495');
INSERT INTO `texam` VALUES ('4', '报名考试测试', '', '2016-10-30 22:38:37', '1', '2', '2016-10-30 00:00:00', '2016-11-01 23:00:00', '2016-11-03 09:00:00', '2016-11-03 11:00:00', '0', '0', '120');

-- ----------------------------
-- Table structure for texam_practice
-- ----------------------------
DROP TABLE IF EXISTS `texam_practice`;
CREATE TABLE `texam_practice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '驾驶员参加模拟考试记录表',
  `create_time` datetime NOT NULL,
  `final_score` double DEFAULT NULL COMMENT '模拟考试成绩',
  `driver_id` int(11) NOT NULL,
  `exam_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK2D0209673A5D4F19` (`exam_id`),
  KEY `FK2D020967C99AF2B9` (`driver_id`),
  CONSTRAINT `FK2D0209673A5D4F19` FOREIGN KEY (`exam_id`) REFERENCES `texam` (`id`),
  CONSTRAINT `FK2D020967C99AF2B9` FOREIGN KEY (`driver_id`) REFERENCES `tdriver` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of texam_practice
-- ----------------------------
INSERT INTO `texam_practice` VALUES ('1', '2016-10-30 22:01:22', '0', '1', '1');
INSERT INTO `texam_practice` VALUES ('2', '2016-10-30 22:22:16', '0', '1', '1');
INSERT INTO `texam_practice` VALUES ('3', '2016-10-31 17:29:41', '5', '1', '1');

-- ----------------------------
-- Table structure for texam_practice_result
-- ----------------------------
DROP TABLE IF EXISTS `texam_practice_result`;
CREATE TABLE `texam_practice_result` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '模拟考试结果表',
  `answer` longtext,
  `marks` double DEFAULT NULL COMMENT '得分',
  `sequence` int(11) NOT NULL COMMENT '序号',
  `submit_time` datetime NOT NULL COMMENT '提交时间',
  `exam_practice_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FKACA8A9F59B9DEE39` (`question_id`),
  KEY `FKACA8A9F591E4A7B8` (`exam_practice_id`),
  CONSTRAINT `FKACA8A9F591E4A7B8` FOREIGN KEY (`exam_practice_id`) REFERENCES `texam_practice` (`id`),
  CONSTRAINT `FKACA8A9F59B9DEE39` FOREIGN KEY (`question_id`) REFERENCES `tquestion` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of texam_practice_result
-- ----------------------------
INSERT INTO `texam_practice_result` VALUES ('1', 'A', null, '1', '2016-10-31 17:29:49', '3', '8');
INSERT INTO `texam_practice_result` VALUES ('2', 'A', null, '2', '2016-10-31 17:29:52', '3', '15');
INSERT INTO `texam_practice_result` VALUES ('3', 'B', null, '3', '2016-10-31 17:29:54', '3', '16');
INSERT INTO `texam_practice_result` VALUES ('4', 'A', null, '4', '2016-10-31 17:29:56', '3', '24');
INSERT INTO `texam_practice_result` VALUES ('5', 'B', null, '5', '2016-10-31 17:29:58', '3', '4');
INSERT INTO `texam_practice_result` VALUES ('6', 'A', null, '6', '2016-10-31 17:30:00', '3', '12');
INSERT INTO `texam_practice_result` VALUES ('7', 'B', null, '7', '2016-10-31 17:30:02', '3', '11');
INSERT INTO `texam_practice_result` VALUES ('8', 'A', null, '8', '2016-10-31 17:30:04', '3', '10');
INSERT INTO `texam_practice_result` VALUES ('9', 'B', null, '9', '2016-10-31 17:30:06', '3', '18');

-- ----------------------------
-- Table structure for texam_result
-- ----------------------------
DROP TABLE IF EXISTS `texam_result`;
CREATE TABLE `texam_result` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '正式考试结果表',
  `answer` longtext,
  `marks` double DEFAULT NULL COMMENT '得分',
  `sequence` int(11) NOT NULL COMMENT '序号',
  `submit_time` datetime NOT NULL COMMENT '提交时间',
  `driver_id` int(11) NOT NULL,
  `exam_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK8FB26B493A5D4F19` (`exam_id`),
  KEY `FK8FB26B49C99AF2B9` (`driver_id`),
  KEY `FK8FB26B499B9DEE39` (`question_id`),
  CONSTRAINT `FK8FB26B493A5D4F19` FOREIGN KEY (`exam_id`) REFERENCES `texam` (`id`),
  CONSTRAINT `FK8FB26B499B9DEE39` FOREIGN KEY (`question_id`) REFERENCES `tquestion` (`id`),
  CONSTRAINT `FK8FB26B49C99AF2B9` FOREIGN KEY (`driver_id`) REFERENCES `tdriver` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of texam_result
-- ----------------------------
INSERT INTO `texam_result` VALUES ('1', 'A', '0', '1', '2016-10-30 21:57:05', '1', '2', '14');
INSERT INTO `texam_result` VALUES ('2', 'A', '0', '2', '2016-10-30 21:57:05', '1', '2', '13');
INSERT INTO `texam_result` VALUES ('3', 'A', '0', '3', '2016-10-30 21:57:05', '1', '2', '9');
INSERT INTO `texam_result` VALUES ('4', 'A', '0', '4', '2016-10-30 21:57:05', '1', '2', '11');
INSERT INTO `texam_result` VALUES ('5', 'A', '0', '5', '2016-10-30 21:57:05', '1', '2', '10');
INSERT INTO `texam_result` VALUES ('6', 'A', '0', '6', '2016-10-30 21:57:05', '1', '2', '27');
INSERT INTO `texam_result` VALUES ('7', 'B', '0', '7', '2016-10-30 21:57:05', '1', '2', '33');
INSERT INTO `texam_result` VALUES ('8', 'A', '0', '8', '2016-10-30 21:57:05', '1', '2', '34');
INSERT INTO `texam_result` VALUES ('9', null, '0', '9', '2016-10-30 21:57:05', '1', '2', '42');

-- ----------------------------
-- Table structure for texam_signup
-- ----------------------------
DROP TABLE IF EXISTS `texam_signup`;
CREATE TABLE `texam_signup` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '考试报名表',
  `exam_id` int(11) NOT NULL,
  `driver_id` int(11) NOT NULL,
  `signup_time` datetime NOT NULL COMMENT '报名时间',
  `final_score` double DEFAULT NULL COMMENT '考试最终分数',
  `certificate_no` varchar(50) DEFAULT NULL COMMENT '证书号',
  `certificate_photo` varchar(255) DEFAULT NULL COMMENT '证书扫描件',
  `status` smallint(6) NOT NULL COMMENT '考试进程状态，-1：已取消；1：已报名；2：已考试；3：已交卷；4：考试成绩已提交；5：已完成',
  `remark` varchar(255) DEFAULT NULL,
  `take_exam_time` datetime DEFAULT NULL COMMENT '参加考试时间',
  `final_num` int(11) DEFAULT NULL COMMENT '正确答题数',
  `final_sum` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK919A14443A5D4F19` (`exam_id`),
  KEY `FK919A1444C99AF2B9` (`driver_id`),
  CONSTRAINT `FK919A14443A5D4F19` FOREIGN KEY (`exam_id`) REFERENCES `texam` (`id`),
  CONSTRAINT `FK919A1444C99AF2B9` FOREIGN KEY (`driver_id`) REFERENCES `tdriver` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of texam_signup
-- ----------------------------
INSERT INTO `texam_signup` VALUES ('1', '2', '1', '2016-10-30 21:54:04', '7', '1234', 'uploadFile/exam/certificate/photo/1_1477837529796.jpg', '5', null, '2016-10-30 21:57:05', '7', null);
INSERT INTO `texam_signup` VALUES ('2', '3', '1', '2016-10-30 21:56:59', '11', null, null, '4', null, null, null, null);

-- ----------------------------
-- Table structure for tmember
-- ----------------------------
DROP TABLE IF EXISTS `tmember`;
CREATE TABLE `tmember` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `login_name` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `sfz_no` varchar(20) DEFAULT NULL,
  `status` smallint(6) NOT NULL,
  `department_id` int(11) DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `org_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `sfz_no` (`sfz_no`),
  KEY `FKB815640EB24312F9` (`department_id`),
  KEY `FKB815640E53A82C48` (`org_id`),
  KEY `FKB815640EDB4FDBB9` (`role_id`),
  KEY `FKB815640E4D5B3B13` (`create_by`),
  CONSTRAINT `FKB815640E4D5B3B13` FOREIGN KEY (`create_by`) REFERENCES `tmember` (`id`),
  CONSTRAINT `FKB815640E53A82C48` FOREIGN KEY (`org_id`) REFERENCES `torganization` (`id`),
  CONSTRAINT `FKB815640EB24312F9` FOREIGN KEY (`department_id`) REFERENCES `tdepartment` (`id`),
  CONSTRAINT `FKB815640EDB4FDBB9` FOREIGN KEY (`role_id`) REFERENCES `trole` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tmember
-- ----------------------------
INSERT INTO `tmember` VALUES ('1', '2016-08-08 11:11:00', '18800008888', '第一个用户', '123456', '18800008888', '1234128478912489', '1', null, null, '1', null);
INSERT INTO `tmember` VALUES ('3', '2016-08-29 15:54:03', 'hangzhou1', 'hangzhou', '367918', '1283781927', '9813761892367918', '1', null, '1', '9', null);
INSERT INTO `tmember` VALUES ('4', '2016-08-29 16:08:54', 'quzhou', '衢州大管家', '827789', '1283618926876', '736512921731827789', '1', null, '1', '11', null);
INSERT INTO `tmember` VALUES ('5', '2016-08-29 16:11:18', 'yuhang', '余杭大管家', '876128', '123687687', '12763187263876128', '1', null, '1', '12', null);
INSERT INTO `tmember` VALUES ('6', '2016-08-30 20:07:28', 'hebei', '河北哥', '138727', '283928379', '98273982738138727', '1', null, '1', '13', null);
INSERT INTO `tmember` VALUES ('7', '2016-08-30 20:09:44', 'wenzhou', '温州哥', '765765', '125367576', '65765765675765765', '1', null, '1', '15', null);
INSERT INTO `tmember` VALUES ('8', '2016-08-30 20:11:50', 'xihuqu', '西湖哥', '645646', '2138798798', '845641546545645646', '1', null, '1', '16', null);
INSERT INTO `tmember` VALUES ('9', '2016-08-30 20:14:44', 'luchengqu', '鹿城哥', '987909', '84768465', '3213218987987909', '1', null, '1', '17', null);
INSERT INTO `tmember` VALUES ('10', '2016-08-30 20:16:21', 'yuhang1che', '余杭一车老板', '465464', '578787864', '5465456465465464', '1', null, '1', '18', null);
INSERT INTO `tmember` VALUES ('11', '2016-09-12 15:06:42', 'hello1', 'hello1', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('12', '2016-09-12 15:06:42', 'hello2', 'hello2', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('13', '2016-09-12 15:06:42', 'hello3', 'hello3', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('14', '2016-09-12 15:06:42', 'hello4', 'hello4', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('15', '2016-09-12 15:06:42', 'hello5', 'hello5', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('16', '2016-09-12 15:06:42', 'hello6', 'hello6', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('17', '2016-09-12 15:06:42', 'hello7', 'hello7', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('18', '2016-09-12 15:06:42', 'hello8', 'hello8', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('19', '2016-09-12 15:06:42', 'hello9', 'hello9', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('20', '2016-09-12 15:06:42', 'hello10', 'hello10', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('21', '2016-09-12 15:06:42', 'hello11', 'hello11', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('22', '2016-09-12 15:06:42', 'hello12', 'hello12', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('23', '2016-09-12 15:06:42', 'hello13', 'hello13', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('24', '2016-09-12 15:06:42', 'hello14', 'hello14', null, null, null, '1', null, '1', '23', null);
INSERT INTO `tmember` VALUES ('25', '2016-09-12 15:06:42', 'hello15', 'hello15', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('26', '2016-09-12 15:06:42', 'hello16', 'hello16', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('27', '2016-09-12 15:06:42', 'hello17', 'hello17', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('28', '2016-09-12 15:06:42', 'hello18', 'hello18', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('29', '2016-09-12 15:06:42', 'hello19', 'hello19', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('30', '2016-09-12 15:06:42', 'hello20', 'hello20', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('31', '2016-09-12 15:06:42', 'hello21', 'hello21', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('32', '2016-09-12 15:06:42', 'hello22', 'hello22', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('33', '2016-09-12 15:06:42', 'hello23', 'hello23', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('34', '2016-09-12 15:06:42', 'hello24', 'hello24', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('35', '2016-09-12 15:06:42', 'hello25', 'hello25', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('36', '2016-09-12 15:06:42', 'hello26', 'hello26', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('37', '2016-09-12 15:06:42', 'hello27', 'hello27', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('38', '2016-09-12 15:06:42', 'hello28', 'hello28', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('39', '2016-09-12 15:06:42', 'hello29', 'hello29', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('40', '2016-09-12 15:06:42', 'hello30', 'hello30', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('41', '2016-09-12 15:06:42', 'hello31', 'hello31', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('42', '2016-09-12 15:06:42', 'hello32', 'hello32', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('43', '2016-09-12 15:06:42', 'hello33', 'hello33', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('44', '2016-09-12 15:06:42', 'hello34', 'hello34', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('45', '2016-09-12 15:06:42', 'hello35', 'hello35', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('46', '2016-09-12 15:06:42', 'hello36', 'hello36', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('47', '2016-09-12 15:06:42', 'hello37', 'hello37', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('48', '2016-09-12 15:06:42', 'hello38', 'hello38', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('49', '2016-09-12 15:06:42', 'hello39', 'hello39', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('50', '2016-09-12 15:06:42', 'hello40', 'hello40', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('51', '2016-09-12 15:06:42', 'hello41', 'hello41', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('52', '2016-09-12 15:06:42', 'hello42', 'hello42', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('53', '2016-09-12 15:06:42', 'hello43', 'hello43', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('54', '2016-09-12 15:06:42', 'hello44', 'hello44', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('55', '2016-09-12 15:06:42', 'hello45', 'hello45', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('56', '2016-09-12 15:06:42', 'hello46', 'hello46', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('57', '2016-09-12 15:06:42', 'hello47', 'hello47', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('58', '2016-09-12 15:06:42', 'hello48', 'hello48', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('59', '2016-09-12 15:06:42', 'hello49', 'hello49', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('60', '2016-09-12 15:06:42', 'hello50', 'hello50', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('61', '2016-09-12 15:06:42', 'hello51', 'hello51', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('62', '2016-09-12 15:06:42', 'hello52', 'hello52', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('63', '2016-09-12 15:06:42', 'hello53', 'hello53', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('64', '2016-09-12 15:06:42', 'hello54', 'hello54', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('65', '2016-09-12 15:06:42', 'hello55', 'hello55', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('66', '2016-09-12 15:06:42', 'hello56', 'hello56', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('67', '2016-09-12 15:06:42', 'hello57', 'hello57', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('68', '2016-09-12 15:06:42', 'hello58', 'hello58', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('69', '2016-09-12 15:06:42', 'hello59', 'hello59', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('70', '2016-09-12 15:06:42', 'hello60', 'hello60', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('71', '2016-09-12 15:06:42', 'hello61', 'hello61', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('72', '2016-09-12 15:06:42', 'hello62', 'hello62', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('73', '2016-09-12 15:06:42', 'hello63', 'hello63', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('74', '2016-09-12 15:06:42', 'hello64', 'hello64', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('75', '2016-09-12 15:06:42', 'hello65', 'hello65', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('76', '2016-09-12 15:06:42', 'hello66', 'hello66', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('77', '2016-09-12 15:06:42', 'hello67', 'hello67', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('78', '2016-09-12 15:06:42', 'hello68', 'hello68', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('79', '2016-09-12 15:06:42', 'hello69', 'hello69', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('80', '2016-09-12 15:06:42', 'hello70', 'hello70', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('81', '2016-09-12 15:06:42', 'hello71', 'hello71', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('82', '2016-09-12 15:06:42', 'hello72', 'hello72', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('83', '2016-09-12 15:06:42', 'hello73', 'hello73', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('84', '2016-09-12 15:06:42', 'hello74', 'hello74', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('85', '2016-09-12 15:06:42', 'hello75', 'hello75', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('86', '2016-09-12 15:06:42', 'hello76', 'hello76', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('87', '2016-09-12 15:06:42', 'hello77', 'hello77', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('88', '2016-09-12 15:06:42', 'hello78', 'hello78', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('89', '2016-09-12 15:06:42', 'hello79', 'hello79', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('90', '2016-09-12 15:06:42', 'hello80', 'hello80', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('91', '2016-09-12 15:06:42', 'hello81', 'hello81', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('92', '2016-09-12 15:06:42', 'hello82', 'hello82', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('93', '2016-09-12 15:06:42', 'hello83', 'hello83', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('94', '2016-09-12 15:06:42', 'hello84', 'hello84', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('95', '2016-09-12 15:06:42', 'hello85', 'hello85', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('96', '2016-09-12 15:06:42', 'hello86', 'hello86', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('97', '2016-09-12 15:06:42', 'hello87', 'hello87', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('98', '2016-09-12 15:06:42', 'hello88', 'hello88', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('99', '2016-09-12 15:06:42', 'hello89', 'hello89', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('100', '2016-09-12 15:06:42', 'hello90', 'hello90', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('101', '2016-09-12 15:06:42', 'hello91', 'hello91', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('102', '2016-09-12 15:06:42', 'hello92', 'hello92', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('103', '2016-09-12 15:06:42', 'hello93', 'hello93', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('104', '2016-09-12 15:06:42', 'hello94', 'hello94', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('105', '2016-09-12 15:06:42', 'hello95', 'hello95', null, null, null, '1', null, '1', '1', null);
INSERT INTO `tmember` VALUES ('106', '2016-09-12 15:06:42', 'hello96', 'hello96', null, null, null, '1', null, '1', '29', null);
INSERT INTO `tmember` VALUES ('107', '2016-09-27 18:44:24', 'ccc12', 'createone', '923719', 'q93937987', '12387128371923719', '0', null, '1', null, null);
INSERT INTO `tmember` VALUES ('108', '2016-09-28 17:23:41', 'ningbo', '宁波管理员', '731892', '1239712897', '821738971289731892', '1', null, '1', '28', null);

-- ----------------------------
-- Table structure for torg_basic_info
-- ----------------------------
DROP TABLE IF EXISTS `torg_basic_info`;
CREATE TABLE `torg_basic_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `business_license` varchar(50) DEFAULT NULL,
  `business_license_photo` varchar(255) DEFAULT NULL,
  `capital` double DEFAULT NULL,
  `create_date` date NOT NULL,
  `credit_code` varchar(50) DEFAULT NULL,
  `credit_code_photo` varchar(255) DEFAULT NULL,
  `legal_person` varchar(50) NOT NULL,
  `org_code` varchar(50) DEFAULT NULL,
  `org_code_photo` varchar(255) DEFAULT NULL,
  `safe_production_licence_code` varchar(50) DEFAULT NULL,
  `safe_production_licence_photo` varchar(255) DEFAULT NULL,
  `tax_code` varchar(50) DEFAULT NULL,
  `tax_code_photo` varchar(255) DEFAULT NULL,
  `org_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FKF3440BCE53A82C48` (`org_id`),
  CONSTRAINT `FKF3440BCE53A82C48` FOREIGN KEY (`org_id`) REFERENCES `torganization` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of torg_basic_info
-- ----------------------------
INSERT INTO `torg_basic_info` VALUES ('1', '87878787878787', 'uploadFile/orgnization/null_1472559464962.png', '5022222', '2016-08-30', '', '', '吴亦凡', '', '', '', null, '', '', '18');
INSERT INTO `torg_basic_info` VALUES ('2', '12312', 'uploadFile/orgnization/null_1473752954212.jpg', '1231232', '2016-09-13', '', '', '法人代表', '', '', '', null, '', '', '28');

-- ----------------------------
-- Table structure for torganization
-- ----------------------------
DROP TABLE IF EXISTS `torganization`;
CREATE TABLE `torganization` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` longtext,
  `email` varchar(50) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `short_name` varchar(20) DEFAULT NULL,
  `status` smallint(6) NOT NULL,
  `tel` varchar(50) DEFAULT NULL,
  `type` smallint(6) NOT NULL,
  `link_person_id` int(11) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK59A3DFA7AB06F0D9` (`link_person_id`),
  KEY `FK59A3DFA7B2E34C2` (`parent_id`),
  CONSTRAINT `FK59A3DFA7AB06F0D9` FOREIGN KEY (`link_person_id`) REFERENCES `tmember` (`id`),
  CONSTRAINT `FK59A3DFA7B2E34C2` FOREIGN KEY (`parent_id`) REFERENCES `torganization` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of torganization
-- ----------------------------
INSERT INTO `torganization` VALUES ('1', '浙江省的水泥办，管理浙江省所有的水泥办222', 'zj@snb.com', '浙江省散装水泥管理办公室', null, '浙江省水泥办', '1', '18800008888', '0', '1', null);
INSERT INTO `torganization` VALUES ('8', '', '273615256@d.com', '杭州市散装水泥管理办公室', null, '杭州市水泥办', '1', '1276357657', '1', '3', '1');
INSERT INTO `torganization` VALUES ('9', '', '273615256@d.com', '杭州市散装水泥管理办公室(市本级)', null, '杭州市水泥办(市本级)', '0', '1276357657', '2', '3', '8');
INSERT INTO `torganization` VALUES ('10', '这是机构：衢州市散装水泥办', '12312@dhis.com', '衢州市散装水泥管理办公室', null, '衢州市散装办', '1', '12354675467', '1', '4', '1');
INSERT INTO `torganization` VALUES ('11', '这是机构：衢州市散装水泥办', '12312@dhis.com', '衢州市散装水泥管理办公室(市本级)', null, '衢州市散装办(市本级)', '0', '12354675467', '2', '4', '10');
INSERT INTO `torganization` VALUES ('12', '', '5673251765@w.w', '余杭区散装水泥管理办公室', null, '余杭区水泥办', '0', '12876358765', '2', '5', '8');
INSERT INTO `torganization` VALUES ('13', '河北的省级散装办', 'hebei@snb.com', '河北省散装水泥管理办公室', null, '河北散装办', '0', '21378268878', '0', '6', null);
INSERT INTO `torganization` VALUES ('14', '温州的散装办', '768768@snb.com', '温州市散装水泥管理办公室', null, '温州散装办', '0', '2736876876786', '1', '7', '1');
INSERT INTO `torganization` VALUES ('15', '温州的散装办', '768768@snb.com', '温州市散装水泥管理办公室(市本级)', null, '温州散装办(市本级)', '0', '2736876876786', '2', '7', '14');
INSERT INTO `torganization` VALUES ('16', '西湖散装办的描述', 'xihu@snb.com', '西湖区散装水泥管理办公室', null, '西湖散装办', '0', '231231232132', '2', '8', '8');
INSERT INTO `torganization` VALUES ('17', '', '76768@ch.com', '鹿城区散装水泥管理办公室', null, '鹿城水泥办', '1', '27637868766786', '2', '9', '14');
INSERT INTO `torganization` VALUES ('18', '', '545642@d.ck', '余杭区的第一个车企', null, '余杭一车', '0', '545465456464', '3', '10', '12');
INSERT INTO `torganization` VALUES ('23', '', '1@1', '丽水市水泥办', null, '丽水市水泥办', '0', '123123', '1', '24', '1');
INSERT INTO `torganization` VALUES ('24', '', '1@1', '丽水市水泥办(市本级)', null, '丽水市水泥办(市本级)', '0', '123123', '2', null, '23');
INSERT INTO `torganization` VALUES ('28', '在测试一个车企', '1231231@sw', '在测试一个车企', null, '在测试一个车企', '1', '12312312', '3', '108', '9');
INSERT INTO `torganization` VALUES ('29', '天台市水泥办', '182763@233', '天台市水泥办', null, '天台市水泥办', '0', '12837897', '1', '106', '1');
INSERT INTO `torganization` VALUES ('30', '天台市水泥办', '182763@233', '天台市水泥办(市本级)', null, '天台市水泥办(市本级)', '0', '12837897', '2', null, '29');
INSERT INTO `torganization` VALUES ('31', '宁波市水泥办', '7687687@qq', '宁波市水泥办', null, '宁波市水泥办', '1', '1236887126', '1', '108', '1');
INSERT INTO `torganization` VALUES ('32', '宁波市水泥办', '7687687@qq', '宁波市水泥办(市本级)', null, '宁波市水泥办(市本级)', '0', '1236887126', '2', null, '31');

-- ----------------------------
-- Table structure for tpaper
-- ----------------------------
DROP TABLE IF EXISTS `tpaper`;
CREATE TABLE `tpaper` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '试卷表',
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `org_id` int(11) NOT NULL,
  `difficulty` double DEFAULT NULL,
  `type` smallint(6) NOT NULL COMMENT '试卷类型，0：随机生成；1：预设试卷',
  `status` smallint(6) NOT NULL COMMENT '状态，-1：已删除；1：可用',
  `easy_percent` double NOT NULL COMMENT '简单占比',
  `medium_percent` double NOT NULL COMMENT '中等占比',
  `hard_percent` double NOT NULL COMMENT '困难占比',
  `pass_num` int(11) DEFAULT NULL COMMENT '及格题目数',
  `pass_score` int(11) DEFAULT NULL COMMENT '及格分',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FKCC4A399853A82C48` (`org_id`),
  CONSTRAINT `FKCC4A399853A82C48` FOREIGN KEY (`org_id`) REFERENCES `torganization` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tpaper
-- ----------------------------
INSERT INTO `tpaper` VALUES ('1', '模拟考试试卷', '', '2016-10-30 21:52:11', '1', '0', '0', '1', '0.3', '0.4', '0.3', null, '6');
INSERT INTO `tpaper` VALUES ('2', '线上测试考卷', '', '2016-10-30 21:53:47', '1', '0', '1', '1', '0.3', '0.4', '0.3', '5', null);
INSERT INTO `tpaper` VALUES ('3', '线下考卷', '', '2016-10-30 21:56:44', '1', '0', '0', '1', '0.3', '0.4', '0.3', null, '9');

-- ----------------------------
-- Table structure for tpaper_design
-- ----------------------------
DROP TABLE IF EXISTS `tpaper_design`;
CREATE TABLE `tpaper_design` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '试卷设计表',
  `paper_id` int(11) NOT NULL COMMENT '试卷id',
  `question_subject_id` bigint(20) NOT NULL COMMENT '试题知识点',
  `question_type_id` bigint(20) NOT NULL COMMENT '试题题型',
  `num` int(11) NOT NULL COMMENT '题目数',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FKCF000A45E10B685B` (`paper_id`),
  CONSTRAINT `FKCF000A45E10B685B` FOREIGN KEY (`paper_id`) REFERENCES `tpaper` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tpaper_design
-- ----------------------------
INSERT INTO `tpaper_design` VALUES ('1', '1', '645', '642', '5');
INSERT INTO `tpaper_design` VALUES ('2', '1', '646', '642', '4');
INSERT INTO `tpaper_design` VALUES ('3', '2', '645', '642', '3');
INSERT INTO `tpaper_design` VALUES ('4', '2', '646', '642', '2');
INSERT INTO `tpaper_design` VALUES ('5', '2', '645', '643', '4');
INSERT INTO `tpaper_design` VALUES ('6', '3', '645', '642', '7');
INSERT INTO `tpaper_design` VALUES ('7', '3', '646', '642', '6');

-- ----------------------------
-- Table structure for tpaper_list
-- ----------------------------
DROP TABLE IF EXISTS `tpaper_list`;
CREATE TABLE `tpaper_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '试卷详情表',
  `paper_id` int(11) NOT NULL COMMENT '试卷id',
  `question_id` int(11) NOT NULL COMMENT '试题id',
  `sequence` int(11) DEFAULT NULL COMMENT '序号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK9EFF6EE5E10B685B` (`paper_id`),
  KEY `FK9EFF6EE59B9DEE39` (`question_id`),
  CONSTRAINT `FK9EFF6EE59B9DEE39` FOREIGN KEY (`question_id`) REFERENCES `tquestion` (`id`),
  CONSTRAINT `FK9EFF6EE5E10B685B` FOREIGN KEY (`paper_id`) REFERENCES `tpaper` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tpaper_list
-- ----------------------------
INSERT INTO `tpaper_list` VALUES ('1', '2', '14', '1');
INSERT INTO `tpaper_list` VALUES ('2', '2', '13', '2');
INSERT INTO `tpaper_list` VALUES ('3', '2', '9', '3');
INSERT INTO `tpaper_list` VALUES ('4', '2', '11', '4');
INSERT INTO `tpaper_list` VALUES ('5', '2', '10', '5');
INSERT INTO `tpaper_list` VALUES ('6', '2', '27', '6');
INSERT INTO `tpaper_list` VALUES ('7', '2', '33', '7');
INSERT INTO `tpaper_list` VALUES ('8', '2', '34', '8');
INSERT INTO `tpaper_list` VALUES ('9', '2', '42', '9');

-- ----------------------------
-- Table structure for tquestion
-- ----------------------------
DROP TABLE IF EXISTS `tquestion`;
CREATE TABLE `tquestion` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '试题表',
  `content` longtext NOT NULL COMMENT '题干',
  `pic1` varchar(255) DEFAULT NULL COMMENT '图1',
  `pic2` varchar(255) DEFAULT NULL COMMENT '图2',
  `pic3` varchar(255) DEFAULT NULL COMMENT '图3',
  `subject_id` bigint(20) NOT NULL COMMENT '知识点id',
  `type_id` bigint(20) NOT NULL COMMENT '题型id',
  `answer` longtext NOT NULL COMMENT '正确答案',
  `marks` double NOT NULL COMMENT '分值',
  `difficulty` smallint(6) DEFAULT NULL COMMENT '难度，1：易；2：中；3：难',
  `status` smallint(6) NOT NULL COMMENT '状态，-1：已删除；1：可用；',
  `remark` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `operator_id` int(11) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tquestion
-- ----------------------------
INSERT INTO `tquestion` VALUES ('1', '这是一个简单数学选择题', '', '', '', '645', '642', 'A', '1', '1', '1', '', '2016-08-15 17:16:00', null);
INSERT INTO `tquestion` VALUES ('2', '这也是一个简单数学选择题', '', '', '', '645', '642', 'A', '1', '1', '1', '', '2016-08-16 17:16:00', null);
INSERT INTO `tquestion` VALUES ('3', '这还是一个简单数学选择题', '', '', '', '645', '642', 'A', '1', '1', '1', '', '2016-08-17 17:16:00', null);
INSERT INTO `tquestion` VALUES ('4', '这没完没了的简单数学选择题', '', '', '', '645', '642', 'A', '1', '1', '1', '', '2016-08-18 17:16:00', null);
INSERT INTO `tquestion` VALUES ('5', '这是一个简单语文选择题', '', '', '', '646', '642', 'A', '1', '1', '1', '', '2016-08-19 17:16:00', null);
INSERT INTO `tquestion` VALUES ('6', '这也是一个简单语文选择题', '', '', '', '646', '642', 'A', '1', '1', '1', '', '2016-08-20 17:16:00', null);
INSERT INTO `tquestion` VALUES ('7', '这还是一个简单语文选择题', '', '', '', '646', '642', 'A', '1', '1', '1', '', '2016-08-21 17:16:00', null);
INSERT INTO `tquestion` VALUES ('8', '这没完没了的简单语文选择题', '', '', '', '646', '642', 'A', '1', '1', '1', '', '2016-08-22 17:16:00', null);
INSERT INTO `tquestion` VALUES ('9', '这是一个中等数学选择题', '', '', '', '645', '642', 'A', '1', '2', '1', '', '2016-08-23 17:16:00', null);
INSERT INTO `tquestion` VALUES ('10', '这也是一个中等数学选择题', '', '', '', '645', '642', 'A', '1', '2', '1', '', '2016-08-24 17:16:00', null);
INSERT INTO `tquestion` VALUES ('11', '这还是一个中等数学选择题', '', '', '', '645', '642', 'A', '1', '2', '1', '', '2016-08-25 17:16:00', null);
INSERT INTO `tquestion` VALUES ('12', '这没完没了的中等数学选择题', '', '', '', '645', '642', 'A', '1', '2', '1', '', '2016-08-26 17:16:00', null);
INSERT INTO `tquestion` VALUES ('13', '这是一个中等语文选择题', '', '', '', '646', '642', 'A', '1', '2', '1', '', '2016-08-27 17:16:00', null);
INSERT INTO `tquestion` VALUES ('14', '这也是一个中等语文选择题', '', '', '', '646', '642', 'A', '1', '2', '1', '', '2016-08-28 17:16:00', null);
INSERT INTO `tquestion` VALUES ('15', '这还是一个中等语文选择题', '', '', '', '646', '642', 'A', '1', '2', '1', '', '2016-08-29 17:16:00', null);
INSERT INTO `tquestion` VALUES ('16', '这没完没了的中等语文选择题', '', '', '', '646', '642', 'A', '1', '2', '1', '', '2016-08-30 17:16:00', null);
INSERT INTO `tquestion` VALUES ('17', '这是一个困难数学选择题', '', '', '', '645', '642', 'A', '1', '3', '1', '', '2016-08-31 17:16:00', null);
INSERT INTO `tquestion` VALUES ('18', '这也是一个困难数学选择题', '', '', '', '645', '642', 'A', '1', '3', '1', '', '2016-09-01 17:16:00', null);
INSERT INTO `tquestion` VALUES ('19', '这还是一个困难数学选择题', '', '', '', '645', '642', 'A', '1', '3', '1', '', '2016-09-02 17:16:00', null);
INSERT INTO `tquestion` VALUES ('20', '这没完没了的困难数学选择题', '', '', '', '645', '642', 'A', '1', '3', '1', '', '2016-09-03 17:16:00', null);
INSERT INTO `tquestion` VALUES ('21', '这是一个困难语文选择题', '', '', '', '646', '642', 'A', '1', '3', '1', '', '2016-09-04 17:16:00', null);
INSERT INTO `tquestion` VALUES ('22', '这也是一个困难语文选择题', '', '', '', '646', '642', 'A', '1', '3', '1', '', '2016-09-05 17:16:00', null);
INSERT INTO `tquestion` VALUES ('23', '这还是一个困难语文选择题', '', '', '', '646', '642', 'A', '1', '3', '1', '', '2016-09-06 17:16:00', null);
INSERT INTO `tquestion` VALUES ('24', '这没完没了的困难语文选择题', '', '', '', '646', '642', 'A', '1', '3', '1', '', '2016-09-07 17:16:00', null);
INSERT INTO `tquestion` VALUES ('25', '这是一个简单数学填空题', '', '', '', '645', '643', 'A', '1', '1', '1', '', '2016-09-08 17:16:00', null);
INSERT INTO `tquestion` VALUES ('26', '这也是一个简单数学填空题', '', '', '', '645', '643', 'A', '1', '1', '1', '', '2016-09-09 17:16:00', null);
INSERT INTO `tquestion` VALUES ('27', '这还是一个简单数学填空题', '', '', '', '645', '643', 'A', '1', '1', '1', '', '2016-09-10 17:16:00', null);
INSERT INTO `tquestion` VALUES ('28', '这没完没了的简单数学填空题', '', '', '', '645', '643', 'A', '1', '1', '1', '', '2016-09-11 17:16:00', null);
INSERT INTO `tquestion` VALUES ('29', '这是一个简单语文填空题', '', '', '', '646', '643', 'A', '1', '1', '1', '', '2016-09-12 17:16:00', null);
INSERT INTO `tquestion` VALUES ('30', '这也是一个简单语文填空题', '', '', '', '646', '643', 'A', '1', '1', '1', '', '2016-09-13 17:16:00', null);
INSERT INTO `tquestion` VALUES ('31', '这还是一个简单语文填空题', '', '', '', '646', '643', 'A', '1', '1', '1', '', '2016-09-14 17:16:00', null);
INSERT INTO `tquestion` VALUES ('32', '这没完没了的简单语文填空题', '', '', '', '646', '643', 'A', '1', '1', '1', '', '2016-09-15 17:16:00', null);
INSERT INTO `tquestion` VALUES ('33', '这是一个中等数学填空题', '', '', '', '645', '643', 'A', '1', '2', '1', '', '2016-09-16 17:16:00', null);
INSERT INTO `tquestion` VALUES ('34', '这也是一个中等数学填空题', '', '', '', '645', '643', 'A', '1', '2', '1', '', '2016-09-17 17:16:00', null);
INSERT INTO `tquestion` VALUES ('35', '这还是一个中等数学填空题', '', '', '', '645', '643', 'A', '1', '2', '1', '', '2016-09-18 17:16:00', null);
INSERT INTO `tquestion` VALUES ('36', '这没完没了的中等数学填空题', '', '', '', '645', '643', 'A', '1', '2', '1', '', '2016-09-19 17:16:00', null);
INSERT INTO `tquestion` VALUES ('37', '这是一个中等语文填空题', '', '', '', '646', '643', 'A', '1', '2', '1', '', '2016-09-20 17:16:00', null);
INSERT INTO `tquestion` VALUES ('38', '这也是一个中等语文填空题', '', '', '', '646', '643', 'A', '1', '2', '1', '', '2016-09-21 17:16:00', null);
INSERT INTO `tquestion` VALUES ('39', '这还是一个中等语文填空题', '', '', '', '646', '643', 'A', '1', '2', '1', '', '2016-09-22 17:16:00', null);
INSERT INTO `tquestion` VALUES ('40', '这没完没了的中等语文填空题', '', '', '', '646', '643', 'A', '1', '2', '1', '', '2016-09-23 17:16:00', null);
INSERT INTO `tquestion` VALUES ('41', '这是一个困难数学填空题', '', '', '', '645', '643', 'A', '1', '3', '1', '', '2016-09-24 17:16:00', null);
INSERT INTO `tquestion` VALUES ('42', '这也是一个困难数学填空题', '', '', '', '645', '643', 'A', '1', '3', '1', '', '2016-09-25 17:16:00', null);
INSERT INTO `tquestion` VALUES ('43', '这还是一个困难数学填空题', '', '', '', '645', '643', 'A', '1', '3', '1', '', '2016-09-26 17:16:00', null);
INSERT INTO `tquestion` VALUES ('44', '这没完没了的困难数学填空题', '', '', '', '645', '643', 'A', '1', '3', '1', '', '2016-09-27 17:16:00', null);
INSERT INTO `tquestion` VALUES ('45', '这是一个困难语文填空题', '', '', '', '646', '643', 'A', '1', '3', '1', '', '2016-09-28 17:16:00', null);
INSERT INTO `tquestion` VALUES ('46', '这也是一个困难语文填空题', '', '', '', '646', '643', 'A', '1', '3', '1', '', '2016-09-29 17:16:00', null);
INSERT INTO `tquestion` VALUES ('47', '这还是一个困难语文填空题', '', '', '', '646', '643', 'A', '1', '3', '1', '', '2016-09-30 17:16:00', null);
INSERT INTO `tquestion` VALUES ('48', '这没完没了的困难语文填空题', '', '', '', '646', '643', 'A', '1', '3', '1', '', '2016-10-01 17:16:00', null);
INSERT INTO `tquestion` VALUES ('49', '这是一个简单数学简答题', '', '', '', '645', '642', 'A', '1', '1', '1', '', '2016-10-02 17:16:00', null);
INSERT INTO `tquestion` VALUES ('50', '这也是一个简单数学简答题', '', '', '', '645', '642', 'A', '1', '1', '1', '', '2016-10-03 17:16:00', null);

-- ----------------------------
-- Table structure for tquestion_items
-- ----------------------------
DROP TABLE IF EXISTS `tquestion_items`;
CREATE TABLE `tquestion_items` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '试题选项表',
  `content` varchar(100) NOT NULL COMMENT '选项文字内容',
  `pic` varchar(255) DEFAULT NULL COMMENT '选项图片',
  `sequence` smallint(6) NOT NULL COMMENT '序号',
  `question_id` int(11) NOT NULL COMMENT '试题id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK4709A79B9B9DEE39` (`question_id`),
  CONSTRAINT `FK4709A79B9B9DEE39` FOREIGN KEY (`question_id`) REFERENCES `tquestion` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tquestion_items
-- ----------------------------
INSERT INTO `tquestion_items` VALUES ('1', '第一个选项', '', '1', '1');
INSERT INTO `tquestion_items` VALUES ('2', '第一个选项', '', '1', '2');
INSERT INTO `tquestion_items` VALUES ('3', '第一个选项', '', '1', '3');
INSERT INTO `tquestion_items` VALUES ('4', '第一个选项', '', '1', '4');
INSERT INTO `tquestion_items` VALUES ('5', '第一个选项', '', '1', '5');
INSERT INTO `tquestion_items` VALUES ('6', '第一个选项', '', '1', '6');
INSERT INTO `tquestion_items` VALUES ('7', '第一个选项', '', '1', '7');
INSERT INTO `tquestion_items` VALUES ('8', '第一个选项', '', '1', '8');
INSERT INTO `tquestion_items` VALUES ('9', '第一个选项', '', '1', '9');
INSERT INTO `tquestion_items` VALUES ('10', '第一个选项', '', '1', '10');
INSERT INTO `tquestion_items` VALUES ('11', '第一个选项', '', '1', '11');
INSERT INTO `tquestion_items` VALUES ('12', '第一个选项', '', '1', '12');
INSERT INTO `tquestion_items` VALUES ('13', '第一个选项', '', '1', '13');
INSERT INTO `tquestion_items` VALUES ('14', '第一个选项', '', '1', '14');
INSERT INTO `tquestion_items` VALUES ('15', '第一个选项', '', '1', '15');
INSERT INTO `tquestion_items` VALUES ('16', '第一个选项', '', '1', '16');
INSERT INTO `tquestion_items` VALUES ('18', '第一个选项', '', '1', '18');
INSERT INTO `tquestion_items` VALUES ('19', '第一个选项', '', '1', '19');
INSERT INTO `tquestion_items` VALUES ('20', '第一个选项', '', '1', '20');
INSERT INTO `tquestion_items` VALUES ('21', '第一个选项', '', '1', '21');
INSERT INTO `tquestion_items` VALUES ('22', '第一个选项', '', '1', '22');
INSERT INTO `tquestion_items` VALUES ('23', '第一个选项', '', '1', '23');
INSERT INTO `tquestion_items` VALUES ('24', '第一个选项', '', '1', '24');
INSERT INTO `tquestion_items` VALUES ('25', '第一个选项', '', '1', '25');
INSERT INTO `tquestion_items` VALUES ('26', '第一个选项', '', '1', '26');
INSERT INTO `tquestion_items` VALUES ('27', '第一个选项', '', '1', '27');
INSERT INTO `tquestion_items` VALUES ('28', '第一个选项', '', '1', '28');
INSERT INTO `tquestion_items` VALUES ('29', '第一个选项', '', '1', '29');
INSERT INTO `tquestion_items` VALUES ('30', '第一个选项', '', '1', '30');
INSERT INTO `tquestion_items` VALUES ('31', '第一个选项', '', '1', '31');
INSERT INTO `tquestion_items` VALUES ('32', '第一个选项', '', '1', '32');
INSERT INTO `tquestion_items` VALUES ('33', '第一个选项', '', '1', '33');
INSERT INTO `tquestion_items` VALUES ('34', '第一个选项', '', '1', '34');
INSERT INTO `tquestion_items` VALUES ('35', '第一个选项', '', '1', '35');
INSERT INTO `tquestion_items` VALUES ('36', '第一个选项', '', '1', '36');
INSERT INTO `tquestion_items` VALUES ('37', '第一个选项', '', '1', '37');
INSERT INTO `tquestion_items` VALUES ('38', '第一个选项', '', '1', '38');
INSERT INTO `tquestion_items` VALUES ('39', '第一个选项', '', '1', '39');
INSERT INTO `tquestion_items` VALUES ('40', '第一个选项', '', '1', '40');
INSERT INTO `tquestion_items` VALUES ('41', '第一个选项', '', '1', '41');
INSERT INTO `tquestion_items` VALUES ('42', '第一个选项', '', '1', '42');
INSERT INTO `tquestion_items` VALUES ('43', '第一个选项', '', '1', '43');
INSERT INTO `tquestion_items` VALUES ('44', '第一个选项', '', '1', '44');
INSERT INTO `tquestion_items` VALUES ('45', '第一个选项', '', '1', '45');
INSERT INTO `tquestion_items` VALUES ('46', '第一个选项', '', '1', '46');
INSERT INTO `tquestion_items` VALUES ('47', '第一个选项', '', '1', '47');
INSERT INTO `tquestion_items` VALUES ('48', '第一个选项', '', '1', '48');
INSERT INTO `tquestion_items` VALUES ('49', '第一个选项', '', '1', '49');
INSERT INTO `tquestion_items` VALUES ('50', '第一个选项', '', '1', '50');
INSERT INTO `tquestion_items` VALUES ('51', '第二个选项', '', '2', '1');
INSERT INTO `tquestion_items` VALUES ('52', '第二个选项', '', '2', '2');
INSERT INTO `tquestion_items` VALUES ('53', '第二个选项', '', '2', '3');
INSERT INTO `tquestion_items` VALUES ('54', '第二个选项', '', '2', '4');
INSERT INTO `tquestion_items` VALUES ('55', '第二个选项', '', '2', '5');
INSERT INTO `tquestion_items` VALUES ('56', '第二个选项', '', '2', '6');
INSERT INTO `tquestion_items` VALUES ('57', '第二个选项', '', '2', '7');
INSERT INTO `tquestion_items` VALUES ('58', '第二个选项', '', '2', '8');
INSERT INTO `tquestion_items` VALUES ('59', '第二个选项', '', '2', '9');
INSERT INTO `tquestion_items` VALUES ('60', '第二个选项', '', '2', '10');
INSERT INTO `tquestion_items` VALUES ('61', '第二个选项', '', '2', '11');
INSERT INTO `tquestion_items` VALUES ('62', '第二个选项', '', '2', '12');
INSERT INTO `tquestion_items` VALUES ('63', '第二个选项', '', '2', '13');
INSERT INTO `tquestion_items` VALUES ('64', '第二个选项', '', '2', '14');
INSERT INTO `tquestion_items` VALUES ('65', '第二个选项', '', '2', '15');
INSERT INTO `tquestion_items` VALUES ('66', '第二个选项', '', '2', '16');
INSERT INTO `tquestion_items` VALUES ('68', '第二个选项', '', '2', '18');
INSERT INTO `tquestion_items` VALUES ('69', '第二个选项', '', '2', '19');
INSERT INTO `tquestion_items` VALUES ('70', '第二个选项', '', '2', '20');
INSERT INTO `tquestion_items` VALUES ('71', '第二个选项', '', '2', '21');
INSERT INTO `tquestion_items` VALUES ('72', '第二个选项', '', '2', '22');
INSERT INTO `tquestion_items` VALUES ('73', '第二个选项', '', '2', '23');
INSERT INTO `tquestion_items` VALUES ('74', '第二个选项', '', '2', '24');
INSERT INTO `tquestion_items` VALUES ('75', '第二个选项', '', '2', '25');
INSERT INTO `tquestion_items` VALUES ('76', '第二个选项', '', '2', '26');
INSERT INTO `tquestion_items` VALUES ('77', '第二个选项', '', '2', '27');
INSERT INTO `tquestion_items` VALUES ('78', '第二个选项', '', '2', '28');
INSERT INTO `tquestion_items` VALUES ('79', '第二个选项', '', '2', '29');
INSERT INTO `tquestion_items` VALUES ('80', '第二个选项', '', '2', '30');
INSERT INTO `tquestion_items` VALUES ('81', '第二个选项', '', '2', '31');
INSERT INTO `tquestion_items` VALUES ('82', '第二个选项', '', '2', '32');
INSERT INTO `tquestion_items` VALUES ('83', '第二个选项', '', '2', '33');
INSERT INTO `tquestion_items` VALUES ('84', '第二个选项', '', '2', '34');
INSERT INTO `tquestion_items` VALUES ('85', '第二个选项', '', '2', '35');
INSERT INTO `tquestion_items` VALUES ('86', '第二个选项', '', '2', '36');
INSERT INTO `tquestion_items` VALUES ('87', '第二个选项', '', '2', '37');
INSERT INTO `tquestion_items` VALUES ('88', '第二个选项', '', '2', '38');
INSERT INTO `tquestion_items` VALUES ('89', '第二个选项', '', '2', '39');
INSERT INTO `tquestion_items` VALUES ('90', '第二个选项', '', '2', '40');
INSERT INTO `tquestion_items` VALUES ('91', '第二个选项', '', '2', '41');
INSERT INTO `tquestion_items` VALUES ('92', '第二个选项', '', '2', '42');
INSERT INTO `tquestion_items` VALUES ('93', '第二个选项', '', '2', '43');
INSERT INTO `tquestion_items` VALUES ('94', '第二个选项', '', '2', '44');
INSERT INTO `tquestion_items` VALUES ('95', '第二个选项', '', '2', '45');
INSERT INTO `tquestion_items` VALUES ('96', '第二个选项', '', '2', '46');
INSERT INTO `tquestion_items` VALUES ('97', '第二个选项', '', '2', '47');
INSERT INTO `tquestion_items` VALUES ('98', '第二个选项', '', '2', '48');
INSERT INTO `tquestion_items` VALUES ('99', '第二个选项', '', '2', '49');
INSERT INTO `tquestion_items` VALUES ('100', '第二个选项', '', '2', '50');

-- ----------------------------
-- Table structure for trole
-- ----------------------------
DROP TABLE IF EXISTS `trole`;
CREATE TABLE `trole` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `ctrl_role_id` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `type` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of trole
-- ----------------------------

-- ----------------------------
-- Table structure for trole_tauth_group
-- ----------------------------
DROP TABLE IF EXISTS `trole_tauth_group`;
CREATE TABLE `trole_tauth_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `auth_group_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK3688CEC75F3912EA` (`auth_group_id`),
  KEY `FK3688CEC7DB4FDBB9` (`role_id`),
  CONSTRAINT `FK3688CEC75F3912EA` FOREIGN KEY (`auth_group_id`) REFERENCES `tauth_group` (`id`),
  CONSTRAINT `FK3688CEC7DB4FDBB9` FOREIGN KEY (`role_id`) REFERENCES `trole` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of trole_tauth_group
-- ----------------------------

-- ----------------------------
-- Table structure for tspecial_vehicle
-- ----------------------------
DROP TABLE IF EXISTS `tspecial_vehicle`;
CREATE TABLE `tspecial_vehicle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `buy_time` date DEFAULT NULL,
  `max_weight` double DEFAULT NULL,
  `production_time` date DEFAULT NULL,
  `registration_number` varchar(255) DEFAULT NULL,
  `self_weight` double DEFAULT NULL,
  `specification` varchar(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `org_id` tinyblob,
  `type` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tspecial_vehicle
-- ----------------------------

-- ----------------------------
-- Table structure for ttruck
-- ----------------------------
DROP TABLE IF EXISTS `ttruck`;
CREATE TABLE `ttruck` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `car_no` varchar(10) DEFAULT NULL,
  `status` smallint(6) NOT NULL,
  `truck_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ttruck
-- ----------------------------

-- 题目选项content栏改为可为空，为了保存图片选项 -sdy -2016年11月4日20:00:11
ALTER TABLE tquestion_items MODIFY content varchar(100) NULL;

-- 新增驾驶员星级评定表 -sdy -2016年11月14日19:14:50
DROP TABLE IF EXISTS `tdriver_star`;
CREATE TABLE `tdriver_star` (
  `id` int(11) NOT NULL COMMENT '驾驶员星级评定表',
  `driver_id` int(11) NOT NULL,
  `new_star` int(11) NOT NULL COMMENT '评定后星级',
  `generate_time` datetime DEFAULT NULL,
  `confirm_time` datetime NOT NULL COMMENT '评定时间',
  `confirmer_id` int(11) NOT NULL COMMENT '评定人',
  `rule_ids` varchar(50) DEFAULT NULL COMMENT '当前星级确定规则编号(可能有多个规则)',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注（评定依据）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 新增星级表 -sdy -2016年11月14日19:15:53
DROP TABLE IF EXISTS `tstar`;
CREATE TABLE `tstar` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `rank` int(11) NOT NULL,
  `status` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 新增星际规则表 -sdy -2016年11月14日19:17:23
DROP TABLE IF EXISTS `tstar_rule`;
CREATE TABLE `tstar_rule` (
  `id` int(11) NOT NULL COMMENT '星级规则',
  `from_star_id` int(11) DEFAULT NULL COMMENT '起始星级',
  `to_start_id` int(11) NOT NULL COMMENT '目标星级',
  `item_id` bigint(20) DEFAULT NULL COMMENT '规则观测点（数据字典中）',
  `value` int(11) DEFAULT NULL COMMENT '规则阈值（都用整型量）',
  `comparator` varchar(255) DEFAULT NULL COMMENT '规则比较符（>,<,>=,<=）',
  `remark` varchar(500) NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 新增车辆保费信息表 -sdy -2016年11月14日19:19:00
DROP TABLE IF EXISTS `tvehicle_insurance`;
CREATE TABLE `tvehicle_insurance` (
  `id` int(11) NOT NULL COMMENT '车辆保费信息表',
  `car_no` varchar(50) NOT NULL COMMENT '车牌号',
  `insurance_company` varchar(100) DEFAULT NULL COMMENT '保险公司名称',
  `insurance_fee` double NOT NULL COMMENT '保险费',
  `year` int(11) NOT NULL COMMENT '保险年度',
  `start_time` datetime NOT NULL COMMENT '保险生效开始时间',
  `end_time` datetime NOT NULL COMMENT '保险生效截止时间',
  `input_time` datetime NOT NULL COMMENT '保险记录导入时间',
  `operator_id` int(11) NOT NULL COMMENT '保险记录导入人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

-- 驾驶员表新增star_id、start_time字段 -sdy -2016年11月14日19:20:54
ALTER TABLE tdriver ADD star_id int(11) COMMENT '驾驶员星级';
ALTER TABLE tdriver ADD CONSTRAINT `tdriver_ibfk_1` FOREIGN KEY (`star_id`) REFERENCES `tstar` (`id`);
ALTER TABLE tdriver ADD star_time datetime COMMENT '驾驶员星级评定时间';

-- tdriver_insurance_compensation表新增死亡人数、事故事件、保险录入是加你车企指定事故责任人时间、录入人、事故责任认定人字段   -sdy -2016年11月14日19:39:51
ALTER TABLE tdriver_insurance_compensation ADD dead_num int(11) COMMENT '死亡人数';
ALTER TABLE tdriver_insurance_compensation ADD occure_time datetime COMMENT '事故时间';
ALTER TABLE tdriver_insurance_compensation ADD input_time datetime COMMENT '保险录入时间';
ALTER TABLE tdriver_insurance_compensation ADD confirm_time datetime COMMENT '车企指定事故责任人时间';
ALTER TABLE tdriver_insurance_compensation ADD inputter_id int(11) COMMENT '录入人';
ALTER TABLE tdriver_insurance_compensation ADD CONSTRAINT `tdriver_insurance_compensation_ibfk_2` FOREIGN KEY (`inputter_id`) REFERENCES `tmember` (`id`);
ALTER TABLE tdriver_insurance_compensation ADD confirmer_id int(11) COMMENT '事故责任认定人';
ALTER TABLE tdriver_insurance_compensation ADD CONSTRAINT `tdriver_insurance_compensation_ibfk_3` FOREIGN KEY (`confirmer_id`) REFERENCES `tmember` (`id`);

-- 添加星级评定计划表  -sdy -2016年11月15日10:37:12
DROP TABLE IF EXISTS `tdriver_star_plan`;
CREATE TABLE `tdriver_star_plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '星级管理任务安排表',
  `name` varchar(50) NOT NULL COMMENT '任务名称',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `year` int(11) NOT NULL COMMENT '统计年度',
  `company_start_time` datetime NOT NULL COMMENT '车企工作时间段：开始时间',
  `company_end_time` datetime NOT NULL COMMENT '车企工作时间段：结束时间',
  `country_start_time` datetime DEFAULT NULL COMMENT '县区散装办工作时间段：开始时间',
  `country_end_time` datetime DEFAULT NULL COMMENT '县区散装办工作时间段：结束时间',
  `city_start_time` datetime DEFAULT NULL COMMENT '市散装办工作时间段：开始时间',
  `city_end_time` datetime DEFAULT NULL COMMENT '市散装办工作时间段：结束时间',
  `province_start_time` datetime DEFAULT NULL COMMENT '省散装办工作时间段：开始时间',
  `province_end_time` datetime DEFAULT NULL COMMENT '省散装办工作时间段：结束时间',
  `adjust_start_time` datetime DEFAULT NULL COMMENT '结果调整时间段：开始时间',
  `adjust_end_time` datetime DEFAULT NULL COMMENT '结果调整时间段：结束时间',
  `status` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 添加星级确认表  -sdy -2016年11月15日10:37:40
DROP TABLE IF EXISTS `tdriver_star_confirm`;
CREATE TABLE `tdriver_star_confirm` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '星级评定的四级审核信息表',
  `plan_id` int(11) NOT NULL COMMENT '评定任务id',
  `submit_org_id` int(11) NOT NULL COMMENT '提交评定结果的车企或散装办',
  `submit_time` datetime NOT NULL COMMENT '提交审核的时间',
  `submitter_id` int(11) NOT NULL COMMENT '提交审核的人',
  `confirm_org_id` int(11) NOT NULL COMMENT '审核所属车企/散装办提交的评定结果的车企或散装办',
  `confirm_result` tinyint(4) NOT NULL COMMENT '审核结果：0->退回；1->确认',
  `confirm_time` datetime NOT NULL COMMENT '确认时间',
  `confirmor_id` int(11) NOT NULL COMMENT '确认人id',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注（可以考虑用来增量记录退回及审核日志）',
  PRIMARY KEY (`id`),
  KEY `plan_id` (`plan_id`),
  CONSTRAINT `tdriver_star_confirm_ibfk_1` FOREIGN KEY (`plan_id`) REFERENCES `tdriver_star_plan` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 添加有责任的保险理赔信息表  -sdy -2016年11月15日10:38:23
DROP TABLE IF EXISTS `tinsurance_claim`;
CREATE TABLE `tinsurance_claim` (
  `id` int(11) NOT NULL COMMENT '有责任的保险理赔信息表',
  `vehicle_sn` varchar(50) NOT NULL COMMENT '车牌号',
  `driver_sfz` varchar(20) DEFAULT NULL COMMENT '驾驶员身份证号',
  `compensate_amount` double NOT NULL COMMENT '理赔金额',
  `insurance_accident_no` varchar(50) DEFAULT NULL COMMENT '保险公司的保险事故编号',
  `traffice_accident_no` varchar(50) DEFAULT NULL COMMENT '交通事故编号（交警的处理编号）',
  `occur_time` datetime DEFAULT NULL COMMENT '事故发生时间',
  `input_time` datetime DEFAULT NULL COMMENT '数据录入时间',
  `operator` int(11) NOT NULL COMMENT '数据录入人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 添加交通数据信息表  -sdy -2016年11月15日10:39:26
DROP TABLE IF EXISTS `ttraffic_accident`;
CREATE TABLE `ttraffic_accident` (
  `id` int(11) NOT NULL COMMENT '交通数据信息表',
  `vehicle_sn` varchar(50) NOT NULL COMMENT '车牌号',
  `driver_sfz` varchar(20) DEFAULT NULL COMMENT '驾驶员身份证号',
  `traffice_accident_no` varchar(50) DEFAULT NULL COMMENT '交通事故编号（交警系统编号）',
  `injured_num` int(11) NOT NULL COMMENT '受伤人数',
  `dead_num` int(11) NOT NULL COMMENT '死亡人数',
  `type` bigint(20) NOT NULL COMMENT '事故类型（来自数据字典）',
  `occur_time` datetime NOT NULL COMMENT '发生时间',
  `input_time` datetime NOT NULL COMMENT '录入时间',
  `operator_id` datetime NOT NULL COMMENT '录入人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 添加车辆保费信息表  -sdy -2016年11月15日10:40:19
DROP TABLE IF EXISTS `tvehicle_insurance`;
CREATE TABLE `tvehicle_insurance` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '车辆保费信息表',
  `car_no` varchar(50) NOT NULL COMMENT '车牌号',
  `insurance_company` varchar(100) DEFAULT NULL COMMENT '保险公司名称',
  `insurance_fee` double NOT NULL COMMENT '保险费',
  `year` int(11) NOT NULL COMMENT '保险年度',
  `start_time` datetime NOT NULL COMMENT '保险生效开始时间',
  `end_time` datetime NOT NULL COMMENT '保险生效截止时间',
  `input_time` datetime NOT NULL COMMENT '保险记录导入时间',
  `operator_id` int(11) NOT NULL COMMENT '保险记录导入人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--  添加优秀车队长、优秀驾驶员评比安排表  -sdy -2016年11月15日10:43:45
DROP TABLE IF EXISTS `tcompetition_plan`;
CREATE TABLE `tcompetition_plan` (
  `id` int(11) NOT NULL COMMENT '优秀车队长、优秀驾驶员评比安排表',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `year` int(11) NOT NULL COMMENT '评比年度',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `select_start_time` datetime NOT NULL COMMENT '省散装办初选开始时间',
  `select_end_time` datetime NOT NULL COMMENT '省散装办初选结束时间',
  `match_start_time` datetime NOT NULL COMMENT '车企确认具体人员名单开始时间',
  `match_end_time` datetime NOT NULL COMMENT '车企确认具体人员名单结束时间',
  `status` tinyint(4) NOT NULL COMMENT '状态：-1->无效（删除）；0->进行中；1->已完成',
  `operator_id` int(11) NOT NULL COMMENT '操作人员id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- 将tstar_rule表的from_star_id以及to_star_id字段关联tstar表  -sdy -2016年11月16日19:15:52
ALTER TABLE tstar_rule ADD CONSTRAINT `tstar_rule_ibfk_1` FOREIGN KEY (`from_star_id`) REFERENCES `tstar` (`id`);
ALTER TABLE tstar_rule CHANGE to_start_id to_star_id int(11);
ALTER TABLE tstar_rule ADD CONSTRAINT `tstar_rule_ibfk_2` FOREIGN KEY (`to_star_id`) REFERENCES `tstar` (`id`);

-- tdriver_star表确认时间、确认人改为可为空，确认人关联tmember表  -sdy -2016年11月16日19:16:00
 ALTER TABLE tdriver_star CHANGE COLUMN `confirm_time` `confirme_time` datetime NULL COMMENT '确认时间';
 ALTER TABLE tdriver_star CHANGE COLUMN `confirmer_id` `confirmer_id` INT(11) NULL COMMENT '确认人' ;
 ALTER TABLE tdriver_star ADD CONSTRAINT `tdriver_star_ibfk_2` FOREIGN KEY (`confirmer_id`) REFERENCES `tmember` (`id`);
 
 -- 修改tdriver_star_confirm表相关字段  -sdy -2016年11月16日19:16:06
ALTER TABLE tdriver_star_confirm CHANGE COLUMN `submit_org_id` `submit_org_id` INT(11) NULL COMMENT '提交评定结果的车企或散装办';
ALTER TABLE tdriver_star_confirm CHANGE COLUMN `submit_time` `submit_time` datetime NULL COMMENT '提交审核的时间';
ALTER TABLE tdriver_star_confirm CHANGE COLUMN `submitter_id` `submitter_id` INT(11) NULL COMMENT '提交审核的人';
ALTER TABLE tdriver_star_confirm CHANGE COLUMN `confirm_org_id` `confirm_org_id` INT(11) NULL COMMENT '审核所属车企/散装办提交的评定结果的车企或散装办';
ALTER TABLE tdriver_star_confirm CHANGE COLUMN `confirm_result` `confirm_result` INT(11) NULL COMMENT '审核结果：0->退回；1->确认';
ALTER TABLE tdriver_star_confirm CHANGE COLUMN `confirm_time` `confirm_time` datetime NULL COMMENT '确认时间';
ALTER TABLE tdriver_star_confirm CHANGE COLUMN `confirmor_id` `confirmer_id` INT(11) NULL COMMENT '确认人id';
ALTER TABLE tdriver_star_confirm ADD CONSTRAINT `tdriver_star_confirm_ibfk_2` FOREIGN KEY (`confirmer_id`) REFERENCES `tmember` (`id`);

-- tdriver_star表new_star关联tstar表 driver_id关联tdriver表  -sdy -2016年11月16日19:16:17
ALTER TABLE tdriver_star ADD CONSTRAINT `tdriver_star_ibfk_3` FOREIGN KEY (`new_star`) REFERENCES `tstar` (`id`);
ALTER TABLE tdriver_star ADD CONSTRAINT `tdriver_star_ibfk_4` FOREIGN KEY (`driver_id`) REFERENCES `tdriver` (`id`);

-- 添加星级等级 -sdy -2016年11月16日20:06:02
INSERT INTO `tstar` VALUES ('1', '培训员', '培训、考试通过省级为驾驶员', '-1', '1');
INSERT INTO `tstar` VALUES ('2', '驾驶员', null, '0', '1');
INSERT INTO `tstar` VALUES ('3', '一星级驾驶员', null, '1', '1');
INSERT INTO `tstar` VALUES ('4', '二星级驾驶员', null, '2', '1');
INSERT INTO `tstar` VALUES ('5', '三星级驾驶员', null, '3', '1');
INSERT INTO `tstar` VALUES ('6', '四星级驾驶员', null, '4', '1');
INSERT INTO `tstar` VALUES ('7', '五星级驾驶员', null, '5', '1');

-- 添加星级评定类别  -sdy  -2016年11月16日20:06:27
INSERT INTO `data_dictionary` VALUES ('653', '', null, '2016-11-16 17:43:34', null, null, 'star_rule_item', '', '0', '6530000', '星级评比规则类型', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('654', '', null, '2016-11-16 17:44:35', '653', '653', null, 'star_rule.rate', '1', '6530100', '当年理赔率', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('655', '', null, '2016-11-16 17:45:05', '653', '653', null, 'star_rule.rateAvg', '1', '6530100', '连续两年理赔率', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('656', '', null, '2016-11-16 17:45:27', '653', '653', null, 'star_rule.injureCount', '1', '6530100', '有责任伤人事故次数', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('657', '', null, '2016-11-16 17:45:43', '653', '653', null, 'star_rule.injure2Count', '1', '6530100', '有责任伤2人以上事故次数', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('658', '', null, '2016-11-16 17:46:09', '653', '653', null, 'star_rule.deadCount', '1', '6530100', '有责任死亡事故次数', null, '', null, '', '1');

-- 添加星级评定规则  -sdy  -2016年11月16日20:06:54
INSERT INTO `tstar_rule` VALUES ('1', '2', '3', '654', '10', '<', '驾驶员一个完整年度内驾驶专用车辆全年保险赔付为10%以下的驾驶员升级为一星级驾驶员');
INSERT INTO `tstar_rule` VALUES ('2', '2', '1', '654', '50', '>', '驾驶员全年保险赔付超过50%，取消星级，重新培训，重新考试，重新发放培训合格证。');
INSERT INTO `tstar_rule` VALUES ('3', '2', '1', '658', '1', '>=', '驾驶员出现1次有责任死亡事故，取消星级，重新培训，重新考试，重新发放培训合格证。');
INSERT INTO `tstar_rule` VALUES ('4', '2', '1', '657', '1', '>=', '驾驶员出现一次2人受伤有责任安全事故，取消星级，重新培训，重新考试，重新发放培训合格证。');
INSERT INTO `tstar_rule` VALUES ('5', '3', '4', '654', '0', '==', '一星级驾驶员驾驶专用车辆1年保险赔付零，升级为二星级驾驶员。');
INSERT INTO `tstar_rule` VALUES ('6', '3', '4', '655', '10', '<', '一星级驾驶员驾驶专用车辆连续2年保险赔付率在10%以下的，升级为二星级驾驶员。');
INSERT INTO `tstar_rule` VALUES ('7', '3', '2', '654', '20', '>', '一星级驾驶员驾驶专用车辆出现年度保险赔付为20%以上的，降为驾驶员');
INSERT INTO `tstar_rule` VALUES ('8', '3', '2', '656', '1', '>=', '一星级驾驶员驾驶专用车辆出现一次有责任的人员受伤事故，降为驾驶员');
INSERT INTO `tstar_rule` VALUES ('9', '3', '1', '658', '1', '>=', '一星级驾驶员驾驶专用车辆出现1次有责任死亡事故，取消星级，重新培训，重新考试，重新发放培训合格证。');
INSERT INTO `tstar_rule` VALUES ('10', '3', '1', '657', '1', '>=', '一星级驾驶员驾驶专用车辆出现一次2人受伤有责任安全事故，取消星级，重新培训，重新考试，重新发放培训合格证。');
INSERT INTO `tstar_rule` VALUES ('11', '4', '5', '654', '0', '==', '二星级驾驶员驾驶专用车辆1年保险赔付零，升级为三星级驾驶员。');
INSERT INTO `tstar_rule` VALUES ('12', '4', '5', '655', '10', '<', '二星级驾驶员连续2年保险赔付率在10%以下的升级为三星级驾驶员。');
INSERT INTO `tstar_rule` VALUES ('13', '4', '3', '654', '20', '>', '二星级驾驶员驾驶专用车辆出现1次年度保险赔付超过20%的，降为一星级驾驶员。');
INSERT INTO `tstar_rule` VALUES ('14', '4', '3', '656', '1', '>=', '二星级驾驶员驾驶专用车辆出现1次有责任的人员受伤事故，降为一星级驾驶员。');
INSERT INTO `tstar_rule` VALUES ('15', '4', '2', '657', '1', '>=', '二星级驾驶员驾驶专用车辆出现一次2人受伤有责任安全事故，降为驾驶员。');
INSERT INTO `tstar_rule` VALUES ('16', '4', '1', '658', '1', '>=', '二星级驾驶员驾驶专用车辆出现1次有责任死亡事故，取消星级，重新培训，重新考试，重新发放培训合格证。');
INSERT INTO `tstar_rule` VALUES ('17', '5', '6', '654', '0', '==', '三星级驾驶员驾驶专用车辆1年保险赔付零，升级为四星级驾驶员。');
INSERT INTO `tstar_rule` VALUES ('18', '5', '6', '655', '10', '<', '三星级驾驶员连续2年保险赔付率在10%以下的升级为四星级驾驶员。');
INSERT INTO `tstar_rule` VALUES ('19', '5', '4', '654', '20', '>', '三星级驾驶员驾驶专用车辆出现1次年度保险赔付超过20%的，降为二星级驾驶员。');
INSERT INTO `tstar_rule` VALUES ('20', '5', '4', '656', '1', '>=', '三星级驾驶员驾驶专用车辆出现1次有责任的人员受伤事故，降为二星级驾驶员。');
INSERT INTO `tstar_rule` VALUES ('21', '5', '3', '657', '1', '>=', '三星级驾驶员驾驶专用车辆出现一次2人受伤有责任安全事故，降为一星级驾驶员。');
INSERT INTO `tstar_rule` VALUES ('22', '5', '1', '658', '1', '>=', '三星级驾驶员驾驶专用车辆出现1次有责任死亡事故，取消星级，重新培训，重新考试，重新发放培训合格证。');
INSERT INTO `tstar_rule` VALUES ('23', '6', '7', '654', '0', '==', '四星级驾驶员驾驶专用车辆1年保险赔付零，升级为五星级驾驶员。');
INSERT INTO `tstar_rule` VALUES ('24', '6', '7', '655', '10', '<', '四星级驾驶员连续2年保险赔付率在10%以下的升级为五星级驾驶员。');
INSERT INTO `tstar_rule` VALUES ('25', '6', '5', '654', '20', '>', '四星级驾驶员驾驶专用车辆出现1次年度保险赔付超过20%的，降为三星级驾驶员。');
INSERT INTO `tstar_rule` VALUES ('26', '6', '5', '656', '1', '>=', '四星级驾驶员驾驶专用车辆出现1次有责任的人员受伤事故，降为三星级驾驶员。');
INSERT INTO `tstar_rule` VALUES ('27', '6', '4', '657', '1', '>=', '四星级驾驶员驾驶专用车辆出现一次2人受伤有责任安全事故，降为二星级驾驶员。');
INSERT INTO `tstar_rule` VALUES ('28', '6', '1', '658', '1', '>=', '四星级驾驶员驾驶专用车辆出现1次有责任死亡事故，取消星级，重新培训，重新考试，重新发放培训合格证。');
INSERT INTO `tstar_rule` VALUES ('29', '7', '6', '654', '20', '>', '五星级驾驶员驾驶专用车辆出现1次年度保险赔付超过20%的，降为四星级驾驶员。');
INSERT INTO `tstar_rule` VALUES ('30', '7', '6', '656', '1', '>=', '五星级驾驶员驾驶专用车辆出现1次有责任的人员受伤事故，降为四星级驾驶员。');
INSERT INTO `tstar_rule` VALUES ('31', '7', '5', '657', '1', '>=', '五星级驾驶员驾驶专用车辆出现一次2人受伤有责任安全事故，降为三星级驾驶员。');
INSERT INTO `tstar_rule` VALUES ('32', '7', '1', '658', '1', '>=', '五星级驾驶员驾驶专用车辆出现1次有责任死亡事故，取消星级，重新培训，重新考试，重新发放培训合格证。');

-- 专用车辆表 车牌号字段名称改为car_no -sdy  -2016年11月24日16:16:02
ALTER TABLE `tspecial_vehicle` CHANGE COLUMN `registration_number` `car_no` VARCHAR(50) NOT NULL COMMENT '车牌号' ;

-- 试题表添加是否必选字段 -sdy -2016年12月4日22:44:10
ALTER TABLE tquestion ADD required TINYINT NOT NULL COMMENT '是否必选，1：必选，0：不必选';

-- 新建培训表 -sdy  -2016年12月6日22:51:47
DROP TABLE IF EXISTS `ttraining`;
CREATE TABLE `ttraining` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '培训表',
  `name` varchar(50) NOT NULL COMMENT '培训名称',
  `description` varchar(1000) DEFAULT NULL COMMENT '培训描述',
  `start_time` datetime NOT NULL COMMENT '培训开始时间',
  `end_time` datetime NOT NULL COMMENT '培训结束时间',
  `org_id` int(11) NOT NULL COMMENT '发起组织',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `exam_id` int(11) DEFAULT NULL COMMENT '关联考试id，培训关联考试则不为空',
  `status` tinyint(255) DEFAULT NULL COMMENT '培训状态',
  PRIMARY KEY (`id`),
  KEY `org_id` (`org_id`),
  KEY `exam_id` (`exam_id`),
  CONSTRAINT `ttraining_ibfk_1` FOREIGN KEY (`org_id`) REFERENCES `torganization` (`id`),
  CONSTRAINT `ttraining_ibfk_2` FOREIGN KEY (`exam_id`) REFERENCES `texam` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- 新建驾驶员参加培训表  -sdy -2016年12月6日22:52:06
DROP TABLE IF EXISTS `tdriver_training`;
CREATE TABLE `tdriver_training` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '驾驶员参加培训表',
  `training_id` int(11) NOT NULL COMMENT '培训id',
  `driver_id` int(11) NOT NULL COMMENT '驾驶员id',
  `status` tinyint(4) DEFAULT NULL COMMENT '参加培训状态，通过/不通过/缺席/取消考试资格等',
  PRIMARY KEY (`id`),
  KEY `training_id` (`training_id`),
  KEY `driver_id` (`driver_id`),
  CONSTRAINT `tdriver_training_ibfk_1` FOREIGN KEY (`training_id`) REFERENCES `ttraining` (`id`),
  CONSTRAINT `tdriver_training_ibfk_2` FOREIGN KEY (`driver_id`) REFERENCES `tdriver` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- 星级规则表记录改变 -sdy -2016年12月8日15:41:23
TRUNCATE TABLE tstar_rule;
INSERT INTO `tstar_rule` VALUES ('5', '3', '4', '654', '0', '==', '一星级驾驶员驾驶专用车辆1年保险赔付零，升级为二星级驾驶员。');
INSERT INTO `tstar_rule` VALUES ('6', '3', '4', '655', '10', '<', '一星级驾驶员驾驶专用车辆连续2年保险赔付率在10%以下的，升级为二星级驾驶员。');
INSERT INTO `tstar_rule` VALUES ('9', '3', '1', '658', '1', '>=', '一星级驾驶员驾驶专用车辆出现1次有责任死亡事故，取消星级，重新培训，重新考试，重新发放培训合格证。');
INSERT INTO `tstar_rule` VALUES ('11', '4', '5', '654', '0', '==', '二星级驾驶员驾驶专用车辆1年保险赔付零，升级为三星级驾驶员。');
INSERT INTO `tstar_rule` VALUES ('12', '4', '5', '655', '10', '<', '二星级驾驶员连续2年保险赔付率在10%以下的升级为三星级驾驶员。');
INSERT INTO `tstar_rule` VALUES ('13', '4', '3', '654', '20', '>', '二星级驾驶员驾驶专用车辆出现1次年度保险赔付超过20%的，降为一星级驾驶员。');
INSERT INTO `tstar_rule` VALUES ('14', '4', '3', '656', '1', '>=', '二星级驾驶员驾驶专用车辆出现1次有责任的人员受伤事故，降为一星级驾驶员。');
INSERT INTO `tstar_rule` VALUES ('16', '4', '1', '658', '1', '>=', '二星级驾驶员驾驶专用车辆出现1次有责任死亡事故，取消星级，重新培训，重新考试，重新发放培训合格证。');
INSERT INTO `tstar_rule` VALUES ('17', '5', '6', '654', '0', '==', '三星级驾驶员驾驶专用车辆1年保险赔付零，升级为四星级驾驶员。');
INSERT INTO `tstar_rule` VALUES ('18', '5', '6', '655', '10', '<', '三星级驾驶员连续2年保险赔付率在10%以下的升级为四星级驾驶员。');
INSERT INTO `tstar_rule` VALUES ('19', '5', '4', '654', '20', '>', '三星级驾驶员驾驶专用车辆出现1次年度保险赔付超过20%的，降为二星级驾驶员。');
INSERT INTO `tstar_rule` VALUES ('20', '5', '4', '656', '1', '>=', '三星级驾驶员驾驶专用车辆出现1次有责任的人员受伤事故，降为二星级驾驶员。');
INSERT INTO `tstar_rule` VALUES ('21', '5', '3', '657', '1', '>=', '三星级驾驶员驾驶专用车辆出现累计2人受伤有责任安全事故，降为一星级驾驶员。');
INSERT INTO `tstar_rule` VALUES ('22', '5', '1', '658', '1', '>=', '三星级驾驶员驾驶专用车辆出现1次有责任死亡事故，取消星级，重新培训，重新考试，重新发放培训合格证。');
INSERT INTO `tstar_rule` VALUES ('23', '6', '7', '654', '0', '==', '四星级驾驶员驾驶专用车辆1年保险赔付零，升级为五星级驾驶员。');
INSERT INTO `tstar_rule` VALUES ('24', '6', '7', '655', '10', '<', '四星级驾驶员连续2年保险赔付率在10%以下的升级为五星级驾驶员。');
INSERT INTO `tstar_rule` VALUES ('25', '6', '5', '654', '20', '>', '四星级驾驶员驾驶专用车辆出现1次年度保险赔付超过20%的，降为三星级驾驶员。');
INSERT INTO `tstar_rule` VALUES ('26', '6', '5', '656', '1', '>=', '四星级驾驶员驾驶专用车辆出现1次有责任的人员受伤事故，降为三星级驾驶员。');
INSERT INTO `tstar_rule` VALUES ('27', '6', '4', '657', '1', '>=', '四星级驾驶员驾驶专用车辆出现累计2人受伤有责任安全事故，降为二星级驾驶员。');
INSERT INTO `tstar_rule` VALUES ('28', '6', '1', '658', '1', '>=', '四星级驾驶员驾驶专用车辆出现1次有责任死亡事故，取消星级，重新培训，重新考试，重新发放培训合格证。');
INSERT INTO `tstar_rule` VALUES ('29', '7', '6', '654', '20', '>', '五星级驾驶员驾驶专用车辆出现1次年度保险赔付超过20%的，降为四星级驾驶员。');
INSERT INTO `tstar_rule` VALUES ('30', '7', '6', '656', '1', '>=', '五星级驾驶员驾驶专用车辆出现1次有责任的人员受伤事故，降为四星级驾驶员。');
INSERT INTO `tstar_rule` VALUES ('31', '7', '5', '657', '1', '>=', '五星级驾驶员驾驶专用车辆出现累计2人受伤有责任安全事故，降为三星级驾驶员。');
INSERT INTO `tstar_rule` VALUES ('32', '7', '1', '658', '1', '>=', '五星级驾驶员驾驶专用车辆出现1次有责任死亡事故，取消星级，重新培训，重新考试，重新发放培训合格证。');



-- 专用车辆表添加责任驾驶员字段  -sdy -2016年12月9日13:03:24
ALTER TABLE tspecial_vehicle ADD driver_id int(11) COMMENT '责任驾驶员';
ALTER TABLE tspecial_vehicle ADD CONSTRAINT `tspecial_vehicle_ibfk_1` FOREIGN KEY (`driver_id`) REFERENCES `tdriver` (`id`);

-- 驾驶员星级表添加原先星级字段，为了计算年度基础星级  -sdy  -2016年12月9日14:12:20
ALTER TABLE tdriver_star ADD pre_star int(11) NOT NULL COMMENT '评定前星级';
ALTER TABLE tdriver_star ADD CONSTRAINT `tdriver_star_ibfk_5` FOREIGN KEY (`pre_star`) REFERENCES `tstar` (`id`);ALTER TABLE torg_basic_info CHANGE COLUMN `driver_captain_id` `driver_captain_id` INT(11) NULL COMMENT '车队长';


ALTER TABLE torg_basic_info CHANGE COLUMN `leader_name` `leader_name` varchar(20) DEFAULT NULL COMMENT '分管领导';
ALTER TABLE torg_basic_info CHANGE COLUMN `leader_tel` `leader_tel` varchar(20) DEFAULT NULL;
ALTER TABLE torg_basic_info ADD CONSTRAINT `torg_basic_info_ibfk_2` FOREIGN KEY (`driver_captain_id`) REFERENCES  `tdriver` (`id`);


-- 培训、考试关联通知  -sdy -2016年12月16日17:59:43
ALTER TABLE ttraining ADD announcement_id int(11) COMMENT '培训关联公告';
ALTER TABLE ttraining ADD CONSTRAINT `ttraining_ibfk_5` FOREIGN KEY (`announcement_id`) REFERENCES `tannouncement` (`id`);
ALTER TABLE texam ADD announcement_id int(11) COMMENT '考试关联公告'; 
ALTER TABLE texam ADD CONSTRAINT `texam_ibfk_5` FOREIGN KEY (`announcement_id`) REFERENCES `tannouncement` (`id`);

-- 星级调整表添加开始时间表示投保信息的开始时间  -sdy  -2016年12月17日11:06:16
 ALTER TABLE tdriver_star ADD start_time datetime COMMENT '星级调整时投保信息的开始时间';
 
  -- 培训表添加报名开始结束时间 -sdy -2016年12月27日15:53:44
ALTER TABLE ttraining ADD signup_start_time datetime COMMENT '培训报名开始时间';
ALTER TABLE ttraining ADD signup_end_time datetime COMMENT '培训报名结束时间';