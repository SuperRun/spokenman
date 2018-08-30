package com.zust.itee.exam.dto;

import java.util.Date;

public class DriverTrafficVio {

	private Integer id;
	private Integer driverId;
	private String driverName;
	private String policeId;
	private String carNo;
	private long trafficViolationTypeId;
	private String trafficVioationName;
	private String occurPlace;
	private Date occurTime;
	private double speed;
	private Integer deathNum;
	private Integer injuredNum;
	private String data1;
	private String data2;
	private String data3;
	private String data4;
	private String data5;
	private String remark;




	public DriverTrafficVio(Integer id, Integer driverId, String driverName,
			String policeId, String carNo, long trafficViolationTypeId,
			String trafficVioationName, String occurPlace, Date occurTime,
			double speed, Integer deathNum, Integer injuredNum, String data1,
			String data2, String data3, String data4, String data5,
			String remark) {
		this.id = id;
		this.driverId = driverId;
		this.driverName = driverName;
		this.policeId = policeId;
		this.carNo = carNo;
		this.trafficViolationTypeId = trafficViolationTypeId;
		this.trafficVioationName = trafficVioationName;
		this.occurPlace = occurPlace;
		this.occurTime = occurTime;
		this.speed = speed;
		this.deathNum = deathNum;
		this.injuredNum = injuredNum;
		this.data1 = data1;
		this.data2 = data2;
		this.data3 = data3;
		this.data4 = data4;
		this.data5 = data5;
		this.remark = remark;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public long getTrafficViolationTypeId() {
		return trafficViolationTypeId;
	}
	public void setTrafficViolationTypeId(long trafficViolationTypeId) {
		this.trafficViolationTypeId = trafficViolationTypeId;
	}
	public String getTrafficVioationName() {
		return trafficVioationName;
	}
	public void setTrafficVioationName(String trafficVioationName) {
		this.trafficVioationName = trafficVioationName;
	}
	public String getOccurPlace() {
		return occurPlace;
	}
	public void setOccurPlace(String occurPlace) {
		this.occurPlace = occurPlace;
	}
	public Date getOccurTime() {
		return occurTime;
	}
	public void setOccurTime(Date occurTime) {
		this.occurTime = occurTime;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public Integer getDeathNum() {
		return deathNum;
	}
	public void setDeathNum(Integer deathNum) {
		this.deathNum = deathNum;
	}
	public Integer getInjuredNum() {
		return injuredNum;
	}
	public void setInjuredNum(Integer injuredNum) {
		this.injuredNum = injuredNum;
	}
	public String getData1() {
		return data1;
	}
	public void setData1(String data1) {
		this.data1 = data1;
	}
	public String getData2() {
		return data2;
	}
	public void setData2(String data2) {
		this.data2 = data2;
	}
	public String getData3() {
		return data3;
	}
	public void setData3(String data3) {
		this.data3 = data3;
	}
	public String getData4() {
		return data4;
	}
	public void setData4(String data4) {
		this.data4 = data4;
	}
	public String getData5() {
		return data5;
	}
	public void setData5(String data5) {
		this.data5 = data5;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}




}
