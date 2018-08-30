package com.zust.itee.exam.dto.driverRecord;

/**
 * Created by LI-Y on 2016/10/3.
 */
public class InsuranceCompensationDto {

    private static final long serialVersionUID = -7485263471451272904L;
    private Integer driverId;
    private String insuranceId;
    private String compensationId;
    private String insuranceCompany;
    private String policeId;
    private String carNo;
    private long compensationTypeId;
    private double compensationFee;
    private String remark;

    public InsuranceCompensationDto () {

    }

    public InsuranceCompensationDto(String carNo, double compensationFee, String compensationId, long compensationTypeId, Integer driverId, String insuranceCompany, String insuranceId, String policeId, String remark) {
        this.carNo = carNo;
        this.compensationFee = compensationFee;
        this.compensationId = compensationId;
        this.compensationTypeId = compensationTypeId;
        this.driverId = driverId;
        this.insuranceCompany = insuranceCompany;
        this.insuranceId = insuranceId;
        this.policeId = policeId;
        this.remark = remark;
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

    public String getCompensationId() {
        return compensationId;
    }

    public void setCompensationId(String compensationId) {
        this.compensationId = compensationId;
    }

    public long getCompensationTypeId() {
        return compensationTypeId;
    }

    public void setCompensationTypeId(long compensationTypeId) {
        this.compensationTypeId = compensationTypeId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public String getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(String insuranceId) {
        this.insuranceId = insuranceId;
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
}
