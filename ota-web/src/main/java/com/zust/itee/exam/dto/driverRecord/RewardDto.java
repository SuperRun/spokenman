package com.zust.itee.exam.dto.driverRecord;

/**
 * Created by LI-Y on 2016/10/3.
 */
public class RewardDto {

    private static final long serialVersionUID = 8886979669576649364L;
    private Integer organizationId;
    private Integer driverId;
    private long rewardTypeId;
    private String remark;

    public RewardDto() {

    }

    public RewardDto(Integer driverId, Integer organizationId, String remark, long rewardTypeId) {
        this.driverId = driverId;
        this.organizationId = organizationId;
        this.remark = remark;
        this.rewardTypeId = rewardTypeId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
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

    public long getRewardTypeId() {
        return rewardTypeId;
    }

    public void setRewardTypeId(long rewardTypeId) {
        this.rewardTypeId = rewardTypeId;
    }
}
