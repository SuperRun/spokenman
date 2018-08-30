package com.zust.itee.exam.dto;


import java.util.Date;

public class DriverInsurance {

	private long id;
	private Integer driverId;
	private String driverName;
	private long compensationTypeId;
	private String insuranceName;
	private String insuranceId;
	private String compensationId;
	private String insuranceCompany;
	private String policeId;
	private String carNo;
	private double compensationFee;
	private String remark;

	private Integer injuredNum;
	private String injuredPlace;
	private Integer deadNum;
	private Date occureTime;

	public DriverInsurance() {
	}

	public DriverInsurance(long id, Integer driverId, String driverName, long compensationTypeId, String insuranceName, String insuranceId, String compensationId, String insuranceCompany, String policeId, String carNo, double compensationFee, String remark, Date occureTime, String injuredPlace, Integer injuredNum, Integer deadNum) {
		this.id = id;
		this.driverId = driverId;
		this.driverName = driverName;
		this.compensationTypeId = compensationTypeId;
		this.insuranceName = insuranceName;
		this.insuranceId = insuranceId;
		this.compensationId = compensationId;
		this.insuranceCompany = insuranceCompany;
		this.policeId = policeId;
		this.carNo = carNo;
		this.compensationFee = compensationFee;
		this.remark = remark;
		this.occureTime = occureTime;
		this.injuredPlace = injuredPlace;
		this.injuredNum = injuredNum;
		this.deadNum = deadNum;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Integer getDriverId() {
		return driverId;
	}
	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public long getCompensationTypeId() {
		return compensationTypeId;
	}
	public void setCompensationTypeId(long compensationTypeId) {
		this.compensationTypeId = compensationTypeId;
	}
	public String getInsuranceName() {
		return insuranceName;
	}
	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}
	public String getInsuranceId() {
		return insuranceId;
	}
	public void setInsuranceId(String insuranceId) {
		this.insuranceId = insuranceId;
	}
	public String getCompensationId() {
		return compensationId;
	}
	public void setCompensationId(String compensationId) {
		this.compensationId = compensationId;
	}
	public String getInsuranceCompany() {
		return insuranceCompany;
	}
	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}
	public String getPoliceId() {
		return policeId;
	}
	public void setPoliceId(String policeId) {
		this.policeId = policeId;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public double getCompensationFee() {
		return compensationFee;
	}
	public void setCompensationFee(double compensationFee) {
		this.compensationFee = compensationFee;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getOccureTime() {
		return occureTime;
	}

	public void setOccureTime(Date occureTime) {
		this.occureTime = occureTime;
	}

	public String getInjuredPlace() {
		return injuredPlace;
	}

	public void setInjuredPlace(String injuredPlace) {
		this.injuredPlace = injuredPlace;
	}

	public Integer getInjuredNum() {
		return injuredNum;
	}

	public void setInjuredNum(Integer injuredNum) {
		this.injuredNum = injuredNum;
	}

	public Integer getDeadNum() {
		return deadNum;
	}

	public void setDeadNum(Integer deadNum) {
		this.deadNum = deadNum;
	}
}
