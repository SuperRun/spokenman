package com.zust.itee.exam.dto.driverRecord;

/**
 * Created by LI-Y on 2016/10/3.
 */

public class HonourDto {

    private static final long serialVersionUID = -2389375487876158215L;
    private Integer organizationId;
    private Integer driverId;
    private long honourTypeId;
    private String remark;

    public HonourDto() {

    }

    public HonourDto(Integer driverId, long honourTypeId, Integer organizationId, String remark) {
        this.driverId = driverId;
        this.honourTypeId = honourTypeId;
        this.organizationId = organizationId;
        this.remark = remark;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public long getHonourTypeId() {
        return honourTypeId;
    }

    public void setHonourTypeId(long honourTypeId) {
        this.honourTypeId = honourTypeId;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
