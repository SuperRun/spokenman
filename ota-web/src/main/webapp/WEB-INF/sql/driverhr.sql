/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : driverhr

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2016-08-08 21:26:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `data_dictionary`
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
) ENGINE=InnoDB AUTO_INCREMENT=623 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of data_dictionary
-- ----------------------------
INSERT INTO `data_dictionary` VALUES ('1', '', null, '2014-08-18 11:42:48', null, null, 'inspection_application_type', null, '0', '10000', '应用场景', null, '', null, null, '1');
INSERT INTO `data_dictionary` VALUES ('2', '', null, '2014-08-18 11:43:18', '1', '1', '', null, '1', '10101', '聚划算质检', null, '', '1', null, '1');
INSERT INTO `data_dictionary` VALUES ('3', '', null, '2014-08-18 11:43:41', '1', '1', '', null, '1', '10102', '入驻续签天猫', null, '', '2', null, '1');
INSERT INTO `data_dictionary` VALUES ('4', '', null, '2014-08-18 11:44:00', '1', '1', '', null, '1', '10103', '入驻续签京东', null, '', '3', null, '1');
INSERT INTO `data_dictionary` VALUES ('5', '', null, '2014-08-18 11:44:56', '1', '1', '', null, '1', '10104', '入驻续签拍拍', null, '', '4', null, '1');
INSERT INTO `data_dictionary` VALUES ('6', '', null, '2014-08-18 11:45:06', '1', '1', '', null, '1', '10105', '入驻续签苏宁', null, '', '5', null, '1');
INSERT INTO `data_dictionary` VALUES ('7', '亚马逊，1号店，当当等', null, '2014-08-18 11:45:15', '1', '1', '', null, '1', '10106', '电商平台入驻质检', null, '', '6', null, '1');
INSERT INTO `data_dictionary` VALUES ('8', '唯品，聚美，糯米团等', null, '2014-08-18 11:45:28', '1', '1', '', null, '1', '10107', '团购活动质检', null, '', '7', null, '1');
INSERT INTO `data_dictionary` VALUES ('9', '电视购物频道，如浙江好易购，央视购物等', null, '2014-09-01 16:57:23', '1', '1', '', null, '1', '10108', '电视购物质检', null, '', '8', null, '1');
INSERT INTO `data_dictionary` VALUES ('10', '', null, '2014-09-01 16:57:27', '1', '1', '', null, '1', '10109', '非特化妆品备案', null, '', '9', null, '1');
INSERT INTO `data_dictionary` VALUES ('11', '', null, '2014-09-01 16:57:31', '1', '1', '', null, '1', '10110', '进出口检验', null, '', '10', null, '1');
INSERT INTO `data_dictionary` VALUES ('12', '样品类别与服务类别内容统一', null, '2014-08-18 12:07:56', null, null, 'inspection_sample_type', null, '0', '120000', '服务产品类别', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('13', '', null, '2014-08-18 12:08:46', '12', '12', '', null, '1', '120101', '轻工产品', null, null, '1', null, '1');
INSERT INTO `data_dictionary` VALUES ('14', '', null, '2014-08-18 12:08:55', '12', '12', null, null, '1', '120102', '机电产品', null, null, '2', null, '1');
INSERT INTO `data_dictionary` VALUES ('15', '', null, '2014-08-18 12:09:10', '12', '12', null, null, '1', '120103', '食品饮料', null, null, '3', null, '1');
INSERT INTO `data_dictionary` VALUES ('16', '', null, '2014-08-18 12:09:21', '12', '12', null, null, '1', '120104', '石化矿产', null, null, '4', null, '1');
INSERT INTO `data_dictionary` VALUES ('17', '', null, '2014-08-18 12:09:38', '13', '12', '', '', '2', '130201', '纺织服装服饰', null, '', '1', '', '1');
INSERT INTO `data_dictionary` VALUES ('18', '', null, '2014-08-18 12:10:12', '13', '12', '', '', '2', '130202', '鞋类箱包皮具', null, '', '2', '', '1');
INSERT INTO `data_dictionary` VALUES ('19', '', null, '2014-08-18 12:10:36', '13', '12', '', '', '2', '130203', '一次性卫生用品', null, '', '3', '', '1');
INSERT INTO `data_dictionary` VALUES ('20', '', null, '2014-08-18 12:10:46', '14', '12', null, null, '2', '120201', '大小家电', null, null, '1', null, '1');
INSERT INTO `data_dictionary` VALUES ('22', '', null, '2014-08-18 12:11:08', '14', '12', null, null, '2', '120203', '音视频', null, null, '3', null, '1');
INSERT INTO `data_dictionary` VALUES ('23', '', null, '2014-08-18 12:11:38', '15', '12', '', '', '2', '150201', '水果、蔬菜，豆类、食用菌、藻类、坚果及籽类', null, '', '1', '', '1');
INSERT INTO `data_dictionary` VALUES ('24', '', null, '2014-08-31 16:57:36', '15', '12', '', '', '2', '150202', '冷冻饮品', null, '', '2', '', '1');
INSERT INTO `data_dictionary` VALUES ('25', '', null, '2014-08-18 12:12:15', '15', '12', '', '', '2', '150203', '脂肪，油和乳化脂肪制品', null, '', '3', '', '1');
INSERT INTO `data_dictionary` VALUES ('26', '', null, '2014-08-18 13:15:29', '13', '12', '', '', '2', '130200', '办公学生文具', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('27', '', null, '2014-08-18 13:15:38', '13', '12', null, null, '2', '120200', '包装', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('28', '', null, '2014-08-18 13:15:47', '13', '12', '', '', '2', '130200', '珠宝饰品摆件', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('29', '', null, '2014-08-18 13:15:58', '13', '12', null, null, '2', '120200', '日化洗涤', null, null, null, null, '0');
INSERT INTO `data_dictionary` VALUES ('30', '', null, '2014-08-18 13:16:05', '13', '12', null, null, '2', '120200', '化妆品', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('31', '', null, '2014-08-18 13:16:16', '13', '12', '', '', '2', '130200', '健身体育乐器', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('32', '', null, '2014-08-18 13:16:35', '14', '12', null, null, '2', '120200', '电动工具', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('33', '', null, '2014-08-18 13:16:45', '14', '12', null, null, '2', '120200', '插座开关', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('34', '', null, '2014-08-18 13:16:56', '14', '12', null, null, '2', '120200', '仪器仪表', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('35', '', null, '2014-08-18 13:17:17', '15', '12', '', '', '2', '150200', '特殊膳食用食品', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('36', '', null, '2014-08-18 13:17:24', '15', '12', '', '', '2', '150200', '乳及乳制品', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('37', '', null, '2014-08-18 13:17:31', '15', '12', '', '', '2', '150200', '调味品', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('38', '', null, '2014-08-18 13:17:43', '15', '12', '', '', '2', '150200', '甜味料，包括蜂蜜', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('39', '', null, '2014-08-18 13:17:55', '15', '12', '', '', '2', '150200', '蛋及蛋制品', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('40', '', null, '2014-08-18 13:18:05', '15', '12', '', '', '2', '150200', '水产及其制品', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('41', '', null, '2014-08-18 13:18:13', '15', '12', '', '', '2', '150200', '肉及肉制品', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('42', '', null, '2014-08-18 13:18:21', '15', '12', '', '', '2', '150200', '焙烤食品', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('43', '', null, '2014-08-18 13:18:28', '15', '12', '', '', '2', '150200', '粮食和粮食制品，包括大米、面粉、杂粮、淀粉', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('44', '', null, '2014-08-18 13:18:37', '15', '12', '', '', '2', '150200', '可可制品、巧克力和巧克力制品及糖果', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('45', '', null, '2014-08-18 13:18:49', '16', '12', null, null, '2', '120200', '化工产品', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('46', '', null, '2014-08-18 13:18:55', '16', '12', null, null, '2', '120200', '成品油', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('47', '', null, '2014-08-18 13:19:02', '16', '12', null, null, '2', '120200', '矿产金属', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('48', '检测机构的主营业务范围', null, '2014-08-19 16:18:17', null, null, 'orgnization_main_business', null, '0', '480000', '主营业务', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('49', '主营业务--检测测试', null, '2014-08-19 16:19:10', '48', '48', '', null, '1', '480101', '产品检测', null, '', '1', null, '1');
INSERT INTO `data_dictionary` VALUES ('50', '主营业务--产品认证', null, '2014-08-19 16:19:34', '48', '48', '', null, '1', '480102', '产品认证', null, null, '2', null, '1');
INSERT INTO `data_dictionary` VALUES ('51', '主营业务--验厂', null, '2014-08-21 09:52:39', '48', '48', '', null, '1', '480103', '实地验厂', null, '', '3', null, '1');
INSERT INTO `data_dictionary` VALUES ('52', '主营业务--验货验品', null, '2014-08-21 09:52:59', '48', '48', null, null, '1', '480104', '验货验品', null, null, '4', null, '1');
INSERT INTO `data_dictionary` VALUES ('62', '质检资讯新闻分类，一级列表', null, '2014-08-26 11:38:06', null, null, 'jianla_news_type', null, '0', '620000', '质检资讯新闻分类', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('63', '行业资讯', null, '2014-08-26 11:38:42', '62', '62', null, null, '1', '620101', '行业资讯', null, null, '2', null, '1');
INSERT INTO `data_dictionary` VALUES ('64', '政策法规', null, '2014-08-26 11:38:59', '62', '62', '', null, '1', '620102', '保障', null, '', '7', null, '1');
INSERT INTO `data_dictionary` VALUES ('65', '平台公告', null, '2014-08-26 11:39:11', '62', '62', null, null, '1', '620103', '平台公告', null, null, '4', null, '1');
INSERT INTO `data_dictionary` VALUES ('66', '平台规则', null, '2014-08-26 11:39:27', '62', '62', null, null, '1', '620104', '平台规则', null, null, '5', null, '1');
INSERT INTO `data_dictionary` VALUES ('67', '机构动态', null, '2014-08-26 17:36:42', '62', '62', '', null, '1', '620105', '机构动态', null, '', '3', null, '1');
INSERT INTO `data_dictionary` VALUES ('190', '', null, '2014-11-12 13:22:55', '62', '62', null, null, '1', '620100', '社会新闻', null, '', '1', null, '1');
INSERT INTO `data_dictionary` VALUES ('191', '', null, '2014-11-12 15:19:53', '62', '62', null, null, '1', '620100', '招商', null, '', '6', null, '1');
INSERT INTO `data_dictionary` VALUES ('192', '平台推荐的机构', null, '2014-11-12 18:02:41', null, null, 'recommend_org', null, '0', '1920000', '机构推荐', null, '', null, null, '1');
INSERT INTO `data_dictionary` VALUES ('193', null, null, '2014-11-12 18:26:12', '192', '192', null, null, '1', '1920100', '莱茵技术上海有限公司', null, 'laiyin.kaopu.com', null, null, '0');
INSERT INTO `data_dictionary` VALUES ('194', null, null, '2014-11-12 18:29:17', '192', '192', null, null, '1', '1920100', '广东纤维鉴定检测有限公司', null, 'gdxwjdyxgs.jianla.com', null, null, '0');
INSERT INTO `data_dictionary` VALUES ('195', null, null, '2014-11-12 18:29:55', '192', '192', null, null, '1', '1920100', ' 山东蓝翔瓦挖机机有限公司', null, 'blushit.jianla.com', null, null, '0');
INSERT INTO `data_dictionary` VALUES ('196', null, null, '2014-11-12 18:30:23', '192', '192', null, null, '1', '1920100', '杭州靠谱电子商务有限公司', null, 'service/service_shop.html?id=579', null, null, '0');
INSERT INTO `data_dictionary` VALUES ('197', null, null, '2014-11-12 18:31:04', '192', '192', null, null, '1', '1920100', ' 江苏检科院', null, 'jsjian.jianla.com', null, null, '0');
INSERT INTO `data_dictionary` VALUES ('198', '特定企业的特定服务推荐', null, '2014-11-12 20:00:02', null, null, 'recommend_particular_service', null, '0', '1980000', '特定服务推荐', null, '', null, null, '1');
INSERT INTO `data_dictionary` VALUES ('199', null, null, '2014-11-12 20:26:49', '198', '198', null, null, '1', '1980100', 'ds', null, null, null, null, '0');
INSERT INTO `data_dictionary` VALUES ('200', null, null, '2014-11-12 20:28:02', '198', '198', null, null, '1', '1980100', '31', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('201', null, null, '2014-11-12 20:34:58', '198', '198', null, null, '1', '1980100', '43', null, null, null, null, '0');
INSERT INTO `data_dictionary` VALUES ('202', null, null, '2014-11-12 20:44:47', '198', '198', null, null, '1', '1980100', '29', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('203', null, null, '2014-11-12 20:44:58', '198', '198', null, null, '1', '1980100', '28', null, null, null, null, '0');
INSERT INTO `data_dictionary` VALUES ('204', null, null, '2014-11-12 21:12:04', '198', '198', null, null, '1', '1980100', '29', null, null, null, null, '0');
INSERT INTO `data_dictionary` VALUES ('205', null, null, '2014-11-12 21:34:06', '198', '198', null, null, '1', '1980100', '32', null, null, null, null, '0');
INSERT INTO `data_dictionary` VALUES ('206', null, null, '2014-11-12 21:34:16', '198', '198', null, null, '1', '1980100', '12', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('207', null, null, '2014-11-12 21:37:57', '198', '198', null, null, '1', '1980100', '23', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('208', null, null, '2014-11-12 21:38:26', '198', '198', null, null, '1', '1980100', '29', null, null, null, null, '0');
INSERT INTO `data_dictionary` VALUES ('209', null, null, '2014-11-12 21:38:41', '198', '198', null, null, '1', '1980100', '22', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('210', null, null, '2014-11-12 21:38:58', '198', '198', null, null, '1', '1980100', '20', null, null, null, null, '0');
INSERT INTO `data_dictionary` VALUES ('211', null, null, '2014-11-12 21:39:24', '198', '198', null, null, '1', '1980100', '21', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('212', null, null, '2014-11-12 21:39:42', '198', '198', null, null, '1', '1980100', '22', null, null, null, null, '0');
INSERT INTO `data_dictionary` VALUES ('213', null, null, '2014-11-13 11:33:01', '192', '192', null, null, '1', '1920100', '594', null, null, null, null, '0');
INSERT INTO `data_dictionary` VALUES ('214', null, null, '2014-11-13 11:39:06', '192', '192', null, null, '1', '1920100', '592', null, null, null, null, '0');
INSERT INTO `data_dictionary` VALUES ('215', null, null, '2014-11-13 11:39:14', '192', '192', null, null, '1', '1920100', '594', null, null, null, null, '0');
INSERT INTO `data_dictionary` VALUES ('216', null, null, '2014-11-13 11:39:24', '192', '192', null, null, '1', '1920100', '595', null, null, null, null, '0');
INSERT INTO `data_dictionary` VALUES ('217', null, null, '2014-11-13 11:55:17', '192', '192', null, null, '1', '1920100', '586', null, null, null, null, '0');
INSERT INTO `data_dictionary` VALUES ('218', null, null, '2014-11-13 12:11:21', '192', '192', null, null, '1', '1920100', '584', null, null, null, null, '0');
INSERT INTO `data_dictionary` VALUES ('219', null, null, '2014-11-13 12:14:09', '192', '192', null, null, '1', '1920100', '579', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('220', null, null, '2014-11-13 12:14:22', '192', '192', null, null, '1', '1920100', '581', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('221', null, null, '2014-11-13 12:14:30', '192', '192', null, null, '1', '1920100', '584', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('222', null, null, '2014-11-13 12:14:35', '192', '192', null, null, '1', '1920100', '585', null, null, null, null, '0');
INSERT INTO `data_dictionary` VALUES ('223', null, null, '2014-11-13 12:14:42', '192', '192', null, null, '1', '1920100', '586', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('224', null, null, '2014-11-13 12:14:50', '192', '192', null, null, '1', '1920100', '588', null, null, null, null, '0');
INSERT INTO `data_dictionary` VALUES ('225', null, null, '2014-11-13 12:14:57', '192', '192', null, null, '1', '1920100', '590', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('226', null, null, '2014-11-13 12:15:04', '192', '192', null, null, '1', '1920100', '592', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('227', null, null, '2014-11-13 12:15:16', '192', '192', null, null, '1', '1920100', '594', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('228', null, null, '2014-11-13 12:15:20', '192', '192', null, null, '1', '1920100', '595', null, null, null, null, '0');
INSERT INTO `data_dictionary` VALUES ('229', null, null, '2014-11-13 13:36:10', '198', '198', null, null, '1', '1980100', '33', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('231', '', null, '2014-11-14 14:57:59', null, null, 'recommend_service', null, '0', '2310000', '推荐检测服务', null, '', null, null, '1');
INSERT INTO `data_dictionary` VALUES ('232', '', null, '2014-11-14 15:00:31', null, null, 'quality_control', null, '0', '2320000', '品控工具', null, '', null, null, '1');
INSERT INTO `data_dictionary` VALUES ('233', null, null, '2014-11-14 15:00:46', '231', '231', null, null, '1', '2310100', 'ss', null, null, null, null, '0');
INSERT INTO `data_dictionary` VALUES ('234', null, null, '2014-11-14 15:00:54', '231', '231', null, null, '1', '2310100', '天猫', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('235', null, null, '2014-11-14 15:00:55', '231', '231', null, null, '1', '2310100', '', null, null, null, null, '0');
INSERT INTO `data_dictionary` VALUES ('236', null, null, '2014-11-14 15:01:01', '231', '231', null, null, '1', '2310100', '京东', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('237', null, null, '2014-11-14 15:06:00', '232', '232', null, null, '1', '2320100', 'HS编码查询', null, 'http://baidu.com', null, null, '0');
INSERT INTO `data_dictionary` VALUES ('238', null, null, '2014-11-14 15:10:05', '232', '232', null, null, '1', '2320100', '实验室检测管理系统', null, '', null, null, '0');
INSERT INTO `data_dictionary` VALUES ('239', null, null, '2014-11-14 15:10:27', '231', '231', null, null, '1', '2310100', '入驻拍拍', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('241', null, null, '2014-11-14 15:19:21', '231', '231', null, null, '1', '2310100', '', null, null, null, null, '0');
INSERT INTO `data_dictionary` VALUES ('242', null, null, '2014-11-16 10:35:28', '232', '232', null, null, '1', '2320100', '淘宝质检服务平台', null, 'http://zhijian.taobao.com/', null, null, '0');
INSERT INTO `data_dictionary` VALUES ('243', null, null, '2014-11-16 10:37:25', '232', '232', null, null, '1', '2320100', '京东质检服务平台', null, 'http://fw.jd.com/ser/list.action?page=1&cid=401', null, null, '0');
INSERT INTO `data_dictionary` VALUES ('244', null, null, '2014-11-16 10:38:16', '232', '232', null, null, '1', '2320100', '苏宁质检服务平台', null, 'http://fuwu.suning.com/fuwu/toList.htm?categoryCode=C011014', null, null, '0');
INSERT INTO `data_dictionary` VALUES ('245', null, null, '2014-11-16 10:39:00', '231', '231', null, null, '1', '2310100', '纺织品', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('246', null, null, '2014-11-16 10:39:16', '231', '231', null, null, '1', '2310100', '皮鞋', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('247', null, null, '2014-11-16 10:39:36', '231', '231', null, null, '1', '2310100', '高关注物质', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('248', null, null, '2014-11-16 10:39:48', '231', '231', null, null, '1', '2310100', 'Rohs', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('249', null, null, '2014-11-16 10:39:57', '231', '231', null, null, '1', '2310100', '多环芳烃', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('262', null, null, '2014-11-17 11:51:11', '232', '232', null, null, '1', '2320100', 'CCC检测机构', null, 'http://www.cnca.gov.cn/cnca/rdht/qzxcprz/jcjggljg/images/20080716/5172.htm', null, null, '0');
INSERT INTO `data_dictionary` VALUES ('263', null, null, '2014-11-17 11:51:44', '232', '232', null, null, '1', '2320100', '认证机构名录', null, 'http://www.cnca.gov.cn/cnca/cxzq/rkcx/4424.shtml', null, null, '0');
INSERT INTO `data_dictionary` VALUES ('264', null, null, '2014-11-17 11:52:53', '232', '232', null, null, '1', '2320100', 'CNAS认可机构', null, 'http://219.238.178.49/Acc_Search2.asp?Class=L', null, null, '0');
INSERT INTO `data_dictionary` VALUES ('265', '主营业务--健康体检', null, '2014-11-18 15:17:52', '48', '48', '', null, '1', '480106', '健康体检', null, '', '6', null, '1');
INSERT INTO `data_dictionary` VALUES ('266', '主营业务--工程验收', null, '2014-11-18 15:19:17', '48', '48', '', null, '1', '480105', '工程验收', null, '', '5', null, '1');
INSERT INTO `data_dictionary` VALUES ('267', '', null, '2014-11-18 15:23:02', '14', '12', null, null, '2', '140200', 'IT产品', null, '', null, null, '1');
INSERT INTO `data_dictionary` VALUES ('268', '', null, '2014-11-18 15:32:52', '1', '1', null, null, '1', '10111', '招投标质检', null, '', '11', null, '1');
INSERT INTO `data_dictionary` VALUES ('269', '国家强制性认证CCC型式试验', null, '2014-11-18 15:33:48', '1', '1', '', null, '1', '10113', 'CCC型式检测', null, '', '13', null, '1');
INSERT INTO `data_dictionary` VALUES ('270', '', null, '2014-11-18 15:36:20', '1', '1', null, null, '1', '10112', '商家企业品控', null, '', '12', null, '1');
INSERT INTO `data_dictionary` VALUES ('271', null, null, '2014-11-19 15:46:32', '192', '192', null, null, '1', '1920100', '596', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('272', '用于标明服务和订单的用途', null, '2014-12-16 14:14:37', null, null, 'service_and_order_usage', null, '0', '2720000', '服务及订单用途', null, '', null, null, '1');
INSERT INTO `data_dictionary` VALUES ('274', '', null, '2014-12-16 14:15:46', '272', '272', '', 'jingdong', '1', '2720101', '京东服务', null, 'http://ajianla.com/jd/jdInterface.html', '1', '', '1');
INSERT INTO `data_dictionary` VALUES ('275', '', null, '2015-01-04 09:00:40', '48', '48', null, null, '1', '480100', '计量校准', null, '', null, null, '1');
INSERT INTO `data_dictionary` VALUES ('289', null, null, '2015-01-22 12:44:12', '231', '231', null, null, '1', '2310100', 'asvv', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('290', null, null, '2015-01-22 12:44:25', '231', '231', null, null, '1', '2310100', '鹅鹅鹅', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('291', null, null, '2015-01-22 12:44:28', '231', '231', null, null, '1', '2310100', '热热热', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('292', null, null, '2015-01-22 12:44:31', '231', '231', null, null, '1', '2310100', '434', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('293', null, null, '2015-01-22 12:44:42', '231', '231', null, null, '1', '2310100', '5rr', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('294', null, null, '2015-01-22 12:44:45', '231', '231', null, null, '1', '2310100', 'reer', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('295', null, null, '2015-01-22 13:21:17', '232', '232', null, null, '1', '2320100', '村委会', null, 'http://baidu.com', null, null, '0');
INSERT INTO `data_dictionary` VALUES ('296', null, null, '2015-01-22 13:22:15', '232', '232', null, null, '1', '2320100', '唐河', null, 'http://sina.com', null, null, '0');
INSERT INTO `data_dictionary` VALUES ('297', null, null, '2015-01-22 13:22:32', '232', '232', null, null, '1', '2320100', 'HS编码查询', null, 'http://baidu.com', null, null, '0');
INSERT INTO `data_dictionary` VALUES ('298', '3', null, '2015-01-22 13:40:05', null, null, 'elec_recommend_org', null, '0', '2980000', '机电活跃检测机构', null, '', null, null, '1');
INSERT INTO `data_dictionary` VALUES ('299', '1', null, '2015-01-22 13:40:49', null, null, 'elec_recommend_service', null, '0', '2990000', '机电推荐检测服务', null, '', null, null, '1');
INSERT INTO `data_dictionary` VALUES ('300', '2', null, '2015-01-22 13:42:14', null, null, 'elec_recommend_particular_service', null, '0', '3000000', '机电热卖质检服务	', null, '', null, null, '1');
INSERT INTO `data_dictionary` VALUES ('301', '3', null, '2015-01-22 13:49:18', null, null, 'food_recommend_org', null, '0', '3010000', '食品活跃检测机构', null, '', null, null, '1');
INSERT INTO `data_dictionary` VALUES ('302', '2', null, '2015-01-22 13:50:02', null, null, 'food_recommend_particular_service', null, '0', '3020000', '食品热卖质检服务	', null, '', null, null, '1');
INSERT INTO `data_dictionary` VALUES ('303', '1', null, '2015-01-22 13:51:47', null, null, 'food_recommend_service', null, '0', '3030000', '食品推荐检测服务	', null, '', null, null, '1');
INSERT INTO `data_dictionary` VALUES ('304', '3', null, '2015-01-22 13:52:42', null, null, 'light_recommend_org', null, '0', '3040000', '轻工活跃检测机构', null, '', null, null, '1');
INSERT INTO `data_dictionary` VALUES ('305', '2', null, '2015-01-22 13:53:37', null, null, 'light_recommend_particular_service', null, '0', '3050000', '轻工热卖质检服务	', null, '', null, null, '1');
INSERT INTO `data_dictionary` VALUES ('306', '1', null, '2015-01-22 13:54:26', null, null, 'light_recommend_service', null, '0', '3060000', '轻工推荐检测服务				', null, '', null, null, '1');
INSERT INTO `data_dictionary` VALUES ('333', '', null, '2015-01-22 21:45:46', null, null, 'friendly_link', null, '0', '3330000', '友情链接', null, '', null, null, '1');
INSERT INTO `data_dictionary` VALUES ('334', null, null, '2015-01-22 21:51:24', '333', '333', null, null, '1', '3330100', '百度', null, 'http://baidu.com', null, null, '0');
INSERT INTO `data_dictionary` VALUES ('335', null, null, '2015-01-22 21:51:58', '333', '333', null, null, '1', '3330100', '新浪', null, 'http://sina.com', null, null, '0');
INSERT INTO `data_dictionary` VALUES ('336', null, null, '2015-01-22 21:52:22', '333', '333', null, null, '1', '3330100', '果壳', null, 'http://guokr.com', null, null, '0');
INSERT INTO `data_dictionary` VALUES ('467', null, null, '2015-02-03 13:22:48', '333', '333', null, null, '1', '3330100', '国家质监总局', null, 'http://www.aqsiq.gov.cn/', null, null, '0');
INSERT INTO `data_dictionary` VALUES ('468', null, null, '2015-02-03 13:23:19', '333', '333', null, null, '1', '3330100', '国家标委会', null, 'http://www.sac.gov.cn/', null, null, '0');
INSERT INTO `data_dictionary` VALUES ('469', null, null, '2015-02-03 13:23:42', '333', '333', null, null, '1', '3330100', '国家食药局', null, 'http://www.sfda.gov.cn/WS01/CL0001/', null, null, '0');
INSERT INTO `data_dictionary` VALUES ('470', null, null, '2015-02-03 13:24:03', '333', '333', null, null, '1', '3330100', '国家环保局', null, 'http://www.zhb.gov.cn/', null, null, '0');
INSERT INTO `data_dictionary` VALUES ('471', null, null, '2015-02-03 13:24:20', '333', '333', null, null, '1', '3330100', '工商总局', null, 'http://www.saic.gov.cn/', null, null, '0');
INSERT INTO `data_dictionary` VALUES ('472', null, null, '2015-02-03 13:24:40', '333', '333', null, null, '1', '3330100', '中国消协', null, 'http://www.cca.org.cn/', null, null, '0');
INSERT INTO `data_dictionary` VALUES ('473', null, null, '2015-02-03 13:25:02', '333', '333', null, null, '1', '3330100', '中国质量认证中心', null, 'http://www.cqc.com.cn/chinese/index.htm', null, null, '0');
INSERT INTO `data_dictionary` VALUES ('474', null, null, '2015-02-03 13:25:55', '333', '333', null, null, '1', '3330100', '国家认监委', null, 'http://www.cnca.gov.cn/', null, null, '0');
INSERT INTO `data_dictionary` VALUES ('475', null, null, '2015-02-03 13:26:40', '333', '333', null, null, '1', '3330100', '中国质量新闻网', null, 'http://www.cqn.com.cn/', null, null, '0');
INSERT INTO `data_dictionary` VALUES ('476', '机构【广州纤维产品检测研究院】的旺铺热卖服务', null, '2015-03-17 10:39:33', null, null, 'shop_hot_services@广州纤检', null, '0', '4760000', '机构旺铺热卖服务', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('477', '机构【广州纤维产品检测研究院】的自定义服务类型', null, '2015-03-17 12:55:22', null, null, 'orgnization_custom_service_type@广州纤检', null, '0', '4770000', '自定义服务类型', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('478', '', null, '2015-03-17 12:55:32', '477', '477', null, '', '1', '4770100', '纺织服装检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('479', '', null, '2015-03-17 12:55:40', '477', '477', null, '', '1', '4770100', '鞋类箱包检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('480', '', null, '2015-03-17 12:55:50', '477', '477', null, '', '1', '4770100', '玩具童车检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('481', '', null, '2015-03-17 12:55:57', '477', '477', null, '', '1', '4770100', '环境检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('482', '', null, '2015-03-17 12:56:10', '478', '477', null, '', '2', '4780200', '纺织服装检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('483', '', null, '2015-03-17 12:56:18', '478', '477', null, '', '2', '4780200', '羽绒制品检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('484', '', null, '2015-03-17 12:56:28', '478', '477', null, '', '2', '4780200', '羊绒貂绒制品检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('485', '', null, '2015-03-17 12:56:37', '478', '477', null, '', '2', '4780200', '皮革服装检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('486', '', null, '2015-03-17 12:56:46', '478', '477', null, '', '2', '4780200', '产业用纺织品检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('487', '机构【杭州靠谱检测有限公司】的自定义服务类型', null, '2015-03-17 22:48:32', null, null, 'orgnization_custom_service_type@靠谱检测', null, '0', '4870000', '自定义服务类型', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('488', '', null, '2015-03-17 22:48:44', '487', '487', null, '', '1', '4870100', '纺织服装检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('489', '', null, '2015-03-17 22:48:55', '487', '487', null, '', '1', '4870100', '玩具童车检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('490', '', null, '2015-03-17 22:49:04', '487', '487', null, '', '1', '4870100', '鞋类箱包检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('491', '', null, '2015-03-17 22:49:16', '487', '487', null, '', '1', '4870100', '食品接触材料检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('492', '', null, '2015-03-17 22:49:22', '487', '487', null, '', '1', '4870100', '毒理检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('493', '', null, '2015-03-17 22:49:39', '487', '487', null, '', '1', '4870100', '化妆品检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('494', '', null, '2015-03-17 22:49:51', '487', '487', null, '', '1', '4870100', '一次性卫生用品检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('495', '', null, '2015-03-17 22:50:15', '488', '487', null, '', '2', '4880200', '纺织服装检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('496', '', null, '2015-03-17 22:50:25', '488', '487', null, '', '2', '4880200', '羽绒制品检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('497', '', null, '2015-03-17 22:50:38', '488', '487', null, '', '2', '4880200', '特种纤维制品检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('498', '', null, '2015-03-17 22:50:47', '488', '487', null, '', '2', '4880200', '皮革服装检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('499', '', null, '2015-03-17 22:51:01', '490', '487', null, '', '2', '4900200', '鞋类检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('500', '', null, '2015-03-17 22:51:07', '490', '487', null, '', '2', '4900200', '箱包检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('501', '', null, '2015-03-17 22:51:24', '490', '487', null, '', '2', '4900200', '皮革制品检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('502', '机构【null】的自定义服务类型', null, '2015-03-18 12:52:31', null, null, 'orgnization_custom_service_type@tuvrheinland', null, '0', '5020000', '自定义服务类型', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('503', '', null, '2015-03-18 12:52:51', '502', '502', null, '', '1', '5020100', '纺织服装检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('504', '', null, '2015-03-18 12:53:36', '503', '502', null, '', '2', '5030200', '纺织服装检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('505', '', null, '2015-03-18 12:53:44', '503', '502', null, '', '2', '5030200', '羽绒制品检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('506', '机构【江苏省纺织产品质量监督检验研究院】的自定义服务类型', null, '2015-03-18 20:10:28', null, null, 'orgnization_custom_service_type@江苏纺检', null, '0', '5060000', '自定义服务类型', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('507', '机构【上海天祥质量技术服务有限公司】的自定义服务类型', null, '2015-03-22 11:19:58', null, null, 'orgnization_custom_service_type@ITS天祥', null, '0', '5070000', '自定义服务类型', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('508', '', null, '2015-03-22 11:20:07', '507', '507', null, '', '1', '5070100', '纺织服装检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('509', '', null, '2015-03-22 11:20:18', '507', '507', null, '', '1', '5070100', '食品药业检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('510', '', null, '2015-03-22 11:20:27', '507', '507', null, '', '1', '5070100', '玩具轻工检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('511', '', null, '2015-03-22 11:20:35', '507', '507', null, '', '1', '5070100', '电子电气检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('512', '', null, '2015-03-22 11:20:42', '507', '507', null, '', '1', '5070100', '化学品检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('513', '', null, '2015-03-22 11:21:01', '507', '507', null, '', '1', '5070100', '鞋类箱包检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('514', '机构【浙江省检验检疫科学技术研究院温州分院】的自定义服务类型', null, '2015-03-22 13:53:10', null, null, 'orgnization_custom_service_type@ZAIQ温州分院', null, '0', '5140000', '自定义服务类型', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('515', '', null, '2015-03-22 13:53:18', '514', '514', null, '', '1', '5140100', '鞋类实验室', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('516', '', null, '2015-03-22 13:53:26', '514', '514', null, '', '1', '5140100', '宠物食品实验室', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('517', '机构【浙江省检验检疫科学技术研究院】的自定义服务类型', null, '2015-03-23 12:56:56', null, null, 'orgnization_custom_service_type@浙江省检科院', null, '0', '5170000', '自定义服务类型', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('518', '', null, '2015-03-23 12:57:06', '517', '517', null, '', '1', '5170100', '纺织服装检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('519', '', null, '2015-03-23 12:57:20', '517', '517', null, '', '1', '5170100', '鞋类箱包检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('520', '', null, '2015-03-23 12:57:35', '517', '517', null, '', '1', '5170100', '玩具轻工检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('521', '', null, '2015-03-23 12:57:54', '518', '517', null, '', '2', '5180200', '普通纺织品检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('522', '', null, '2015-03-23 12:58:06', '518', '517', null, '', '2', '5180200', '皮革服装检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('523', '', null, '2015-03-23 12:58:14', '518', '517', null, '', '2', '5180200', '羽绒服装检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('524', '', null, '2015-03-23 12:58:23', '518', '517', null, '', '2', '5180200', '特种纤维检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('525', '', null, '2015-03-23 12:58:36', '519', '517', null, '', '2', '5190200', '鞋类检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('526', '', null, '2015-03-23 12:58:43', '519', '517', null, '', '2', '5190200', '箱包检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('527', '', null, '2015-03-23 12:58:53', '519', '517', null, '', '2', '5190200', '皮具检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('528', '', null, '2015-03-23 12:59:03', '520', '517', null, '', '2', '5200200', '玩具检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('529', '', null, '2015-03-23 12:59:13', '520', '517', null, '', '2', '5200200', '童车检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('530', '', null, '2015-03-23 12:59:22', '520', '517', null, '', '2', '5200200', '食品接触材料检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('531', '', null, '2015-03-23 12:59:33', '520', '517', null, '', '2', '5200200', '一次性卫生用品检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('532', '对接淘宝服务平台的类型标识', null, '2015-03-24 20:37:54', '272', '272', null, 'taobao', '1', '2720102', '淘宝服务', null, 'http://ajianla.com/tb/tbInterface.html', '2', '', '1');
INSERT INTO `data_dictionary` VALUES ('533', '机构【广东产品质量监督检验研究院】的旺铺热卖服务', null, '2015-03-31 14:14:02', null, null, 'shop_hot_services@广东质检院', null, '0', '5330000', '机构旺铺热卖服务', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('534', '机构【北京远东正大商品检验有限公司】的自定义服务类型', null, '2015-04-07 11:51:21', null, null, 'orgnization_custom_service_type@远东正大检验', null, '0', '5340000', '自定义服务类型', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('535', null, null, '2015-04-07 20:47:58', '306', '306', null, null, '1', '3060100', '京东质检报告', null, null, null, null, '0');
INSERT INTO `data_dictionary` VALUES ('536', null, null, '2015-04-07 20:48:09', '306', '306', null, null, '1', '3060100', '天猫质检报告', null, null, null, null, '0');
INSERT INTO `data_dictionary` VALUES ('537', null, null, '2015-04-07 20:48:18', '306', '306', null, null, '1', '3060100', '聚划算质检', null, null, null, null, '0');
INSERT INTO `data_dictionary` VALUES ('538', null, null, '2015-04-07 20:48:30', '306', '306', null, null, '1', '3060100', '皮鞋', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('539', null, null, '2015-04-07 20:48:38', '306', '306', null, null, '1', '3060100', '服装', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('540', null, null, '2015-04-07 20:49:01', '306', '306', null, null, '1', '3060100', '纺织品', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('541', null, null, '2015-04-07 20:49:08', '306', '306', null, null, '1', '3060100', 'GB 18401', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('542', null, null, '2015-04-07 20:49:15', '306', '306', null, null, '1', '3060100', '安全卫生', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('543', null, null, '2015-04-07 20:49:33', '306', '306', null, null, '1', '3060100', '旅游鞋', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('544', null, null, '2015-04-07 20:49:36', '306', '306', null, null, '1', '3060100', '休闲鞋', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('545', null, null, '2015-04-07 20:49:41', '306', '306', null, null, '1', '3060100', '运动鞋', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('546', null, null, '2015-04-07 20:49:45', '306', '306', null, null, '1', '3060100', '雪地靴', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('547', null, null, '2015-04-07 20:49:49', '306', '306', null, null, '1', '3060100', '防护鞋', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('548', null, null, '2015-04-07 20:49:57', '306', '306', null, null, '1', '3060100', '京东质检', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('549', null, null, '2015-04-07 20:50:01', '306', '306', null, null, '1', '3060100', '天猫质检', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('550', null, null, '2015-04-07 20:50:06', '306', '306', null, null, '1', '3060100', '聚划算质检', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('551', null, null, '2015-04-07 20:50:16', '306', '306', null, null, '1', '3060100', '纤维成分', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('552', null, null, '2015-04-07 20:50:25', '306', '306', null, null, '1', '3060100', '童装', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('553', null, null, '2015-04-07 20:50:53', '306', '306', null, null, '1', '3060100', '背提包', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('554', null, null, '2015-04-07 20:50:57', '306', '306', null, null, '1', '3060100', '钱包', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('555', null, null, '2015-04-07 20:51:06', '306', '306', null, null, '1', '3060100', '票夹', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('556', null, null, '2015-04-07 20:51:16', '306', '306', null, null, '1', '3060100', '腰带', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('557', null, null, '2015-04-07 20:51:20', '306', '306', null, null, '1', '3060100', '皮带', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('558', null, null, '2015-04-07 20:52:14', '304', '304', null, null, '1', '3040100', '32', null, null, null, null, '0');
INSERT INTO `data_dictionary` VALUES ('559', null, null, '2015-04-07 20:52:31', '305', '305', null, null, '1', '3050100', '32', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('560', null, null, '2015-04-07 20:52:53', '305', '305', null, null, '1', '3050100', '24', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('561', null, null, '2015-04-07 20:53:14', '305', '305', null, null, '1', '3050100', '10', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('562', null, null, '2015-04-07 20:53:34', '305', '305', null, null, '1', '3050100', '9', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('563', null, null, '2015-04-07 20:53:45', '305', '305', null, null, '1', '3050100', '8', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('564', null, null, '2015-04-07 20:54:32', '305', '305', null, null, '1', '3050100', '12', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('565', null, null, '2015-04-07 20:54:52', '305', '305', null, null, '1', '3050100', '6', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('566', null, null, '2015-04-07 20:55:10', '304', '304', null, null, '1', '3040100', '5', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('567', null, null, '2015-04-07 20:55:13', '305', '305', null, null, '1', '3050100', '5', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('568', null, null, '2015-04-07 20:55:41', '305', '305', null, null, '1', '3050100', '15', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('569', null, null, '2015-04-07 20:56:04', '305', '305', null, null, '1', '3050100', '25', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('570', null, null, '2015-04-07 20:58:08', '304', '304', null, null, '1', '3040100', '3', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('571', null, null, '2015-04-07 20:58:11', '304', '304', null, null, '1', '3040100', '4', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('572', null, null, '2015-04-07 20:58:19', '304', '304', null, null, '1', '3040100', '7', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('573', null, null, '2015-04-07 20:58:22', '304', '304', null, null, '1', '3040100', '8', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('574', null, null, '2015-04-07 20:58:32', '304', '304', null, null, '1', '3040100', '11', null, null, null, null, '0');
INSERT INTO `data_dictionary` VALUES ('575', null, null, '2015-04-07 20:58:41', '304', '304', null, null, '1', '3040100', '13', null, null, null, null, '0');
INSERT INTO `data_dictionary` VALUES ('576', null, null, '2015-04-07 20:58:51', '304', '304', null, null, '1', '3040100', '15', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('577', null, null, '2015-04-07 20:59:24', '304', '304', null, null, '1', '3040100', '16', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('578', null, null, '2015-04-07 20:59:33', '304', '304', null, null, '1', '3040100', '20', null, null, null, null, '0');
INSERT INTO `data_dictionary` VALUES ('579', null, null, '2015-04-07 20:59:40', '304', '304', null, null, '1', '3040100', '21', null, null, null, null, '0');
INSERT INTO `data_dictionary` VALUES ('580', null, null, '2015-04-07 20:59:45', '304', '304', null, null, '1', '3040100', '22', null, null, null, null, '0');
INSERT INTO `data_dictionary` VALUES ('581', null, null, '2015-04-07 21:00:13', '304', '304', null, null, '1', '3040100', '30', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('582', null, null, '2015-04-07 21:00:20', '304', '304', null, null, '1', '3040100', '21', null, null, null, null, '0');
INSERT INTO `data_dictionary` VALUES ('583', null, null, '2015-04-07 21:00:28', '304', '304', null, null, '1', '3040100', '31', null, null, null, null, '0');
INSERT INTO `data_dictionary` VALUES ('584', null, null, '2015-04-07 21:00:43', '304', '304', null, null, '1', '3040100', '36', null, null, null, null, '0');
INSERT INTO `data_dictionary` VALUES ('585', '机构【苏州检验检疫局技术中心】的自定义服务类型', null, '2015-04-10 15:26:37', null, null, 'orgnization_custom_service_type@苏州国检检测', null, '0', '5850000', '自定义服务类型', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('586', '', null, '2015-04-10 15:26:47', '585', '585', null, '', '1', '5850100', '化妆品检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('587', '', null, '2015-04-10 15:27:01', '585', '585', null, '', '1', '5850100', '一次性卫生用品检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('588', '', null, '2015-04-10 15:27:16', '585', '585', null, '', '1', '5850100', '纺织品检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('589', '机构【诺安检测服务有限公司】的自定义服务类型', null, '2015-04-11 14:37:29', null, null, 'orgnization_custom_service_type@诺安检测', null, '0', '5890000', '自定义服务类型', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('590', null, null, '2015-04-17 15:32:39', '304', '304', null, null, '1', '3040100', '40', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('591', '', null, '2015-04-18 20:58:19', '15', '12', null, '', '2', '150200', '饮料类', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('592', '', null, '2015-04-18 20:58:38', '15', '12', null, '', '2', '150200', '酒类', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('593', '', null, '2015-04-18 20:58:55', '15', '12', null, '', '2', '150200', '其他食品', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('594', '', null, '2015-04-18 20:59:41', '15', '12', '', '', '2', '150201', '食品大类', null, '', '1', '', '1');
INSERT INTO `data_dictionary` VALUES ('595', '', null, '2015-04-18 21:00:17', '15', '12', null, '', '2', '150200', '保健食品', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('596', '', null, '2015-04-18 21:02:17', '15', '12', null, '', '2', '150200', '宠物食品', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('597', '', null, '2015-04-18 21:02:35', '15', '12', null, '', '2', '150200', '动物饲料', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('598', '', null, '2015-04-18 21:03:16', '15', '12', null, '', '2', '150200', '食品接触材料', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('599', '', null, '2015-04-18 21:03:44', '13', '12', null, '', '2', '130200', '轻工产品大类', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('600', '', null, '2015-04-18 21:04:40', '13', '12', '', '', '2', '130200', '家纺家居家具', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('601', '', null, '2015-04-18 21:04:51', '13', '12', null, '', '2', '130200', '装饰装修材料', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('602', '', null, '2015-04-18 21:05:06', '13', '12', null, '', '2', '130200', '汽车内饰用品', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('603', '', null, '2015-04-23 08:07:18', '502', '502', null, '', '1', '5020100', '家用电子电器检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('604', '', null, '2015-04-23 08:07:29', '502', '502', null, '', '1', '5020100', '食品饮料检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('605', '', null, '2015-04-23 08:07:48', '502', '502', null, '', '1', '5020100', '鞋类箱包检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('606', '', null, '2015-04-23 08:08:12', '502', '502', null, '', '1', '5020100', '家纺家居检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('607', '机构【广东产品质量监督检验研究院】的自定义服务类型', null, '2015-04-23 09:14:25', null, null, 'orgnization_custom_service_type@广东质检院', null, '0', '6070000', '自定义服务类型', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('608', '', null, '2015-04-23 10:44:22', '13', '12', null, '', '2', '130200', '一次性卫生用品', null, '', null, '', '0');
INSERT INTO `data_dictionary` VALUES ('609', '机构【广州工业微生物检测中心】的自定义服务类型', null, '2015-04-30 15:10:54', null, null, 'orgnization_custom_service_type@广州工业微生物检测中心', null, '0', '6090000', '自定义服务类型', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('610', '', null, '2015-04-30 15:11:05', '609', '609', null, '', '1', '6090100', '化妆品检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('611', '', null, '2015-04-30 15:11:12', '609', '609', null, '', '1', '6090100', '食品检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('612', '机构【广州工业微生物检测中心】的旺铺热卖服务', null, '2015-05-04 13:57:03', null, null, 'shop_hot_services@广州工业微生物检测中心', null, '0', '6120000', '机构旺铺热卖服务', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('613', '机构【常州进出口工业及消费品安全检测中心】的自定义服务类型', null, '2015-05-08 08:41:50', null, null, 'orgnization_custom_service_type@常州商检', null, '0', '6130000', '自定义服务类型', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('614', '', null, '2015-05-08 08:42:29', '613', '613', null, '', '1', '6130100', '危险品分类鉴定', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('615', '', null, '2015-05-08 08:42:39', '613', '613', null, '', '1', '6130100', '食品接触材料检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('616', '', null, '2015-05-08 08:42:47', '613', '613', null, '', '1', '6130100', '消费品检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('617', '机构【杭州靠谱检测有限公司】的旺铺热卖服务', null, '2015-05-08 09:17:47', null, null, 'shop_hot_services@靠谱检测', null, '0', '6170000', '机构旺铺热卖服务', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('618', '机构【谱尼测试】的自定义服务类型', null, '2015-05-08 11:27:18', null, null, 'orgnization_custom_service_type@谱尼测试', null, '0', '6180000', '自定义服务类型', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('619', '', null, '2015-05-08 11:27:26', '618', '618', null, '', '1', '6180100', '化妆品检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('620', '', null, '2015-05-08 11:27:35', '618', '618', null, '', '1', '6180100', '一次性卫生用品检测', null, '', null, '', '1');
INSERT INTO `data_dictionary` VALUES ('621', '机构【谱尼测试】的旺铺热卖服务', null, '2015-05-11 09:27:14', null, null, 'shop_hot_services@谱尼测试', null, '0', '6210000', '机构旺铺热卖服务', null, null, null, null, '1');
INSERT INTO `data_dictionary` VALUES ('622', '', null, '2015-06-17 16:13:22', '272', '272', null, 'suning', '1', '2720103', '苏宁服务', null, 'http://localhost:8080/sun/sunInterface.html', '3', '', '1');

-- ----------------------------
-- Table structure for `tdepartment`
-- ----------------------------
DROP TABLE IF EXISTS `tdepartment`;
CREATE TABLE `tdepartment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '机构下属的部门表',
  `name` varchar(50) NOT NULL COMMENT '部门名称',
  `description` varchar(255) DEFAULT NULL COMMENT '部门描述',
  `parent_id` int(11) DEFAULT NULL COMMENT '【不启用，目前支持一级部门】上级部门id',
  `org_id` int(11) NOT NULL COMMENT '所属机构的id',
  `create_time` date NOT NULL COMMENT '部门创建日期',
  `create_by` int(11) NOT NULL COMMENT '部门创建者id',
  `status` tinyint(4) NOT NULL COMMENT '部门状态：-1->删除；0->不可用；1->可用',
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`),
  KEY `org_id` (`org_id`),
  KEY `create_by` (`create_by`),
  CONSTRAINT `tdepartment_ibfk_3` FOREIGN KEY (`create_by`) REFERENCES `tmember` (`id`),
  CONSTRAINT `tdepartment_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `tdepartment` (`id`),
  CONSTRAINT `tdepartment_ibfk_2` FOREIGN KEY (`org_id`) REFERENCES `torganization` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tdepartment
-- ----------------------------

-- ----------------------------
-- Table structure for `tdriver`
-- ----------------------------
DROP TABLE IF EXISTS `tdriver`;
CREATE TABLE `tdriver` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '驾驶员基本信息',
  `name` varchar(50) NOT NULL COMMENT '驾驶员姓名',
  `birth_place` varchar(50) NOT NULL COMMENT '籍贯',
  `birth` date DEFAULT NULL COMMENT '出生日期',
  `gender` tinyint(4) DEFAULT NULL COMMENT '性别：0->女；1->男',
  `sfz_no` varchar(20) NOT NULL COMMENT '身份证号',
  `photo` varchar(255) DEFAULT NULL COMMENT '照片',
  `address` varchar(255) DEFAULT NULL COMMENT '家庭地址',
  `postcode` varchar(10) DEFAULT NULL COMMENT '邮编',
  `mobile` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) DEFAULT NULL COMMENT '联系email',
  `org_id` int(11) NOT NULL COMMENT '所属车企id',
  `emergency_contact` varchar(50) DEFAULT NULL COMMENT '紧急家庭联系人姓名',
  `emergency_contact_mobile` varchar(50) DEFAULT NULL COMMENT '紧急联系人电话',
  `drive_licence_no` varchar(20) DEFAULT NULL COMMENT '驾驶证号',
  `drive_lincence_photo` varchar(255) DEFAULT NULL COMMENT '驾驶证照片',
  `drive_licence_start_time` date DEFAULT NULL COMMENT '驾驶证生效日期',
  `drive_licence_end_time` date DEFAULT NULL COMMENT '驾驶证失效日期',
  `safe_centificate_no` varchar(20) DEFAULT NULL COMMENT '安全证书号',
  `safe_centificate_photo` varchar(255) DEFAULT NULL COMMENT '安全证书照片',
  `safe_centificate_start_time` date DEFAULT NULL COMMENT '安全证书生效日期',
  `safe_centificate_end_time` date DEFAULT NULL COMMENT '安全证书失效日期',
  `status` tinyint(4) NOT NULL COMMENT '驾驶员状态：-1->删除；0->不可用；1->可用 (可能会增加状态）',
  PRIMARY KEY (`id`),
  KEY `org_id` (`org_id`),
  CONSTRAINT `tdriver_ibfk_1` FOREIGN KEY (`org_id`) REFERENCES `torganization` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tdriver
-- ----------------------------

-- ----------------------------
-- Table structure for `tdriver_honour`
-- ----------------------------
DROP TABLE IF EXISTS `tdriver_honour`;
CREATE TABLE `tdriver_honour` (
  `id` int(11) NOT NULL COMMENT '驾驶员荣誉记录',
  `driver_id` int(11) NOT NULL COMMENT '驾驶员id',
  `honour_type_id` bigint(20) NOT NULL COMMENT '预定义的荣誉类型',
  `honour_time` date DEFAULT NULL COMMENT '荣誉授予时间',
  `honour_org_id` int(11) DEFAULT NULL COMMENT '荣誉授予机构',
  `remark` varchar(255) DEFAULT NULL COMMENT '荣誉备注',
  `status` tinyint(4) NOT NULL COMMENT '状态：-1->删除；0->待审核；1->可用',
  PRIMARY KEY (`id`),
  KEY `driver_id` (`driver_id`),
  KEY `honour_org_id` (`honour_org_id`),
  CONSTRAINT `tdriver_honour_ibfk_2` FOREIGN KEY (`honour_org_id`) REFERENCES `torganization` (`id`),
  CONSTRAINT `tdriver_honour_ibfk_1` FOREIGN KEY (`driver_id`) REFERENCES `tdriver` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tdriver_honour
-- ----------------------------

-- ----------------------------
-- Table structure for `tdriver_insurance_compensation`
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
  `status` tinyint(4) NOT NULL COMMENT '状态：-1->删除；0->待审核；1->可用',
  PRIMARY KEY (`id`),
  KEY `driver_id` (`driver_id`),
  CONSTRAINT `tdriver_insurance_compensation_ibfk_1` FOREIGN KEY (`driver_id`) REFERENCES `tdriver` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tdriver_insurance_compensation
-- ----------------------------

-- ----------------------------
-- Table structure for `tdriver_reward`
-- ----------------------------
DROP TABLE IF EXISTS `tdriver_reward`;
CREATE TABLE `tdriver_reward` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '驾驶员奖惩',
  `driver_id` int(11) NOT NULL COMMENT '驾驶员id',
  `reward_type_id` bigint(20) NOT NULL COMMENT '预定义的奖惩id',
  `reward_time` date DEFAULT NULL COMMENT '奖惩时间',
  `reward_org_id` int(11) DEFAULT NULL COMMENT '奖惩机构',
  `remark` varchar(255) DEFAULT NULL COMMENT '奖惩备注',
  `status` tinyint(4) NOT NULL COMMENT '状态：-1->删除；0->待审核；1->可用',
  PRIMARY KEY (`id`),
  KEY `driver_id` (`driver_id`),
  KEY `reward_org_id` (`reward_org_id`),
  CONSTRAINT `tdriver_reward_ibfk_2` FOREIGN KEY (`reward_org_id`) REFERENCES `torganization` (`id`),
  CONSTRAINT `tdriver_reward_ibfk_1` FOREIGN KEY (`driver_id`) REFERENCES `tdriver` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tdriver_reward
-- ----------------------------

-- ----------------------------
-- Table structure for `tdriver_traffic_violation`
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
  `status` tinyint(4) NOT NULL COMMENT '状态：-1->删除；0->待审核；1->可用',
  PRIMARY KEY (`id`),
  KEY `driver_id` (`driver_id`),
  CONSTRAINT `tdriver_traffic_violation_ibfk_1` FOREIGN KEY (`driver_id`) REFERENCES `tdriver` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tdriver_traffic_violation
-- ----------------------------

-- ----------------------------
-- Table structure for `tdriver_work_history`
-- ----------------------------
DROP TABLE IF EXISTS `tdriver_work_history`;
CREATE TABLE `tdriver_work_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '驾驶员工作经历',
  `driver_id` int(11) NOT NULL COMMENT '驾驶员id',
  `start_date` date DEFAULT NULL COMMENT '经历开始日期',
  `end_date` date DEFAULT NULL COMMENT '经历截止日期',
  `org_id` int(11) DEFAULT NULL COMMENT '所在车企id',
  `org_name` varchar(50) DEFAULT NULL COMMENT '所在车企名字',
  `remark` varchar(255) DEFAULT NULL COMMENT '经历备注',
  `level` int(11) DEFAULT NULL COMMENT '经历等级-以备分级查询',
  `status` tinyint(4) NOT NULL COMMENT '状态：-1->删除；0->待审核；1->可用',
  PRIMARY KEY (`id`),
  KEY `driver_id` (`driver_id`),
  KEY `org_id` (`org_id`),
  CONSTRAINT `tdriver_work_history_ibfk_2` FOREIGN KEY (`org_id`) REFERENCES `torganization` (`id`),
  CONSTRAINT `tdriver_work_history_ibfk_1` FOREIGN KEY (`driver_id`) REFERENCES `tdriver` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tdriver_work_history
-- ----------------------------

-- ----------------------------
-- Table structure for `texam`
-- ----------------------------
DROP TABLE IF EXISTS `texam`;
CREATE TABLE `texam` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '考试表',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `description` varchar(255) DEFAULT NULL COMMENT '描述简介',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `org_id` int(11) NOT NULL COMMENT '创建的机构id',
  `paper_id` int(11) NOT NULL COMMENT '考试所使用的试卷id',
  `signup_start_time` datetime DEFAULT NULL COMMENT '报名开始时间',
  `signup_end_time` datetime DEFAULT NULL COMMENT '报名截止时间',
  `exam_start_time` datetime DEFAULT NULL COMMENT '考试开始时间',
  `exam_end_time` datetime DEFAULT NULL COMMENT '考试结束时间',
  `status` tinyint(4) NOT NULL COMMENT '考试状态',
  PRIMARY KEY (`id`),
  KEY `org_id` (`org_id`),
  KEY `paper_id` (`paper_id`),
  CONSTRAINT `texam_ibfk_2` FOREIGN KEY (`paper_id`) REFERENCES `tpaper` (`id`),
  CONSTRAINT `texam_ibfk_1` FOREIGN KEY (`org_id`) REFERENCES `torganization` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of texam
-- ----------------------------

-- ----------------------------
-- Table structure for `texam_result`
-- ----------------------------
DROP TABLE IF EXISTS `texam_result`;
CREATE TABLE `texam_result` (
  `id` int(11) NOT NULL,
  `exam_id` int(11) NOT NULL,
  `driver_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `sequence` int(11) NOT NULL,
  `answer` varchar(500) NOT NULL,
  `submit_time` datetime NOT NULL,
  `marks` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `exam_id` (`exam_id`),
  KEY `driver_id` (`driver_id`),
  KEY `question_id` (`question_id`),
  CONSTRAINT `texam_result_ibfk_3` FOREIGN KEY (`question_id`) REFERENCES `tquestion` (`id`),
  CONSTRAINT `texam_result_ibfk_1` FOREIGN KEY (`exam_id`) REFERENCES `texam` (`id`),
  CONSTRAINT `texam_result_ibfk_2` FOREIGN KEY (`driver_id`) REFERENCES `tdriver` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of texam_result
-- ----------------------------

-- ----------------------------
-- Table structure for `texam_signup`
-- ----------------------------
DROP TABLE IF EXISTS `texam_signup`;
CREATE TABLE `texam_signup` (
  `id` int(11) NOT NULL,
  `exam_id` int(11) NOT NULL,
  `driver_id` int(11) NOT NULL,
  `signup_time` datetime NOT NULL,
  `final_score` double DEFAULT NULL,
  `certificate_no` varchar(50) DEFAULT NULL,
  `certificate_photo` varchar(255) DEFAULT NULL,
  `status` tinyint(4) NOT NULL COMMENT '驾驶员考试状态',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `exam_id` (`exam_id`),
  KEY `driver_id` (`driver_id`),
  CONSTRAINT `texam_signup_ibfk_2` FOREIGN KEY (`driver_id`) REFERENCES `tdriver` (`id`),
  CONSTRAINT `texam_signup_ibfk_1` FOREIGN KEY (`exam_id`) REFERENCES `texam` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of texam_signup
-- ----------------------------

-- ----------------------------
-- Table structure for `tmember`
-- ----------------------------
DROP TABLE IF EXISTS `tmember`;
CREATE TABLE `tmember` (
  `id` int(11) NOT NULL COMMENT '机构员工表',
  `name` varchar(50) DEFAULT NULL COMMENT '员工姓名',
  `org_id` int(11) DEFAULT NULL COMMENT '所属机构id',
  `department_id` int(11) DEFAULT NULL COMMENT '所属部门id',
  `login_name` varchar(50) NOT NULL COMMENT '登录名',
  `password` varchar(50) NOT NULL COMMENT '登录密码',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` int(11) DEFAULT NULL COMMENT '创建人',
  `status` tinyint(4) NOT NULL COMMENT '状态:-1->删除；0->不可用；1->可用',
  PRIMARY KEY (`id`),
  KEY `org_id` (`org_id`),
  KEY `department_id` (`department_id`),
  KEY `create_by` (`create_by`),
  CONSTRAINT `tmember_ibfk_3` FOREIGN KEY (`create_by`) REFERENCES `tmember` (`id`),
  CONSTRAINT `tmember_ibfk_1` FOREIGN KEY (`org_id`) REFERENCES `torganization` (`id`),
  CONSTRAINT `tmember_ibfk_2` FOREIGN KEY (`department_id`) REFERENCES `tdepartment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tmember
-- ----------------------------

-- ----------------------------
-- Table structure for `torganization`
-- ----------------------------
DROP TABLE IF EXISTS `torganization`;
CREATE TABLE `torganization` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '组织机构主表',
  `name` varchar(50) NOT NULL COMMENT '机构名称',
  `short_name` varchar(20) DEFAULT NULL COMMENT '机构简称',
  `description` varchar(500) DEFAULT NULL COMMENT '机构描述',
  `parent_id` int(11) DEFAULT NULL COMMENT '当前机构所属的机构id',
  `link_person_id` int(11) DEFAULT NULL COMMENT '机构联系人id',
  `tel` varchar(50) DEFAULT NULL COMMENT '机构联系电话',
  `email` varchar(50) DEFAULT NULL COMMENT '机构联系email',
  `type` tinyint(4) NOT NULL COMMENT '机构类型：0->省水泥办；1->市水泥办；2->县区水泥办；3->车企',
  `status` tinyint(4) NOT NULL COMMENT '机构状态：-1->删除；0->不可用；1->可用',
  PRIMARY KEY (`id`),
  KEY `organization_organization_FK1` (`parent_id`),
  KEY `link_person_id` (`link_person_id`),
  CONSTRAINT `torganization_ibfk_2` FOREIGN KEY (`link_person_id`) REFERENCES `tmember` (`id`),
  CONSTRAINT `torganization_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `torganization` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织机构主表 的表';

-- ----------------------------
-- Records of torganization
-- ----------------------------

-- ----------------------------
-- Table structure for `torg_basic_info`
-- ----------------------------
DROP TABLE IF EXISTS `torg_basic_info`;
CREATE TABLE `torg_basic_info` (
  `id` int(11) NOT NULL COMMENT '机构的基本信息',
  `org_id` int(11) NOT NULL,
  `date_of_create` date NOT NULL COMMENT '机构设立时间',
  `legal_person` varchar(50) NOT NULL COMMENT '机构法人/负责人',
  `business_license` varchar(50) DEFAULT NULL COMMENT '工商执照号',
  `business_license_photo` varchar(255) DEFAULT NULL COMMENT '工商执照图片',
  `capital` double DEFAULT NULL COMMENT '总注册资本',
  `tax_code` varchar(50) DEFAULT NULL COMMENT '机构税号',
  `tax_code_photo` varchar(255) DEFAULT NULL COMMENT '机构税号图片',
  `credit_code` varchar(50) DEFAULT NULL COMMENT '机构信用证号',
  `credit_code_photo` varchar(255) DEFAULT NULL COMMENT '机构信用证照片',
  `org_code` varchar(50) DEFAULT NULL COMMENT '机构组织代码',
  `org_code_photo` varchar(255) DEFAULT NULL COMMENT '机构组织代码证照片',
  `safe_production_licence_code` varchar(50) DEFAULT NULL COMMENT '安全生产许可证代码',
  `safe_production_licence_photo` varchar(255) DEFAULT NULL COMMENT '安全生产许可证照片',
  PRIMARY KEY (`id`),
  KEY `org_id` (`org_id`),
  CONSTRAINT `torg_basic_info_ibfk_1` FOREIGN KEY (`org_id`) REFERENCES `torganization` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of torg_basic_info
-- ----------------------------

-- ----------------------------
-- Table structure for `tpaper`
-- ----------------------------
DROP TABLE IF EXISTS `tpaper`;
CREATE TABLE `tpaper` (
  `id` int(11) NOT NULL COMMENT '试卷',
  `name` varchar(50) NOT NULL COMMENT '试卷名称',
  `description` varchar(255) DEFAULT NULL COMMENT '试卷描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `org_id` int(11) NOT NULL COMMENT '创建机构',
  `difficulty` double DEFAULT NULL COMMENT '试卷难度',
  `type` tinyint(4) NOT NULL COMMENT '试卷生成方式：0->一人一卷(答题时生成）；1->众人同卷（考试前提前生成）',
  `status` tinyint(4) NOT NULL COMMENT '试卷状态',
  PRIMARY KEY (`id`),
  KEY `org_id` (`org_id`),
  CONSTRAINT `tpaper_ibfk_1` FOREIGN KEY (`org_id`) REFERENCES `torganization` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tpaper
-- ----------------------------

-- ----------------------------
-- Table structure for `tpaper_design`
-- ----------------------------
DROP TABLE IF EXISTS `tpaper_design`;
CREATE TABLE `tpaper_design` (
  `id` int(11) NOT NULL COMMENT '试卷设计表',
  `paper_id` int(11) NOT NULL COMMENT '试卷id',
  `question_subject_id` bigint(20) NOT NULL COMMENT '试题内容分类id',
  `question_type_id` bigint(20) NOT NULL COMMENT '试题题型id',
  `num` int(11) NOT NULL COMMENT '试题数量',
  PRIMARY KEY (`id`),
  KEY `paper_id` (`paper_id`),
  CONSTRAINT `tpaper_design_ibfk_1` FOREIGN KEY (`paper_id`) REFERENCES `tpaper` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tpaper_design
-- ----------------------------

-- ----------------------------
-- Table structure for `tpaper_list`
-- ----------------------------
DROP TABLE IF EXISTS `tpaper_list`;
CREATE TABLE `tpaper_list` (
  `id` int(11) NOT NULL,
  `paper_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `sequence` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `paper_id` (`paper_id`),
  KEY `question_id` (`question_id`),
  CONSTRAINT `tpaper_list_ibfk_2` FOREIGN KEY (`question_id`) REFERENCES `tquestion` (`id`),
  CONSTRAINT `tpaper_list_ibfk_1` FOREIGN KEY (`paper_id`) REFERENCES `tpaper` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tpaper_list
-- ----------------------------

-- ----------------------------
-- Table structure for `tquestion`
-- ----------------------------
DROP TABLE IF EXISTS `tquestion`;
CREATE TABLE `tquestion` (
  `id` int(11) NOT NULL COMMENT '题库',
  `content` varchar(500) NOT NULL COMMENT '题干内容',
  `pic1` varchar(255) DEFAULT NULL COMMENT '题干中的图一',
  `pic2` varchar(255) DEFAULT NULL COMMENT '题干中的图二',
  `pic3` varchar(255) DEFAULT NULL COMMENT '题干中的图三',
  `subject_id` bigint(20) NOT NULL COMMENT '题目的内容分类',
  `type_id` bigint(20) NOT NULL COMMENT '题型分类',
  `answer` varchar(500) NOT NULL COMMENT '参考答案',
  `marks` double NOT NULL COMMENT '题目分数',
  `difficulty` tinyint(4) DEFAULT NULL COMMENT '题目难度，1->易；2->中；3->难',
  `status` tinyint(4) NOT NULL COMMENT '题目状态',
  `remark` varchar(255) DEFAULT NULL COMMENT '题目备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `operator_id` int(11) DEFAULT NULL COMMENT '创建人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tquestion
-- ----------------------------

-- ----------------------------
-- Table structure for `tquestion_items`
-- ----------------------------
DROP TABLE IF EXISTS `tquestion_items`;
CREATE TABLE `tquestion_items` (
  `id` int(11) NOT NULL COMMENT '题目的选项',
  `question_id` int(11) NOT NULL COMMENT '对应的题目id',
  `sequence` tinyint(4) NOT NULL COMMENT '序号',
  `content` varchar(100) NOT NULL COMMENT '内容',
  `pic` varchar(255) DEFAULT NULL COMMENT '题目选项中的图片',
  PRIMARY KEY (`id`),
  KEY `question_id` (`question_id`),
  CONSTRAINT `tquestion_items_ibfk_1` FOREIGN KEY (`question_id`) REFERENCES `tquestion` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tquestion_items
-- ----------------------------

-- 修改了一个字段名称 更符合类中属性的命名 -dxb -2016年8月9日11:18:09
ALTER TABLE `driverhr`.`torg_basic_info` 
CHANGE COLUMN `date_of_create` `create_date` DATE NOT NULL COMMENT '机构设立时间' ;

-- 司机增加了密码字段 登录名暂且用手机号 -dxb -2016年8月10日14:32:18
ALTER TABLE `driverhr`.`tdriver` 
ADD COLUMN `password` VARCHAR(255) NOT NULL AFTER `mobile`;



-- 考试中的 paper_id not null应该取消 原因：并不是所有考试都固定试卷的 -dxb -2016年8月11日09:45:24
ALTER TABLE `driverhr`.`texam` 
DROP FOREIGN KEY `texam_ibfk_2`;
ALTER TABLE `driverhr`.`texam` 
CHANGE COLUMN `paper_id` `paper_id` INT(11) NULL COMMENT '考试所使用的试卷id' ;
ALTER TABLE `driverhr`.`texam` 
ADD CONSTRAINT `texam_ibfk_2`
  FOREIGN KEY (`paper_id`)
  REFERENCES `driverhr`.`tpaper` (`id`);
  
  
-- 给职员加上身份证字段   -dxb -2016年8月11日09:52:53
ALTER TABLE `driverhr`.`tmember` 
ADD COLUMN `sfz_no` VARCHAR(20) NOT NULL AFTER `name`,
ADD UNIQUE INDEX `sfz_no_UNIQUE` (`sfz_no` ASC);
  
  
-- 驾驶员身份证字段unique  -dxb -2016年8月11日09:52:57
ALTER TABLE `driverhr`.`tdriver` 
ADD UNIQUE INDEX `sfz_no_UNIQUE` (`sfz_no` ASC);

-- 员工表加上电话号码字段  -dxb -2016年8月12日13:36:36
ALTER TABLE `driverhr`.`tmember` 
ADD COLUMN `phone` VARCHAR(20) NULL AFTER `login_name`;

-- 补充主键自增 -dxb -2016年8月12日15:02:22
ALTER TABLE `driverhr`.`tdriver_honour` 
CHANGE COLUMN `id` `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '驾驶员荣誉记录' ;

ALTER TABLE `driverhr`.`texam_result` 
CHANGE COLUMN `id` `id` INT(11) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `driverhr`.`texam_signup` 
CHANGE COLUMN `id` `id` INT(11) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `driverhr`.`torg_basic_info` 
CHANGE COLUMN `id` `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '机构的基本信息' ;

ALTER TABLE `driverhr`.`tpaper_design` 
CHANGE COLUMN `id` `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '试卷设计表' ;

ALTER TABLE `driverhr`.`tpaper_list` 
CHANGE COLUMN `id` `id` INT(11) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `driverhr`.`tquestion_items` 
CHANGE COLUMN `id` `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '题目的选项' ;


ALTER TABLE `driverhr`.`texam_result` 
DROP FOREIGN KEY `texam_result_ibfk_3`;
ALTER TABLE `driverhr`.`texam_result` 
DROP INDEX `question_id` ;

ALTER TABLE `driverhr`.`tquestion` 
CHANGE COLUMN `id` `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '题库' ;

ALTER TABLE `driverhr`.`texam_result` 
ADD INDEX `1231_idx` (`question_id` ASC);
ALTER TABLE `driverhr`.`texam_result` 
ADD CONSTRAINT `texam_result_ibfk_3`
  FOREIGN KEY (`question_id`)
  REFERENCES `driverhr`.`tquestion` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


ALTER TABLE `driverhr`.`texam` 
DROP FOREIGN KEY `texam_ibfk_2`;
ALTER TABLE `driverhr`.`texam` 
DROP INDEX `paper_id` ;

ALTER TABLE `driverhr`.`tpaper_design` 
DROP FOREIGN KEY `tpaper_design_ibfk_1`;
ALTER TABLE `driverhr`.`tpaper_design` 
DROP INDEX `paper_id` ;

ALTER TABLE `driverhr`.`tpaper_list` 
DROP FOREIGN KEY `tpaper_list_ibfk_1`;
ALTER TABLE `driverhr`.`tpaper_list` 
DROP INDEX `paper_id` ;

ALTER TABLE `driverhr`.`tpaper` 
CHANGE COLUMN `id` `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '试卷' ;

ALTER TABLE `driverhr`.`tpaper_list` 
ADD INDEX `tpaper_list_ibfk_2_idx` (`paper_id` ASC);
ALTER TABLE `driverhr`.`tpaper_list` 
ADD CONSTRAINT `tpaper_list_ibfk_1`
  FOREIGN KEY (`paper_id`)
  REFERENCES `driverhr`.`tpaper` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `driverhr`.`tpaper_design` 
ADD INDEX `tpaper_design_ibfk_1_idx` (`paper_id` ASC);
ALTER TABLE `driverhr`.`tpaper_design` 
ADD CONSTRAINT `tpaper_design_ibfk_1`
  FOREIGN KEY (`paper_id`)
  REFERENCES `driverhr`.`tpaper` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `driverhr`.`texam` 
ADD INDEX `texam_ibfk_2_idx` (`paper_id` ASC);
ALTER TABLE `driverhr`.`texam` 
ADD CONSTRAINT `texam_ibfk_2`
  FOREIGN KEY (`paper_id`)
  REFERENCES `driverhr`.`tpaper` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `driverhr`.`tdepartment` 
DROP FOREIGN KEY `tdepartment_ibfk_3`;
ALTER TABLE `driverhr`.`tdepartment` 
DROP INDEX `create_by` ;

ALTER TABLE `driverhr`.`tmember` 
DROP FOREIGN KEY `tmember_ibfk_3`;
ALTER TABLE `driverhr`.`tmember` 
DROP INDEX `create_by` ;

ALTER TABLE `driverhr`.`torganization` 
DROP FOREIGN KEY `torganization_ibfk_2`;
ALTER TABLE `driverhr`.`torganization` 
DROP INDEX `link_person_id` ;

ALTER TABLE `driverhr`.`tmember` 
CHANGE COLUMN `id` `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '机构员工表' ;

ALTER TABLE `driverhr`.`torganization` 
ADD INDEX `torganization_ibfk_2_idx` (`link_person_id` ASC);
ALTER TABLE `driverhr`.`torganization` 
ADD CONSTRAINT `torganization_ibfk_2`
  FOREIGN KEY (`link_person_id`)
  REFERENCES `driverhr`.`tmember` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `driverhr`.`tmember` 
ADD INDEX `tmember_ibfk_3_idx` (`create_by` ASC);
ALTER TABLE `driverhr`.`tmember` 
ADD CONSTRAINT `tmember_ibfk_3`
  FOREIGN KEY (`create_by`)
  REFERENCES `driverhr`.`tmember` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `driverhr`.`tdepartment` 
ADD INDEX `tdepartment_ibfk_3_idx` (`create_by` ASC);
ALTER TABLE `driverhr`.`tdepartment` 
ADD CONSTRAINT `tdepartment_ibfk_3`
  FOREIGN KEY (`create_by`)
  REFERENCES `driverhr`.`tmember` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
  
  
-- 添加权限 -dxb -2016年8月15日08:58:41
--
-- Table structure for table `tauth`
--

DROP TABLE IF EXISTS `tauth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tauth` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `seq` int(11) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `is_menu` tinyint(4) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pid` (`pid`),
  CONSTRAINT `tauth_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `tauth` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=856 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tauth_group`
--

DROP TABLE IF EXISTS `tauth_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tauth_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tauth_group_list`
--

DROP TABLE IF EXISTS `tauth_group_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tauth_group_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `auth_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `group_id` (`group_id`),
  KEY `auth_id` (`auth_id`),
  CONSTRAINT `tauth_group_list_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `tauth_group` (`id`),
  CONSTRAINT `tauth_group_list_ibfk_2` FOREIGN KEY (`auth_id`) REFERENCES `tauth` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4247 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `trole`
--

DROP TABLE IF EXISTS `trole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trole` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `org_id` bigint(20) DEFAULT NULL,
  `ctrl_role_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `org_id` (`org_id`),
  CONSTRAINT `trole_ibfk_1` FOREIGN KEY (`org_id`) REFERENCES `torgnization` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `trole_tauth_group`
--

DROP TABLE IF EXISTS `trole_tauth_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trole_tauth_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `auth_group_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`) USING BTREE,
  KEY `auth_id` (`auth_group_id`) USING BTREE,
  CONSTRAINT `trole_tauth_group_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `trole` (`id`),
  CONSTRAINT `trole_tauth_group_ibfk_2` FOREIGN KEY (`auth_group_id`) REFERENCES `tauth_group` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=680 DEFAULT CHARSET=utf8;

-- 员工添加权限相关字段  -dxb -2016年8月15日09:06:21
ALTER TABLE `driverhr`.`tmember` 
ADD COLUMN `role_id` INT NULL AFTER `status`,
ADD INDEX `tmember_ibfk_3_idx1` (`role_id` ASC);

-- 试卷表中增加难中易难度百分比 -sdy -2016年8月16日16:45:45
ALTER TABLE tpaper ADD easy_percent double NOT NULL COMMENT '简单难度题目所占百分比', 
ADD medium_percent double NOT NULL COMMENT '中等难度题目所占百分比',
ADD hard_percent double NOT NULL COMMENT '困难难度题目所占百分比';

-- 考试表中增加标记正式考试或模拟考试字段 -sdy -2016年8月16日16:46:35
ALTER TABLE texam ADD type tinyint NOT NULL DEFAULT 0 COMMENT '考试类型，0表示正式考试，1表示模拟考试，默认0';

-- 添加驾驶员参加模拟考试表 -sdy -2016年8月16日16:47:10
DROP TABLE IF EXISTS `texam_practice`;
CREATE TABLE `texam_practice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '驾驶员参加模拟考试表',
  `exam_id` int(11) NOT NULL COMMENT '模拟考试id',
  `driver_id` int(11) NOT NULL COMMENT '驾驶员id',
  `create_time` datetime NOT NULL DEFAULT NOW() COMMENT '参加模拟考时间',
  PRIMARY KEY (`id`),
  KEY `exam_id` (`exam_id`),
  KEY `driver_id` (`driver_id`),
  CONSTRAINT `texam_practice_ibfk_1` FOREIGN KEY (`exam_id`) REFERENCES `texam` (`id`),
  CONSTRAINT `texam_practice_ibfk_2` FOREIGN KEY (`driver_id`) REFERENCES `tdriver` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 添加驾驶员模拟考试结果表 -sdy -2016年8月16日16:47:41
DROP TABLE IF EXISTS `texam_practice_result`;
CREATE TABLE `texam_practice_result`(
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '驾驶员模拟考结果表',
  `exam_practice_id` int(11) NOT NULL COMMENT '参加模拟考试id',
  `question_id` int(11) NOT NULL COMMENT '题目id',
  `sequence` int(11) NOT NULL COMMENT '题目序号',
  `answer` varchar(500) NOT NULL COMMENT '驾驶员答案',
  `submit_time` datetime NOT NULL COMMENT '提交时间',
  `marks` double DEFAULT NULL COMMENT '得分',
  PRIMARY KEY (`id`),
  KEY `exam_practice_id` (`exam_practice_id`),
  KEY `question_id` (`question_id`),
  CONSTRAINT `exam_practice_id` FOREIGN KEY (`exam_practice_id`) REFERENCES `texam_practice` (`id`),
  CONSTRAINT `question_id` FOREIGN KEY (`question_id`) REFERENCES `tquestion` (`id`)  ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 驾驶员参加模拟考表添加模拟考总分字段
ALTER TABLE texam_practice ADD final_score double COMMENT '驾驶员模拟考总分';


-- 机构加上一个备注字段，用于记录审核失败原因等
ALTER TABLE `driverhr`.`torganization` 
ADD COLUMN `remark` VARCHAR(200) NULL COMMENT '用于记录审核失败原因等' AFTER `status`;

-- 所创建的部门默认可用
ALTER TABLE `driverhr`.`tdepartment` 
CHANGE COLUMN `status` `status` TINYINT(4) NOT NULL DEFAULT 1 COMMENT '部门状态：-1->删除；0->不可用；1->可用' ;

-- 考试表添加考试时长字段，用于模拟考设计
ALTER TABLE texam ADD duration int COMMENT '考试时长(分钟)，模拟考无考试时间需要时长字段';


-- 员工创建后状态默认为可用 -dxb -2016年8月22日16:32:49
ALTER TABLE `driverhr`.`tmember`
CHANGE COLUMN `status` `status` TINYINT(4) NOT NULL DEFAULT 1 COMMENT '状态: -1 ->删除；0->不可用；1->可用' ;


-- 考试结果表中answer字段改为可空，空answer为了保存众人一卷时的试卷题目 -sdy -
ALTER TABLE texam_result MODIFY answer varchar(500) NULL;
ALTER TABLE texam_practice_result MODIFY answer varchar(500) NULL;

-- 默认密码为身份证后六位，密码可以为空
ALTER TABLE `driverhr`.`tmember`
CHANGE COLUMN `password` `password` VARCHAR(50) NULL
COMMENT '登录密码';

-- 驾驶员报名考试表增加驾驶员实际参加考试时间 -sdy -2016年8月26日13:53:31
ALTER TABLE texam_signup ADD take_exam_time datetime COMMENT '驾驶员参加考试的时间';

-- 考试表增加及格题目数以及及格分字段 -sdy -2016年8月26日13:54:06
ALTER TABLE texam ADD pass_num int(11) COMMENT '该场考试及格题目数';
ALTER TABLE texam ADD pass_score int(11) COMMENT '该场考试及格分';

-- 公告表 -dxb -2016年8月27日09:47:12
CREATE TABLE `driverhr`.`tannouncement` (
  `id`                     INT           NOT NULL AUTO_INCREMENT,
  `title`                  VARCHAR(100)  NOT NULL
  COMMENT '标题',
  `text`                   VARCHAR(2000) NULL
  COMMENT '正文',
  `source_organization_id` INT           NOT NULL
  COMMENT '创建人所在的org，也就是公告来源',
  `create_by`              INT           NOT NULL
  COMMENT '创建人',
  `create_date`            TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  `closure_date`           TIMESTAMP     NULL
  COMMENT '有效期，若为空  有效期就是无限大',
  `attachment`             VARCHAR(45)   NULL
  COMMENT '附件，这里只包含一个附件，若需要多附件，请打包上传',
  `type`                   TINYINT(4)    NULL
  COMMENT '类型（看的人是驾驶员还是车企 还是 水泥办员工）',
  `read_count`             INT           NOT NULL DEFAULT 0
  COMMENT '阅读次数',
  PRIMARY KEY (`id`),
  INDEX `source_org_idx` (`source_organization_id` ASC),
  INDEX `creater_idx` (`create_by` ASC),
  CONSTRAINT `source_org`
  FOREIGN KEY (`source_organization_id`)
  REFERENCES `driverhr`.`torganization` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `creater`
  FOREIGN KEY (`create_by`)
  REFERENCES `driverhr`.`tmember` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = '公告表';

-- 原先考试表中的及格分数和及格题目数删除，试卷表添加及格分数和及格题目数  -sdy 2016年9月12日14:51:12
ALTER TABLE texam DROP COLUMN pass_num;
ALTER TABLE texam DROP COLUMN pass_score;
ALTER TABLE tpaper ADD pass_num int(11) COMMENT '该试卷及格题目数';
ALTER TABLE tpaper ADD pass_score int(11) COMMENT '该试卷及格分';

-- 考试报名表增加正确答题数 -sdy 2016年9月26日14:47:10
ALTER TABLE texam_signup ADD final_num int(11) COMMENT '正确答题数';

-- 添加卡车表
DROP TABLE IF EXISTS `ttruck`;
CREATE TABLE `ttruck` (
  `id` int(11) NOT NULL,
  `car_no` varchar(10) DEFAULT NULL COMMENT '车牌号',
  `truck_type` varchar(255) DEFAULT NULL COMMENT '车型',
  `status` tinyint(4) DEFAULT NULL COMMENT '汽车状态：-1->损坏(不可用) 0->未使用 1->使用中',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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

-- 新增星级规则表 -sdy -2016年11月14日19:17:23
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
ALTER TABLE `tspecial_vehicle` CHANGE COLUMN `registration_number` `car_no` DATE NOT NULL COMMENT '车牌号';

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

--  驾驶员星级表添加原先星级字段，为了计算年度基础星级  -sdy  -2016年12月9日14:12:20
ALTER TABLE tdriver_star ADD pre_star int(11) NOT NULL COMMENT '评定前星级';
ALTER TABLE tdriver_star ADD CONSTRAINT `tdriver_star_ibfk_5` FOREIGN KEY (`pre_star`) REFERENCES `tstar` (`id`);

ALTER TABLE torg_basic_info CHANGE COLUMN `driver_captain_id` `driver_captain_id` INT(11) NULL COMMENT '车队长';
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
