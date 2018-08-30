package com.zust.itee.exam.dto;

import java.util.Date;

import com.zust.itee.exam.entity.Tdriver;
import com.zust.itee.exam.entity.Torganization;

public class DriverReward {

    private Integer id;
    private String rewardName;
    private String driverName;
    private String orgName;

    private Torganization torganization;
    private Tdriver tdriver;
    private long rewardTypeId;
    private Date rewardTime;
    private String remark;

    public DriverReward() {
        // TODO Auto-generated constructor stub
    }

    public DriverReward(Integer id, String rewardName, String driverName,
            String orgName, long rewardTypeId, Date rewardTime, String remark) {
        super();
        this.id = id;
        this.rewardName = rewardName;
        this.driverName = driverName;
        this.orgName = orgName;
        this.rewardTypeId = rewardTypeId;
        this.rewardTime = rewardTime;
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRewardName() {
        return rewardName;
    }

    public void setRewardName(String rewardName) {
        this.rewardName = rewardName;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Torganization getTorganization() {
        return torganization;
    }

    public void setTorganization(Torganization torganization) {
        this.torganization = torganization;
    }

    public Tdriver getTdriver() {
        return tdriver;
    }

    public void setTdriver(Tdriver tdriver) {
        this.tdriver = tdriver;
    }

    public long getRewardTypeId() {
        return rewardTypeId;
    }

    public void setRewardTypeId(long rewardTypeId) {
        this.rewardTypeId = rewardTypeId;
    }

    public Date getRewardTime() {
        return rewardTime;
    }

    public void setRewardTime(Date rewardTime) {
        this.rewardTime = rewardTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
