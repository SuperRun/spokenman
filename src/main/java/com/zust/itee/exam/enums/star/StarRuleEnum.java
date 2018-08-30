package com.zust.itee.exam.enums.star;

/**
 * 星级规则枚举
 *
 * @author sdy
 *
 */
public enum StarRuleEnum {

	ONESTARDRIVER_TWOSTARDRIVER_1(5, DriverStarEnum.ONESTARDRIVER.getId(),
			DriverStarEnum.TWOSTARDRIVER.getId(), StarRuleTypeEnum.RATE
					.getValueStr(), "一星级驾驶员驾驶专用车辆1年保险赔付零，升级为二星级驾驶员。"), ONESTARDRIVER_TWOSTARDRIVER_2(
			6, DriverStarEnum.ONESTARDRIVER.getId(),
			DriverStarEnum.TWOSTARDRIVER.getId(), StarRuleTypeEnum.RATEAVG
					.getValueStr(), "一星级驾驶员驾驶专用车辆连续2年保险赔付率在10%以下的，升级为二星级驾驶员。"), ONESTARDRIVER_TRAINER_1(
			9, DriverStarEnum.ONESTARDRIVER.getId(), DriverStarEnum.TRAINER
					.getId(), StarRuleTypeEnum.DEADCOUNT.getValueStr(),
			"一星级驾驶员驾驶专用车辆出现1次有责任死亡事故，取消星级，重新培训，重新考试，重新发放培训合格证。"), TWOSTARDRIVER_THREESTARDRIVER_1(
			11, DriverStarEnum.TWOSTARDRIVER.getId(),
			DriverStarEnum.THREESTARDRIVER.getId(), StarRuleTypeEnum.RATE
					.getValueStr(), "二星级驾驶员驾驶专用车辆1年保险赔付零，升级为三星级驾驶员。"), TWOSTARDRIVER_THREESTARDRIVER_2(
			12, DriverStarEnum.TWOSTARDRIVER.getId(),
			DriverStarEnum.THREESTARDRIVER.getId(), StarRuleTypeEnum.RATEAVG
					.getValueStr(), "二星级驾驶员连续2年保险赔付率在10%以下的升级为三星级驾驶员。"), TWOSTARDRIVER_ONESTARDRIVER_1(
			13, DriverStarEnum.TWOSTARDRIVER.getId(),
			DriverStarEnum.ONESTARDRIVER.getId(), StarRuleTypeEnum.RATE
					.getValueStr(), "二星级驾驶员驾驶专用车辆出现1次年度保险赔付超过20%的，降为一星级驾驶员。"), TWOSTARDRIVER_ONESTARDRIVER_2(
			14, DriverStarEnum.TWOSTARDRIVER.getId(),
			DriverStarEnum.ONESTARDRIVER.getId(), StarRuleTypeEnum.INJURECOUNT
					.getValueStr(), "二星级驾驶员驾驶专用车辆出现1次有责任的人员受伤事故，降为一星级驾驶员。"), TWOSTARDRIVER_TRAINER_1(
			16, DriverStarEnum.TWOSTARDRIVER.getId(), DriverStarEnum.TRAINER
					.getId(), StarRuleTypeEnum.DEADCOUNT.getValueStr(),
			"二星级驾驶员驾驶专用车辆出现1次有责任死亡事故，取消星级，重新培训，重新考试，重新发放培训合格证。"),

	THREESTADRIVER_FOURSTARDRIVER_1(17, DriverStarEnum.THREESTARDRIVER.getId(),
			DriverStarEnum.FOURSTARDRIVER.getId(), StarRuleTypeEnum.RATE
					.getValueStr(), "三星级驾驶员驾驶专用车辆1年保险赔付零，升级为四星级驾驶员。"), THREESTARDIVER_FOURSTARDRIVER_2(
			18, DriverStarEnum.THREESTARDRIVER.getId(),
			DriverStarEnum.FOURSTARDRIVER.getId(), StarRuleTypeEnum.RATEAVG
					.getValueStr(), "三星级驾驶员连续2年保险赔付率在10%以下的升级为四星级驾驶员。"), THREESTARDRIVER_TWOSTARDRIVER_1(
			19, DriverStarEnum.THREESTARDRIVER.getId(),
			DriverStarEnum.TWOSTARDRIVER.getId(), StarRuleTypeEnum.RATE
					.getValueStr(), "三星级驾驶员驾驶专用车辆出现1次年度保险赔付超过20%的，降为二星级驾驶员。"), THREESTARDRIVER_TWOSTARDRIVER_2(
			20, DriverStarEnum.THREESTARDRIVER.getId(),
			DriverStarEnum.TWOSTARDRIVER.getId(), StarRuleTypeEnum.INJURECOUNT
					.getValueStr(), "三星级驾驶员驾驶专用车辆出现1次有责任的人员受伤事故，降为二星级驾驶员。"), THREESTARDRIVER_ONESTARDRIVER_1(
			21, DriverStarEnum.THREESTARDRIVER.getId(),
			DriverStarEnum.ONESTARDRIVER.getId(), StarRuleTypeEnum.INJURE2COUNT
					.getValueStr(), "三星级驾驶员驾驶专用车辆出现累计2人受伤有责任安全事故，降为一星级驾驶员。"), THREESTADRIVER_TRAINER_1(
			22, DriverStarEnum.THREESTARDRIVER.getId(), DriverStarEnum.TRAINER
					.getId(), StarRuleTypeEnum.DEADCOUNT.getValueStr(),
			"三星级驾驶员驾驶专用车辆出现1次有责任死亡事故，取消星级，重新培训，重新考试，重新发放培训合格证。"),

