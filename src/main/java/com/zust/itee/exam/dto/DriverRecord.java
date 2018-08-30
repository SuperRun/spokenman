package com.zust.itee.exam.dto;

/**
 * 驾驶员档案首页
 * @author liy
 *
 */
public class DriverRecord {

	private Integer id;
	private String name;
	private String orgName;

	private String driverHonourName;
	private String driverInsuranceName;
	private String driverRewardName;
	private String driverTrafficVioName;
	private String driverWorkHisName;

	public DriverRecord(Integer id, String name, String orgName, String driverHonourName, String driverInsuranceName,
			String driverRewardName, String driverTrafficVioName, String driverWorkHisName) {
		super();
		this.id = id;
		this.name = name;
		this.orgName = orgName;
		this.driverHonourName = driverHonourName;
		this.driverInsuranceName = driverInsuranceName;
		this.driverRewardName = driverRewardName;
		this.driverTrafficVioName = driverTrafficVioName;
		this.driverWorkHisName = driverWorkHisName;
	}

	@Override
	public String toString() {
		return "DriverRecord [id=" + id + ", name=" + name + ", orgName=" + orgName + ", driverHonourName="
				+ driverHonourName + ", driverInsuranceName=" + driverInsuranceName + ", driverRewardName="
				+ driverRewardName + ", driverTrafficVioName=" + driverTrafficVioName + ", driverWorkHisName="
				+ driverWorkHisName + "]";
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
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getDriverHonourName() {
		return driverHonourName;
	}
	public void setDriverHonourName(String driverHonourName) {
		this.driverHonourName = driverHonourName;
	}
	public String getDriverInsuranceName() {
		return driverInsuranceName;
	}
	public void setDriverInsuranceName(String driverInsuranceName) {
		this.driverInsuranceName = driverInsuranceName;
	}
	public String getDriverRewardName() {
		return driverRewardName;
	}
	public void setDriverRewardName(String driverRewardName) {
		this.driverRewardName = driverRewardName;
	}
	public String getDriverTrafficVioName() {
		return driverTrafficVioName;
	}
	public void setDriverTrafficVioName(String driverTrafficVioName) {
		this.driverTrafficVioName = driverTrafficVioName;
	}
	public String getDriverWorkHisName() {
		return driverWorkHisName;
	}
	public void setDriverWorkHisName(String driverWorkHisName) {
		this.driverWorkHisName = driverWorkHisName;
	}




}
