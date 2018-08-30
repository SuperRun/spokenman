package com.zust.itee.exam.dto.driverRecord;

import java.util.Date;

/**
 * Created by liy on 2016/12/21.
 */
public class TrainingIndexDto {

    private String name;
    private String description;
    private Date startTime;
    private Date endTime;
    private String driverStatus;
    private String examStatus;

    public TrainingIndexDto() {
    }

    public TrainingIndexDto(String name, String description, Date startTime, Date endTime, String driverStatus, String examStatus) {
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.driverStatus = driverStatus;
        this.examStatus = examStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(String driverStatus) {
        this.driverStatus = driverStatus;
    }

    public String getExamStatus() {
        return examStatus;
    }

    public void setExamStatus(String examStatus) {
        this.examStatus = examStatus;
    }
}
