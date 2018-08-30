package com.zust.itee.exam.enums.star;

/**
 * 驾驶员星级枚举
 * @author terry
 *
 */
public enum DriverStarEnum {
	TRAINER(1, "培训员"), DRIVER(2, "无星级驾驶员"), ONESTARDRIVER(3, "一星级驾驶员"), TWOSTARDRIVER(
			4, "二星级驾驶员"), THREESTARDRIVER(5, "三星级驾驶员"), FOURSTARDRIVER(6,
			"四星级驾驶员"), FIVESTARDRIVER(7, "五星级驾驶员");
	//

	// 数据库中id
	private Integer id;
	// 名称
	private String name;

	private DriverStarEnum(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public static DriverStarEnum getDriverStarById(Integer starId){
		for(DriverStarEnum driverStarEnum: DriverStarEnum.values()){
			if(driverStarEnum.getId()==starId){
				return driverStarEnum;
			}
		}
		return null;
	}

}
