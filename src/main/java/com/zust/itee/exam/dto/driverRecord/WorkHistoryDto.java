package com.zust.itee.exam.dto.driverRecord;

import java.util.Date;

/**
 * Created by LI-Y on 2016/10/3.
 */
public class WorkHistoryDto {

    private static final long serialVersionUID = -3170580981361984311L;
    private Integer organizationId;
    private Integer driverId;
    private Date startDate;
    private Date endDate;
    private String remark;
    private Integer level;

    public WorkHistoryDto() {

    }

    public WorkHistoryDto(Integer driverId, Date endDate, Integer level, Integer organizationId, String remark, Date startDate) {
        this.driverId = driverId;
        this.endDate = endDate;
        this.level = level;
        this.organizationId = organizationId;
        this.remark = remark;
        this.startDate = startDate;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
