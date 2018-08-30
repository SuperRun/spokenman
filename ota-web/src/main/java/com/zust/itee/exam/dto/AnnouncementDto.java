package com.zust.itee.exam.dto;

import java.util.Date;

import com.zust.itee.exam.enums.AnnouncementStatusEnum;
import com.zust.itee.exam.enums.AnnouncementTypeEnum;

public class AnnouncementDto {

    private Integer id;
    private String title;
    private String text;
    private Date createDate;
    private Date closureDate;
    private String attachment;
    private Integer readCount;

    //private AnnouncementTypeEnum type;
    private AnnouncementTypeEnum type;

    private Integer memberId;

    private Integer organizationId;

    private AnnouncementStatusEnum status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getClosureDate() {
        return closureDate;
    }

    public void setClosureDate(Date closureDate) {
        this.closureDate = closureDate;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public AnnouncementTypeEnum getType() {
        return type;
    }

    public void setType(AnnouncementTypeEnum type) {
        this.type = type;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public AnnouncementStatusEnum getStatus() {
        return status;
    }

    public void setStatus(AnnouncementStatusEnum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AnnouncementDto [id=" + id + ", title=" + title + ", text="
                + text + ", createDate=" + createDate + ", closureDate="
                + closureDate + ", attachment=" + attachment + ", readCount="
                + readCount + ", type=" + type.getType() + ", memberId=" + memberId
                + ", organizationId=" + organizationId
                + "]";
    }
}