	FOURSTARDRIVER_FIVESTARDRIVER_1(23, DriverStarEnum.FOURSTARDRIVER.getId(),
			DriverStarEnum.FIVESTARDRIVER.getId(), StarRuleTypeEnum.RATE
					.getValueStr(), "四星级驾驶员驾驶专用车辆1年保险赔付零，升级为五星级驾驶员。"), FOURSTARDRIVER_FIVESTARDRIVER_2(
			24, DriverStarEnum.FOURSTARDRIVER.getId(),
			DriverStarEnum.FIVESTARDRIVER.getId(), StarRuleTypeEnum.RATEAVG
					.getValueStr(), "四星级驾驶员连续2年保险赔付率在10%以下的升级为五星级驾驶员。"), FOURSTARDRIVER_THREESTARDRIVER_1(
			25, DriverStarEnum.FOURSTARDRIVER.getId(),
			DriverStarEnum.THREESTARDRIVER.getId(), StarRuleTypeEnum.RATE
					.getValueStr(), "四星级驾驶员驾驶专用车辆出现1次年度保险赔付超过20%的，降为三星级驾驶员。"), FOURSTARDRIVER_THREESTARDRIVER_2(
			26, DriverStarEnum.FOURSTARDRIVER.getId(),
			DriverStarEnum.THREESTARDRIVER.getId(),
			StarRuleTypeEnum.INJURECOUNT.getValueStr(),
			"四星级驾驶员驾驶专用车辆出现1次有责任的人员受伤事故，降为三星级驾驶员。"), FOURSTARDRIVER_TWOSTARDRIVER_1(
			27, DriverStarEnum.FOURSTARDRIVER.getId(),
			DriverStarEnum.TWOSTARDRIVER.getId(), StarRuleTypeEnum.INJURE2COUNT
					.getValueStr(), "四星级驾驶员驾驶专用车辆出现累计2人受伤有责任安全事故，降为二星级驾驶员。"), FOURSTARDRIVER_TRAINER_1(
			28, DriverStarEnum.FOURSTARDRIVER.getId(), DriverStarEnum.TRAINER
					.getId(), StarRuleTypeEnum.DEADCOUNT.getValueStr(),
			"四星级驾驶员驾驶专用车辆出现1次有责任死亡事故，取消星级，重新培训，重新考试，重新发放培训合格证"),

	FIVESTARDRIVER_FOURSTARDRIVER_1(29, DriverStarEnum.FIVESTARDRIVER.getId(),
			DriverStarEnum.FOURSTARDRIVER.getId(), StarRuleTypeEnum.RATE
					.getValueStr(), "五星级驾驶员驾驶专用车辆出现1次年度保险赔付超过20%的，降为四星级驾驶员。"), FIVESTARDRIVER_FOURSTARDRIVER_2(
			30, DriverStarEnum.FIVESTARDRIVER.getId(),
			DriverStarEnum.FOURSTARDRIVER.getId(), StarRuleTypeEnum.INJURECOUNT
					.getValueStr(), "五星级驾驶员驾驶专用车辆出现1次有责任的人员受伤事故，降为四星级驾驶员。"), FIVESTARDRIVER_THREESTARDRIVER_1(
			31, DriverStarEnum.FIVESTARDRIVER.getId(),
			DriverStarEnum.THREESTARDRIVER.getId(),
			StarRuleTypeEnum.INJURE2COUNT.getValueStr(),
			"五星级驾驶员驾驶专用车辆出现累计2人受伤有责任安全事故，降为三星级驾驶员。"), FIVESTARDRIVER_TRAINER_1(
			32, DriverStarEnum.FIVESTARDRIVER.getId(), DriverStarEnum.TRAINER
					.getId(), StarRuleTypeEnum.DEADCOUNT.getValueStr(),
			"五星级驾驶员驾驶专用车辆出现1次有责任死亡事故，取消星级，重新培训，重新考试，重新发放培训合格证。");
	// 星级评价规则id
	private Integer id;
	// from星级
	private Integer fromStarId;
	// to星级
	private Integer toStarId;
	// 星级规则类别
	private String ruleTypeName;
	// 描述
	private String description;

	private StarRuleEnum(Integer id, Integer fromStarId, Integer toStarId,
			String ruleTypeName, String description) {
		this.id = id;
		this.fromStarId = fromStarId;
		this.toStarId = toStarId;
		this.ruleTypeName = ruleTypeName;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFromStarId() {
		return fromStarId;
	}

	public void setFromStarId(Integer fromStarId) {
		this.fromStarId = fromStarId;
	}

	public Integer getToStarId() {
		return toStarId;
	}

	public void setToStarId(Integer toStarId) {
		this.toStarId = toStarId;
	}

	public String getRuleTypeName() {
		return ruleTypeName;
	}

	public void setRuleTypeName(String ruleTypeName) {
		this.ruleTypeName = ruleTypeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static Integer getIdOfRule(Integer fromStarId, Integer toStarId,
			String ruleTypeName) {
		for (StarRuleEnum starRuleEnum : StarRuleEnum.values()) {
			if (starRuleEnum.getFromStarId() == fromStarId
					&& starRuleEnum.getToStarId() == toStarId
					&& starRuleEnum.getRuleTypeName().equals(ruleTypeName)) {
				return starRuleEnum.getId();
			}
		}
		return null;
	}

	public static StarRuleEnum getStarRuleById(Integer id) {
		for (StarRuleEnum starRuleEnum : StarRuleEnum.values()) {
			if (starRuleEnum.getId() == id) {
				return starRuleEnum;
			}
		}
		return null;
	}

}
