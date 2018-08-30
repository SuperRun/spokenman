package com.zust.itee.exam.dto;

import java.util.Date;

public class DriverWorkHis {

	private Integer id;
	private Integer orgId;
	private Integer driverId;
	private String orgName;
	private String driverName;
	private Date startDate;
	private Date endDate;
	private String remark;
	private Integer level;

	public DriverWorkHis() {
		// TODO Auto-generated constructor stub
	}


	public DriverWorkHis(Integer id, Integer orgId, Integer driverId,
			String orgName, String driverName, Date startDate, Date endDate,
			String remark, Integer level) {
		super();
		this.id = id;
		this.orgId = orgId;
		this.driverId = driverId;
		this.orgName = orgName;
		this.driverName = driverName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.remark = remark;
		this.level = level;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public Integer getDriverId() {
		return driverId;
	}
	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}




}
