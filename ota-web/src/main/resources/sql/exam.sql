SET FOREIGN_KEY_CHECKS=0;
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
INSERT INTO `data_dictionary` VALUES ('635', '', null, '2016-08-22 11:50:41', '634', '634', null, '', '1', '6340100', '追尾', null, '', null, '', '0');
INSERT INTO `data_dictionary` VALUES ('636', '', null, '2016-08-22 11:51:31', '634', '634', null, '', '1', '6340100', '剐蹭', null, '', null, '', '0');
INSERT INTO `data_dictionary` VALUES ('637', '', null, '2016-08-22 11:52:28', '634', '634', null, '', '1', '6340100', '侧翻', null, '', null, '', '0');
INSERT INTO `data_dictionary` VALUES ('638', '', null, '2016-08-22 16:48:51', null, null, 'compensation_type', '', '0', '6380000', '理赔类型', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('639', '', null, '2016-08-22 16:50:01', '638', '638', null, '', '1', '6380100', '给付型', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('640', '', null, '2016-08-22 16:50:08', '638', '638', null, '', '1', '6380100', '补偿型', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('641', '', null, '2016-09-29 16:52:48', null, null, 'driverhr_question_type', '', '0', '6410000', '题目题型', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('642', '', null, '2016-09-29 16:53:13', '641', '641', null, '', '1', '6410100', '单选题', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('643', '', null, '2016-09-29 16:53:42', '641', '641', null, '', '1', '6410100', '判断题', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('644', '', null, '2016-09-29 16:54:05', null, null, 'driverhr_question_subject', '', '0', '6440000', '题目知识点', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('645', '道路安全行车规范', null, '2016-09-29 16:54:45', '644', '644', null, '', '1', '6440100', '行车安全', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('646', '', null, '2016-09-29 16:55:49', '644', '644', null, '', '1', '6440100', '职业素养', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('653', '', null, '2016-11-16 17:43:34', null, null, 'star_rule_item', '', '0', '6530000', '星级评比规则类型', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('654', '', null, '2016-11-16 17:44:35', '653', '653', null, 'star_rule.rate', '1', '6530100', '当年理赔率', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('655', '', null, '2016-11-16 17:45:05', '653', '653', null, 'star_rule.rateAvg', '1', '6530100', '连续两年理赔率', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('656', '', null, '2016-11-16 17:45:27', '653', '653', null, 'star_rule.injureCount', '1', '6530100', '有责任伤人事故次数', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('657', '', null, '2016-11-16 17:45:43', '653', '653', null, 'star_rule.injure2Count', '1', '6530100', '有责任累计伤2人以上事故次数', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('658', '', null, '2016-11-16 17:46:09', '653', '653', null, 'star_rule.deadCount', '1', '6530100', '有责任死亡事故次数', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('659', '', null, '2016-11-18 16:40:15', null, null, 'special_vehicle_type', '', '0', '6590000', '专用车辆类型', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('660', '', null, '2016-11-18 16:40:31', '659', '659', null, '', '1', '6590100', '水泥罐车', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('661', '', null, '2016-11-18 16:40:39', '659', '659', null, '', '1', '6590100', '大型搅拌车', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('662', '', null, '2016-11-18 16:40:49', '659', '659', null, '', '1', '6590100', '小水泥车', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('663', '', null, '2016-12-08 20:05:02', '634', '634', null, '', '1', '6340100', '闯红灯', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('664', '', null, '2016-12-08 20:05:10', '634', '634', null, '', '1', '6340100', '违法停车', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('665', '', null, '2016-12-08 20:05:37', '634', '634', null, '', '1', '6340100', '超速', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('666', '', null, '2016-12-08 20:05:47', '634', '634', null, '', '1', '6340100', '违法变道', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('667', '', null, '2016-12-08 20:05:54', '634', '634', null, '', '1', '6340100', '逆向行驶', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('668', '', null, '2016-12-08 20:05:59', '634', '634', null, '', '1', '6340100', '其他', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('669', '', null, '2016-12-15 19:26:36', '641', '641', null, null, '1', '6410100', '多选题', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('670', '', null, '2016-12-15 19:26:44', '641', '641', null, null, '1', '6410100', '填空题', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('671', '', null, '2016-12-15 19:26:48', '641', '641', null, null, '1', '6410100', '简答题', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('672', null, null, '2016-12-16 09:22:19', null, null, 'region', null, '0', '6720000', '区域信息', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('673', null, null, '2016-12-16 09:24:28', '672', '672', null, null, '1', '6720100', '浙江省', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('674', null, null, '2016-12-16 09:24:38', '673', '672', null, null, '2', '6730200', '杭州市', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('675', null, null, '2016-12-16 09:37:37', '672', '672', null, null, '1', '6720100', '江苏省', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('695', '', null, '2016-12-22 20:00:11', null, null, 'industry', '', '0', '6950000', '行业类型', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('696', '水泥办', null, '2016-12-22 20:00:36', '695', '695', null, null, '1', '6950100', '散装水泥管理办公室', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('697', '示例行业，不具备任何意义', null, '2016-12-22 20:01:00', '695', '695', null, null, '1', '6950100', '危险品运输', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('698', null, null, '2016-12-22 20:02:19', '673', '672', null, null, '2', '6730200', '舟山市', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('699', null, null, '2016-12-22 20:02:19', '698', '672', null, null, '3', '6980300', '普陀区', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('700', null, null, '2016-12-22 20:02:19', '673', '672', null, null, '2', '6730200', '金华市', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('701', null, null, '2016-12-22 20:02:19', '700', '672', null, null, '3', '7000300', '义乌市', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('702', null, null, '2016-12-22 20:02:19', '673', '672', null, null, '2', '6730200', '绍兴市', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('703', null, null, '2016-12-22 20:02:19', '702', '672', null, null, '3', '7020300', '绍兴市市辖区', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('704', null, null, '2016-12-22 20:02:20', '673', '672', null, null, '2', '6730200', '宁波市', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('705', null, null, '2016-12-22 20:02:20', '704', '672', null, null, '3', '7040300', '鄞州区', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('706', null, null, '2016-12-22 20:02:20', '673', '672', null, null, '2', '6730200', '台州市', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('707', null, null, '2016-12-22 20:02:20', '706', '672', null, null, '3', '7060300', '椒江区', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('708', null, null, '2016-12-22 20:02:20', '698', '672', null, null, '3', '6980300', '定海区', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('709', null, null, '2016-12-22 20:02:20', '702', '672', null, null, '3', '7020300', '上虞区', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('710', null, null, '2016-12-22 20:02:21', '700', '672', null, null, '3', '7000300', '永康市', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('711', null, null, '2016-12-22 20:02:21', '673', '672', null, null, '2', '6730200', '湖州市', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('712', null, null, '2016-12-22 20:02:21', '711', '672', null, null, '3', '7110300', '吴兴区', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('713', null, null, '2016-12-22 20:02:21', '711', '672', null, null, '3', '7110300', '德清县', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('714', null, null, '2016-12-22 20:02:22', '704', '672', null, null, '3', '7040300', '宁波市市辖区', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('715', null, null, '2016-12-22 20:02:22', '711', '672', null, null, '3', '7110300', '长兴县', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('716', '', null, '2016-12-22 21:36:38', '695', '695', null, null, '1', '6950100', '渣土车行业', null, null, null, null, '1');


-- ----------------------------
-- Table structure for tannouncement
-- ----------------------------
DROP TABLE IF EXISTS `tannouncement`;
CREATE TABLE `tannouncement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `attachment` varchar(255) DEFAULT NULL,
  `title` varchar(100) NOT NULL COMMENT '标题',
  `text` varchar(2000) DEFAULT NULL COMMENT '正文',
  `type` tinyint(4) NOT NULL,
  `source_organization_id` int(11) NOT NULL COMMENT '创建人所在的org，也就是公告来源',
  `create_by` int(11) NOT NULL COMMENT '创建人',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `closure_date` timestamp NULL DEFAULT NULL COMMENT '有效期，若为空  有效期就是无限大',
  `read_count` int(11) NOT NULL DEFAULT '0' COMMENT '阅读次数',
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `source_org_idx` (`source_organization_id`),
  KEY `creater_idx` (`create_by`),
  CONSTRAINT `creater` FOREIGN KEY (`create_by`) REFERENCES `tmember` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `source_org` FOREIGN KEY (`source_organization_id`) REFERENCES `torganization` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='公告表';

-- ----------------------------
-- Records of tannouncement
-- ----------------------------
INSERT INTO `tannouncement` VALUES ('23', '', '随机考试通知', '好好准备', '2', '1', '1', '2017-01-12 20:55:01', null, '1', '1');
INSERT INTO `tannouncement` VALUES ('24', '', '浙江省安全考试通知', '各个考生办请督促考生复习', '2', '1', '1', '2017-01-13 13:37:04', null, '0', '1');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tdepartment
-- ----------------------------

-- ----------------------------
-- Table structure for tdriver
-- ----------------------------
DROP TABLE IF EXISTS `tdriver`;
CREATE TABLE `tdriver` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50),
  `birth_place` varchar(50),
  `birth` date DEFAULT NULL,
  `gender` smallint(6) DEFAULT NULL,
  `sfz_no` varchar(20),
  `photo` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `postcode` varchar(10) DEFAULT NULL,
  `mobile` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `org_id` int(11),
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
  `star_time` datetime DEFAULT NULL COMMENT '驾驶员星级评定时间',
  `train_centificate_no` varchar(255) DEFAULT NULL,
  `train_centificate_photo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `sfz_no` (`sfz_no`),
  KEY `FKA96F719C53A82C48` (`org_id`),
  CONSTRAINT `FKA96F719C53A82C48` FOREIGN KEY (`org_id`) REFERENCES `torganization` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tdriver
-- ----------------------------
INSERT INTO `tdriver` VALUES ('1', '裴红', '杭州', '1980-06-12', '1', '12345678', '', '', '', '18800008888', '123456', '', '2', '', '', '', '', null, null, '', '', null, null, '2', null, '', '');
INSERT INTO `tdriver` VALUES ('2', '郑东升', '杭州', '1970-08-06', '1', '12345679', '', '', '', '18800008888', '123456', '', '2', '', '', '', '', null, null, '', '', null, null, '2', null, '', '');
INSERT INTO `tdriver` VALUES ('5', '周汝金', '杭州', '1970-08-06', '1', '12345682', '', '', '', '18800008888', '123456', '', '2', '', '', '', '', null, null, '', '', null, null, '2', null, '', '');
INSERT INTO `tdriver` VALUES ('6', '蔡澜', '杭州', '1970-08-06', '1', '12345683', '', '', '', '18800008888', '123456', '', '2', '', '', '', '', null, null, '', '', null, null, '2', null, '', '');
INSERT INTO `tdriver` VALUES ('7', '胡云充', '杭州', '1970-08-06', '1', '12345684', '', '', '', '18800008888', '123456', '', '2', '', '', '', '', null, null, '', '', null, null, '2', null, '', '');
INSERT INTO `tdriver` VALUES ('8', '章芬娟', '杭州', '1970-08-06', '1', '12345685', '', '', '', '18800008888', '123456', '', '2', '', '', '', '', null, null, '', '', null, null, '2', null, '', '');
INSERT INTO `tdriver` VALUES ('9', '王锦团', '杭州', '1970-08-06', '1', '12345686', '', '', '', '18800008888', '123456', '', '2', '', '', '', '', null, null, '', '', null, null, '2', null, '', '');
INSERT INTO `tdriver` VALUES ('10', '罗立军', '杭州', '1970-08-06', '1', '12345687', '', '', '', '18800008888', '123456', '', '2', '', '', '', '', null, null, '', '', null, null, '2', null, '', '');
INSERT INTO `tdriver` VALUES ('11', '陈希罕', '台州', '1980-06-12', '1', '12345688', '', '', '', '18800008888', '123456', '', '3', '', '', '', '', null, null, '', '', null, null, '2', null, '', '');
INSERT INTO `tdriver` VALUES ('12', '唐永友', '台州', '1970-08-06', '1', '12345689', '', '', '', '18800008888', '123456', '', '3', '', '', '', '', null, null, '', '', null, null, '2', null, '', '');
INSERT INTO `tdriver` VALUES ('13', '潘志文', '台州', '1970-08-06', '1', '12345690', '', '', '', '18800008888', '123456', '', '3', '', '', '', '', null, null, '', '', null, null, '2', null, '', '');
INSERT INTO `tdriver` VALUES ('14', '任狄彪', '台州', '1970-08-06', '1', '12345691', '', '', '', '18800008888', '123456', '', '3', '', '', '', '', null, null, '', '', null, null, '2', null, '', '');
INSERT INTO `tdriver` VALUES ('15', '徐发林', '台州', '1970-08-06', '1', '12345692', '', '', '', '18800008888', '123456', '', '3', '', '', '', '', null, null, '', '', null, null, '2', null, '', '');
INSERT INTO `tdriver` VALUES ('16', '苏海华', '台州', '1970-08-06', '1', '12345693', '', '', '', '18800008888', '123456', '', '3', '', '', '', '', null, null, '', '', null, null, '2', null, '', '');
INSERT INTO `tdriver` VALUES ('17', '陆晓挥', '台州', '1970-08-06', '1', '12345694', '', '', '', '18800008888', '123456', '', '3', '', '', '', '', null, null, '', '', null, null, '2', null, '', '');
INSERT INTO `tdriver` VALUES ('18', '高峰', '台州', '1970-08-06', '1', '12345695', '', '', '', '18800008888', '123456', '', '3', '', '', '', '', null, null, '', '', null, null, '2', null, '', '');
INSERT INTO `tdriver` VALUES ('19', '朱建冬', '台州', '1970-08-06', '1', '12345696', '', '', '', '18800008888', '123456', '', '3', '', '', '', '', null, null, '', '', null, null, '2', null, '', '');
INSERT INTO `tdriver` VALUES ('20', '王怡德', '台州', '1970-08-06', '1', '12345697', '', '', '', '18800008888', '123456', '', '3', '', '', '', '', null, null, '', '', null, null, '2', null, '', '');
INSERT INTO `tdriver` VALUES ('88', '新增考生', '杭州', '1996-05-18', '0', '331023199605181429', '', '浙江科技学院', '310000', '17826802391', '181429', '237813@qq.com', '2', null, null, null, null, null, null, null, null, null, null, '2', null, null, null);
INSERT INTO `tdriver` VALUES ('90', '杭州考生办新增驾驶员', '浙', null, '1', '123489231', null, '', null, '17826801923', '0000', null, '2', null, null, '123489231', null, null, null, null, null, null, null, '2', null, null, null);

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
  `announcement_id` int(11) DEFAULT NULL COMMENT '考试关联公告',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK6925E9353A82C48` (`org_id`),
  KEY `FK6925E93E10B685B` (`paper_id`),
  KEY `texam_ibfk_5` (`announcement_id`),
  CONSTRAINT `FK6925E9353A82C48` FOREIGN KEY (`org_id`) REFERENCES `torganization` (`id`),
  CONSTRAINT `FK6925E93E10B685B` FOREIGN KEY (`paper_id`) REFERENCES `tpaper` (`id`),
  CONSTRAINT `texam_ibfk_5` FOREIGN KEY (`announcement_id`) REFERENCES `tannouncement` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of texam
-- ----------------------------
INSERT INTO `texam` VALUES ('15', '浙江统一考试', '浙江省统一考试', '2017-01-12 17:17:10', '1', '11', '2017-01-12 00:00:00', '2017-01-12 18:00:00', '2017-01-12 18:30:00', '2017-01-14 23:00:00', '0', '0', '0', null);
INSERT INTO `texam` VALUES ('16', '浙江随机卷考试', '', '2017-01-12 20:26:29', '1', '12', '2017-01-12 00:00:00', '2017-01-13 23:00:00', '2017-01-15 08:00:00', '2017-01-15 10:00:00', '0', '0', '120', '23');
INSERT INTO `texam` VALUES ('17', '浙江省线下考试测试', '', '2017-01-13 10:27:45', '1', '11', '2017-01-12 00:00:00', '2017-01-12 23:00:00', '2017-01-13 08:00:00', '2017-01-13 10:00:00', '5', '2', '0', null);
INSERT INTO `texam` VALUES ('18', '浙江省安全考试', '安全考试', '2017-01-13 12:54:05', '1', '11', '2017-01-13 00:00:00', '2017-01-13 23:00:00', '2017-01-14 09:00:00', '2017-01-14 11:00:00', '5', '0', '0', '24');

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
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of texam_result
-- ----------------------------
INSERT INTO `texam_result` VALUES ('10', 'A', '0', '1', '2017-01-12 18:41:53', '2', '15', '27');
INSERT INTO `texam_result` VALUES ('11', 'B', '0', '2', '2017-01-12 18:41:53', '2', '15', '50');
INSERT INTO `texam_result` VALUES ('12', 'A', '0', '3', '2017-01-12 18:41:53', '2', '15', '6');
INSERT INTO `texam_result` VALUES ('13', 'A', '0', '4', '2017-01-12 18:41:53', '2', '15', '32');
INSERT INTO `texam_result` VALUES ('14', 'A', '0', '5', '2017-01-12 18:41:53', '2', '15', '2');
INSERT INTO `texam_result` VALUES ('15', null, '0', '6', '2017-01-12 18:41:53', '2', '15', '7');
INSERT INTO `texam_result` VALUES ('16', null, '0', '7', '2017-01-12 18:41:53', '2', '15', '25');
INSERT INTO `texam_result` VALUES ('17', null, '0', '8', '2017-01-12 18:41:53', '2', '15', '29');
INSERT INTO `texam_result` VALUES ('18', null, '0', '9', '2017-01-12 18:41:53', '2', '15', '5');
INSERT INTO `texam_result` VALUES ('19', null, '0', '10', '2017-01-12 18:41:53', '2', '15', '31');
INSERT INTO `texam_result` VALUES ('20', null, '0', '11', '2017-01-12 18:41:53', '2', '15', '49');
INSERT INTO `texam_result` VALUES ('21', null, '0', '12', '2017-01-12 18:41:53', '2', '15', '3');
INSERT INTO `texam_result` VALUES ('22', null, '0', '13', '2017-01-12 18:41:53', '2', '15', '30');
INSERT INTO `texam_result` VALUES ('23', null, '0', '14', '2017-01-12 18:41:53', '2', '15', '8');
INSERT INTO `texam_result` VALUES ('24', null, '0', '15', '2017-01-12 18:41:53', '2', '15', '26');
INSERT INTO `texam_result` VALUES ('25', 'A', '0', '1', '2017-01-13 13:04:44', '5', '15', '27');
INSERT INTO `texam_result` VALUES ('26', 'A', '0', '2', '2017-01-13 13:04:44', '5', '15', '50');
INSERT INTO `texam_result` VALUES ('27', 'A', '0', '3', '2017-01-13 13:04:44', '5', '15', '6');
INSERT INTO `texam_result` VALUES ('28', 'B', '0', '4', '2017-01-13 13:04:44', '5', '15', '32');
INSERT INTO `texam_result` VALUES ('29', 'B', '0', '5', '2017-01-13 13:04:44', '5', '15', '2');
INSERT INTO `texam_result` VALUES ('30', 'A', '0', '6', '2017-01-13 13:04:44', '5', '15', '7');
INSERT INTO `texam_result` VALUES ('31', 'A', '0', '7', '2017-01-13 13:04:44', '5', '15', '25');
INSERT INTO `texam_result` VALUES ('32', '', '0', '8', '2017-01-13 13:04:44', '5', '15', '29');
INSERT INTO `texam_result` VALUES ('33', 'A', '0', '9', '2017-01-13 13:04:44', '5', '15', '5');
INSERT INTO `texam_result` VALUES ('34', 'B', '0', '10', '2017-01-13 13:04:44', '5', '15', '31');
INSERT INTO `texam_result` VALUES ('35', 'B', '0', '11', '2017-01-13 13:04:44', '5', '15', '49');
INSERT INTO `texam_result` VALUES ('36', 'A', '0', '12', '2017-01-13 13:04:44', '5', '15', '3');
INSERT INTO `texam_result` VALUES ('37', 'A', '0', '13', '2017-01-13 13:04:44', '5', '15', '30');
INSERT INTO `texam_result` VALUES ('38', 'A', '0', '14', '2017-01-13 13:04:44', '5', '15', '8');
INSERT INTO `texam_result` VALUES ('39', 'A', '0', '15', '2017-01-13 13:04:44', '5', '15', '26');

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
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK919A14443A5D4F19` (`exam_id`),
  KEY `FK919A1444C99AF2B9` (`driver_id`),
  CONSTRAINT `FK919A14443A5D4F19` FOREIGN KEY (`exam_id`) REFERENCES `texam` (`id`),
  CONSTRAINT `FK919A1444C99AF2B9` FOREIGN KEY (`driver_id`) REFERENCES `tdriver` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of texam_signup
-- ----------------------------
INSERT INTO `texam_signup` VALUES ('10', '15', '1', '2017-01-12 18:28:03', null, null, null, '-1', null, null, null);
INSERT INTO `texam_signup` VALUES ('11', '15', '2', '2017-01-12 18:36:09', null, null, null, '2', null, '2017-01-12 18:41:54', null);
INSERT INTO `texam_signup` VALUES ('12', '15', '5', '2017-01-12 18:36:09', '10', null, null, '3', null, '2017-01-13 13:04:44', '10');
INSERT INTO `texam_signup` VALUES ('13', '15', '6', '2017-01-12 18:36:09', null, null, null, '1', null, null, null);
INSERT INTO `texam_signup` VALUES ('14', '15', '7', '2017-01-12 18:36:09', null, null, null, '1', null, null, null);
INSERT INTO `texam_signup` VALUES ('15', '15', '8', '2017-01-12 18:36:09', null, null, null, '1', null, null, null);
INSERT INTO `texam_signup` VALUES ('16', '17', '1', '2017-01-13 10:28:11', '8', '123', 'uploadFile/exam/certificate/photo/1_1484284845930.png', '4', null, '2017-01-13 08:00:00', null);
INSERT INTO `texam_signup` VALUES ('17', '17', '2', '2017-01-13 10:28:11', '9', '1234', '', '4', null, '2017-01-13 08:00:00', null);
INSERT INTO `texam_signup` VALUES ('18', '17', '5', '2017-01-13 10:28:11', '7', '', null, '4', null, '2017-01-13 08:00:00', null);
INSERT INTO `texam_signup` VALUES ('19', '17', '6', '2017-01-13 10:28:11', '6', '', '', '4', null, '2017-01-13 08:00:00', null);
INSERT INTO `texam_signup` VALUES ('20', '17', '7', '2017-01-13 10:28:11', '8', '123', '', '4', null, '2017-01-13 08:00:00', null);
INSERT INTO `texam_signup` VALUES ('21', '18', '1', '2017-01-13 13:14:00', null, null, null, '1', null, null, null);
INSERT INTO `texam_signup` VALUES ('22', '18', '2', '2017-01-13 13:14:00', null, null, null, '1', null, null, null);
INSERT INTO `texam_signup` VALUES ('23', '18', '5', '2017-01-13 13:14:00', null, null, null, '1', null, null, null);
INSERT INTO `texam_signup` VALUES ('24', '18', '6', '2017-01-13 13:14:00', null, null, null, '1', null, null, null);
INSERT INTO `texam_signup` VALUES ('25', '18', '7', '2017-01-13 13:14:00', null, null, null, '1', null, null, null);

-- ----------------------------
-- Table structure for tmember
-- ----------------------------
DROP TABLE IF EXISTS `tmember`;
CREATE TABLE `tmember` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime ,
  `login_name` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `sfz_no` varchar(20) DEFAULT NULL,
  `status` smallint(6) NOT NULL,
  `create_by` int(11) DEFAULT NULL,
  `org_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FKB815640E53A82C48` (`org_id`),
  KEY `FKB815640E4D5B3B13` (`create_by`),
  CONSTRAINT `FKB815640E4D5B3B13` FOREIGN KEY (`create_by`) REFERENCES `tmember` (`id`),
  CONSTRAINT `FKB815640E53A82C48` FOREIGN KEY (`org_id`) REFERENCES `torganization` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tmember
-- ----------------------------
INSERT INTO `tmember` VALUES ('1', '2017-01-12 16:27:16', '18800008888', '浙江省考试办管理员', '123456', '18800009999', '1234128478912489', '1', '1', '1');
INSERT INTO `tmember` VALUES ('2', '2017-01-12 16:28:24', 'hangzhou', '杭州考生办管理员', '123456', '1283781927', '9813761892367918', '1', '1', '2');
INSERT INTO `tmember` VALUES ('3', '2017-01-12 16:29:32', 'taizhou', '台州考生办管理员', '123456', '123687687', '12763187263876128', '1', '1', '3');

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
  `region` varchar(20) DEFAULT NULL,
  `linkman_name` varchar(50) DEFAULT NULL,
  `region_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK59A3DFA7AB06F0D9` (`link_person_id`),
  KEY `FK59A3DFA7B2E34C2` (`parent_id`),
  CONSTRAINT `FK59A3DFA7AB06F0D9` FOREIGN KEY (`link_person_id`) REFERENCES `tmember` (`id`),
  CONSTRAINT `FK59A3DFA7B2E34C2` FOREIGN KEY (`parent_id`) REFERENCES `torganization` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of torganization
-- ----------------------------
INSERT INTO `torganization` VALUES ('1', '考试管理组织', null, '考试管理组织', null, '考试办', '1', '18800008888', '0', null, null, '', null, '673');
INSERT INTO `torganization` VALUES ('2', '考生管理组织1', null, '杭州考生办', null, '杭州考生办', '1', '1276357657', '3', null, '1', null, null, null);
INSERT INTO `torganization` VALUES ('3', '考生管理组织2', null, '台州考生办', null, '台州考生办', '1', '1276357657', '3', null, '1', null, null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tpaper
-- ----------------------------
INSERT INTO `tpaper` VALUES ('11', '浙江省预设试卷', '', '2017-01-12 18:10:02', '1', '0', '1', '1', '0.3', '0.4', '0.3', '8', '8');
INSERT INTO `tpaper` VALUES ('12', '浙江省随机试卷', '', '2017-01-12 20:21:11', '1', '0', '0', '1', '0.3', '0.4', '0.3', '8', null);
INSERT INTO `tpaper` VALUES ('13', '测试试卷', '', '2017-01-13 13:26:31', '1', '0', '1', '-1', '0.3', '0.4', '0.3', null, '6');

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
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tpaper_design
-- ----------------------------
INSERT INTO `tpaper_design` VALUES ('36', '11', '645', '642', '3');
INSERT INTO `tpaper_design` VALUES ('37', '11', '646', '642', '4');
INSERT INTO `tpaper_design` VALUES ('38', '11', '645', '670', '3');
INSERT INTO `tpaper_design` VALUES ('39', '11', '646', '670', '4');
INSERT INTO `tpaper_design` VALUES ('40', '12', '645', '642', '4');
INSERT INTO `tpaper_design` VALUES ('41', '12', '646', '642', '3');
INSERT INTO `tpaper_design` VALUES ('42', '12', '645', '670', '3');
INSERT INTO `tpaper_design` VALUES ('43', '12', '646', '670', '4');
INSERT INTO `tpaper_design` VALUES ('44', '13', '645', '642', '4');
INSERT INTO `tpaper_design` VALUES ('45', '13', '646', '642', '5');

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
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tpaper_list
-- ----------------------------
INSERT INTO `tpaper_list` VALUES ('17', '11', '27', '1');
INSERT INTO `tpaper_list` VALUES ('18', '11', '50', '2');
INSERT INTO `tpaper_list` VALUES ('19', '11', '6', '3');
INSERT INTO `tpaper_list` VALUES ('20', '11', '32', '4');
INSERT INTO `tpaper_list` VALUES ('21', '11', '2', '5');
INSERT INTO `tpaper_list` VALUES ('22', '11', '7', '6');
INSERT INTO `tpaper_list` VALUES ('23', '11', '25', '7');
INSERT INTO `tpaper_list` VALUES ('24', '11', '29', '8');
INSERT INTO `tpaper_list` VALUES ('25', '11', '5', '9');
INSERT INTO `tpaper_list` VALUES ('26', '11', '31', '10');
INSERT INTO `tpaper_list` VALUES ('27', '11', '49', '11');
INSERT INTO `tpaper_list` VALUES ('28', '11', '3', '12');
INSERT INTO `tpaper_list` VALUES ('29', '11', '30', '13');
INSERT INTO `tpaper_list` VALUES ('30', '11', '8', '14');
INSERT INTO `tpaper_list` VALUES ('31', '11', '26', '15');

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
  `required` smallint(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tquestion
-- ----------------------------
INSERT INTO `tquestion` VALUES ('1', '这是一个简单数学选择题', '', '', '', '645', '642', 'A', '1', '1', '1', '', '2016-08-15 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('2', '这也是一个简单数学选择题', '', '', '', '645', '642', 'A', '1', '1', '1', '', '2016-08-16 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('3', '这还是一个简单数学选择题', '', '', '', '645', '642', 'A', '1', '1', '1', '', '2016-08-17 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('4', '这没完没了的简单数学选择题', '', '', '', '645', '642', 'A', '1', '1', '1', '', '2016-08-18 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('5', '这是一个简单语文选择题', '', '', '', '646', '642', 'A', '1', '1', '1', '', '2016-08-19 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('6', '这也是一个简单语文选择题', '', '', '', '646', '642', 'A', '1', '1', '1', '', '2016-08-20 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('7', '这还是一个简单语文选择题', '', '', '', '646', '642', 'A', '1', '1', '1', '', '2016-08-21 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('8', '这没完没了的简单语文选择题', '', '', '', '646', '642', 'A', '1', '1', '1', '', '2016-08-22 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('9', '这是一个中等数学选择题', '', '', '', '645', '642', 'A', '1', '2', '1', '', '2016-08-23 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('10', '这也是一个中等数学选择题', '', '', '', '645', '642', 'A', '1', '2', '1', '', '2016-08-24 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('11', '这还是一个中等数学选择题', '', '', '', '645', '642', 'A', '1', '2', '1', '', '2016-08-25 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('12', '这没完没了的中等数学选择题', '', '', '', '645', '642', 'A', '1', '2', '1', '', '2016-08-26 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('13', '这是一个中等语文选择题', '', '', '', '646', '642', 'A', '1', '2', '1', '', '2016-08-27 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('14', '这也是一个中等语文选择题', '', '', '', '646', '642', 'A', '1', '2', '1', '', '2016-08-28 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('15', '这还是一个中等语文选择题', '', '', '', '646', '642', 'A', '1', '2', '1', '', '2016-08-29 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('16', '这没完没了的中等语文选择题', '', '', '', '646', '642', 'A', '1', '2', '1', '', '2016-08-30 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('17', '这是一个困难数学选择题', '', '', '', '645', '642', 'A', '1', '3', '1', '', '2016-08-31 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('18', '这也是一个困难数学选择题', '', '', '', '645', '642', 'A', '1', '3', '1', '', '2016-09-01 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('19', '这还是一个困难数学选择题', '', '', '', '645', '642', 'A', '1', '3', '1', '', '2016-09-02 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('20', '这没完没了的困难数学选择题', '', '', '', '645', '642', 'A', '1', '3', '1', '', '2016-09-03 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('21', '这是一个困难语文选择题', '', '', '', '646', '642', 'A', '1', '3', '1', '', '2016-09-04 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('22', '这也是一个困难语文选择题', '', '', '', '646', '642', 'A', '1', '3', '1', '', '2016-09-05 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('23', '这还是一个困难语文选择题', '', '', '', '646', '642', 'A', '1', '3', '1', '', '2016-09-06 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('24', '这没完没了的困难语文选择题', '', '', '', '646', '642', 'A', '1', '3', '1', '', '2016-09-07 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('25', '这是一个简单数学填空题', '', '', '', '645', '670', 'A', '1', '1', '1', '', '2016-09-08 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('26', '这也是一个简单数学填空题', '', '', '', '645', '670', 'A', '1', '1', '1', '', '2016-09-09 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('27', '这还是一个简单数学填空题', '', '', '', '645', '670', 'A', '1', '1', '1', '', '2016-09-10 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('28', '这没完没了的简单数学填空题', '', '', '', '645', '670', 'A', '1', '1', '1', '', '2016-09-11 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('29', '这是一个简单语文填空题', '', '', '', '646', '670', 'A', '1', '1', '1', '', '2016-09-12 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('30', '这也是一个简单语文填空题', '', '', '', '646', '670', 'A', '1', '1', '1', '', '2016-09-13 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('31', '这还是一个简单语文填空题', '', '', '', '646', '670', 'A', '1', '1', '1', '', '2016-09-14 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('32', '这没完没了的简单语文填空题', '', '', '', '646', '670', 'A', '1', '1', '1', '', '2016-09-15 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('33', '这是一个中等数学填空题', '', '', '', '645', '670', 'A', '1', '2', '1', '', '2016-09-16 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('34', '这也是一个中等数学填空题', '', '', '', '645', '670', 'A', '1', '2', '1', '', '2016-09-17 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('35', '这还是一个中等数学填空题', '', '', '', '645', '670', 'A', '1', '2', '1', '', '2016-09-18 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('36', '这没完没了的中等数学填空题', '', '', '', '645', '670', 'A', '1', '2', '1', '', '2016-09-19 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('37', '这是一个中等语文填空题', '', '', '', '646', '670', 'A', '1', '2', '1', '', '2016-09-20 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('38', '这也是一个中等语文填空题', '', '', '', '646', '670', 'A', '1', '2', '1', '', '2016-09-21 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('39', '这还是一个中等语文填空题', '', '', '', '646', '670', 'A', '1', '2', '1', '', '2016-09-22 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('40', '这没完没了的中等语文填空题', '', '', '', '646', '670', 'A', '1', '2', '1', '', '2016-09-23 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('41', '这是一个困难数学填空题', '', '', '', '645', '670', 'A', '1', '3', '1', '', '2016-09-24 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('42', '这也是一个困难数学填空题', '', '', '', '645', '670', 'A', '1', '3', '1', '', '2016-09-25 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('43', '这还是一个困难数学填空题', '', '', '', '645', '670', 'A', '1', '3', '1', '', '2016-09-26 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('44', '这没完没了的困难数学填空题', '', '', '', '645', '670', 'A', '1', '3', '1', '', '2016-09-27 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('45', '这是一个困难语文填空题', '', '', '', '646', '670', 'A', '1', '3', '1', '', '2016-09-28 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('46', '这也是一个困难语文填空题', '', '', '', '646', '670', 'A', '1', '3', '1', '', '2016-09-29 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('47', '这还是一个困难语文填空题', '', '', '', '646', '670', 'A', '1', '3', '1', '', '2016-09-30 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('48', '这没完没了的困难语文填空题', '', '', '', '646', '670', 'A', '1', '3', '1', '', '2016-10-01 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('49', '这是一个简单数学简答题', '', '', '', '645', '642', 'A', '1', '1', '1', '', '2016-10-02 17:16:00', null, '0');
INSERT INTO `tquestion` VALUES ('50', '这也是一个简单数学简答题', '', '', '', '645', '642', 'A', '1', '1', '1', '', '2016-10-03 17:16:00', null, '1');
INSERT INTO `tquestion` VALUES ('51', '多选题', null, null, null, '645', '669', 'A&&B', '1', '1', '1', null, '2016-12-16 16:46:36', null, '0');
INSERT INTO `tquestion` VALUES ('52', '填空题', null, null, null, '645', '670', 'A', '1', '1', '1', null, '2016-12-16 16:47:19', null, '0');
INSERT INTO `tquestion` VALUES ('53', '简答题', null, null, null, '645', '671', 'A', '1', '1', '1', null, '2016-12-16 16:47:47', null, '0');
INSERT INTO `tquestion` VALUES ('54', '判断题', null, null, null, '645', '643', 'A', '1', '2', '1', null, '2017-01-12 18:08:49', null, '0');


-- ----------------------------
-- Records of tquestion_items
-- ----------------------------
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第一个选项', '', '1', '1');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第一个选项', '', '1', '2');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第一个选项', '', '1', '3');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第一个选项', '', '1', '4');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第一个选项', '', '1', '5');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第一个选项', '', '1', '6');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第一个选项', '', '1', '7');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第一个选项', '', '1', '8');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第一个选项', '', '1', '9');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第一个选项', '', '1', '10');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第一个选项', '', '1', '11');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第一个选项', '', '1', '12');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第一个选项', '', '1', '13');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第一个选项', '', '1', '14');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第一个选项', '', '1', '15');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第一个选项', '', '1', '16');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第一个选项', '', '1', '18');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第一个选项', '', '1', '19');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第一个选项', '', '1', '20');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第一个选项', '', '1', '21');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第一个选项', '', '1', '22');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第一个选项', '', '1', '23');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第一个选项', '', '1', '24');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第一个选项', '', '1', '49');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第一个选项', '', '1', '50');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第二个选项', '', '2', '1');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第二个选项', '', '2', '2');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第二个选项', '', '2', '3');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第二个选项', '', '2', '4');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第二个选项', '', '2', '5');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第二个选项', '', '2', '6');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第二个选项', '', '2', '7');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第二个选项', '', '2', '8');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第二个选项', '', '2', '9');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第二个选项', '', '2', '10');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第二个选项', '', '2', '11');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第二个选项', '', '2', '12');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第二个选项', '', '2', '13');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第二个选项', '', '2', '14');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第二个选项', '', '2', '15');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第二个选项', '', '2', '16');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第二个选项', '', '2', '18');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第二个选项', '', '2', '19');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第二个选项', '', '2', '20');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第二个选项', '', '2', '21');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第二个选项', '', '2', '22');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第二个选项', '', '2', '23');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第二个选项', '', '2', '24');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第二个选项', '', '2', '49');
INSERT INTO `tquestion_items`(content,pic,sequence,question_id) VALUES ('第二个选项', '', '2', '50');

-- 驾驶员表添加 ota 用户id -sdy -2018年5月11日19:44:49
ALTER TABLE tdriver ADD COLUMN ota_user_id INT(11) UNIQUE COMMENT '新闻发言人平台用户 id';

-- 工作人员添加 ota 用户 id -sdy -2018年5月11日19:45:33
ALTER TABLE tmember ADD COLUMN ota_user_id INT(11) UNIQUE COMMENT '新闻发言人平台用户 id';

-- 组织表添加 ota 组织 id -sdy -2018年5月11日19:49:22
ALTER TABLE torganization ADD COLUMN ota_org_id INT(11) UNIQUE COMMENT '新闻发言人平台组织 id';

-- 工作人员表添加新闻发言人系统平台管理员 -sdy -2018年5月11日22:43:41
INSERT INTO `tmember`(id,create_time,login_name,name,password,phone,status,ota_user_id) VALUES ('4','2018-03-26 22:35:51','admin','admin','123456','17826801239','1','1');

-- 组织表添加新闻发言人系统平台组织 -sdy -2018年5月11日22:43:41
INSERT INTO `torganization`(id,description,name,short_name,status,type,link_person_id,linkman_name,ota_org_id) VALUES ('4','平台组织','平台组织','平台','1','0','4','平台管理员',1);

-- 工作人员表关联组织表平台组织 -sdy -2018年5月11日22:43:41
UPDATE tmember set org_id = 4 where id = 4;