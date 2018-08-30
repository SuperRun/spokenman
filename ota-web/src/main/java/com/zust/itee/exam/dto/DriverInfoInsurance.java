package com.zust.itee.exam.dto;

public class DriverInfoInsurance {

	private long id;
	private Integer driverId;
	private String driverName;

	private String thisYear;
	private String nextYear;
	private Double feeSum;
	private Double rate;

	public DriverInfoInsurance() {
	}

	public DriverInfoInsurance(Integer driverId, String driverName, String thisYear, String nextYear) {
		this.driverId = driverId;
		this.driverName = driverName;
		this.thisYear = thisYear;
		this.nextYear = nextYear;
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

	public String getThisYear() {
		return thisYear;
	}

	public void setThisYear(String thisYear) {
		this.thisYear = thisYear;
	}

	public String getNextYear() {
		return nextYear;
	}

	public void setNextYear(String nextYear) {
		this.nextYear = nextYear;
	}

	public Double getFeeSum() {
		return feeSum;
	}

	public void setFeeSum(Double feeSum) {
		this.feeSum = feeSum;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}
}
