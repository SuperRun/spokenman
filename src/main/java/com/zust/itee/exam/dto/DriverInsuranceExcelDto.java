package com.zust.itee.exam.dto;


public class DriverInsuranceExcelDto {

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



	public DriverInsuranceExcelDto(long id, Integer driverId, String driverName,
								   long compensationTypeId, String insuranceName, String insuranceId,
								   String compensationId, String insuranceCompany, String policeId,
								   String carNo, double compensationFee, String remark) {
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



}
