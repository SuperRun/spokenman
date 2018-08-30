package com.zust.itee.exam.dto.driverRecord;

import java.util.Date;

/**
 * Created by LI-Y on 2016/10/3.
 */
public class TrafficViolationDto {

    private static final long serialVersionUID = -9022597078940404335L;
    private Integer driverId;
    private String policeId;
    private String carNo;
    private long trafficViolationTypeId;
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

    public TrafficViolationDto() {

    }

    public TrafficViolationDto(String carNo, String data1, String data2, String data3, String data4, String data5, Integer deathNum, Integer driverId, Integer injuredNum, String occurPlace, Date occurTime, String policeId, String remark, double speed, long trafficViolationTypeId) {
        this.carNo = carNo;
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
        this.data4 = data4;
        this.data5 = data5;
        this.deathNum = deathNum;
        this.driverId = driverId;
        this.injuredNum = injuredNum;
        this.occurPlace = occurPlace;
        this.occurTime = occurTime;
        this.policeId = policeId;
        this.remark = remark;
        this.speed = speed;
        this.trafficViolationTypeId = trafficViolationTypeId;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
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

    public Integer getDeathNum() {
        return deathNum;
    }

    public void setDeathNum(Integer deathNum) {
        this.deathNum = deathNum;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getInjuredNum() {
        return injuredNum;
    }

    public void setInjuredNum(Integer injuredNum) {
        this.injuredNum = injuredNum;
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

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public long getTrafficViolationTypeId() {
        return trafficViolationTypeId;
    }

    public void setTrafficViolationTypeId(long trafficViolationTypeId) {
        this.trafficViolationTypeId = trafficViolationTypeId;
    }
}

