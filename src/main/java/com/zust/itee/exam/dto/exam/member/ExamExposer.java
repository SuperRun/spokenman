package com.zust.itee.exam.dto.exam.member;

import com.zust.itee.exam.entity.Texam;
import com.zust.itee.exam.util.MyDateUtil;

/**
 * 驾驶员考试列表（供报名查看用）
 *
 * @author sdy
 */
public class ExamExposer {

    // 报名考试状态码
    private short state;

    // 报名考试状态信息
    private String stateInfo;

    // 考试id
    private int examId;

    // 考试名称
    private String name;

    // 考试备注
    private String description;

    // 考试创建时间
    private String creatTime;

    // 考试报名开始时间
    private String signupStartTime;

    // 考试报名结束时间
    private String signupEndTime;

    // 考试开始时间
    private String examStartTime;

    // 考试结束时间
    private String examEndTime;

    // 考试发起组织
    private String orgName;

    // 考试类型
    private Short type;

    // 是否已发布通知
    private Boolean hasAnnouncement;
    // 关联的通知id
    private Integer announcementId;

    public ExamExposer() {
        super();
    }

    /**
     * 不可报名时用
     */
    public ExamExposer(short state, String stateInfo, Texam texam) {
        super();
        this.state = state;
        this.stateInfo = stateInfo;
        this.creatTime = MyDateUtil.date2strMDHM(texam.getCreateTime());
        this.signupStartTime = MyDateUtil.date2strMDHM(texam
                .getSignupStartTime());
        this.signupEndTime = MyDateUtil.date2strMDHM(texam.getSignupEndTime());
        this.examStartTime = MyDateUtil.date2strMDHM(texam.getExamStartTime());
        this.examEndTime = MyDateUtil.date2strMDHM(texam.getExamEndTime());
        this.orgName = texam.getTorganization().getName();
        this.type = texam.getType();
    }

    /**
     * 可以报名时使用
     */
    public ExamExposer(short state, String stateInfo, Texam texam, int examId) {
        super();
        this.state = state;
        this.stateInfo = stateInfo;
        this.examId = examId;
        this.name = texam.getName();
        this.description = texam.getDescription();
        this.creatTime = MyDateUtil.date2strMDHM(texam.getCreateTime());
        this.signupStartTime = MyDateUtil.date2strMDHM(texam
                .getSignupStartTime());
        this.signupEndTime = MyDateUtil.date2strMDHM(texam.getSignupEndTime());
        this.examStartTime = MyDateUtil.date2strMDHM(texam.getExamStartTime());
        this.examEndTime = MyDateUtil.date2strMDHM(texam.getExamEndTime());
        this.orgName = texam.getTorganization().getName();
        this.type = texam.getType();
    }

    /**
     * 模拟考试用
     */
    public ExamExposer(Texam texam) {
        super();
        this.creatTime = MyDateUtil.date2strMDHM(texam.getCreateTime());
        this.examId = texam.getId();
        this.orgName = texam.getTorganization().getName();
        this.name = texam.getName();
        this.type = texam.getType();
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public String getSignupStartTime() {
        return signupStartTime;
    }

    public void setSignupStartTime(String signupStartTime) {
        this.signupStartTime = signupStartTime;
    }

    public String getSignupEndTime() {
        return signupEndTime;
    }

    public void setSignupEndTime(String signupEndTime) {
        this.signupEndTime = signupEndTime;
    }

    public String getExamStartTime() {
        return examStartTime;
    }

    public void setExamStartTime(String examStartTime) {
        this.examStartTime = examStartTime;
    }

    public String getExamEndTime() {
        return examEndTime;
    }

    public void setExamEndTime(String examEndTime) {
        this.examEndTime = examEndTime;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
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

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Boolean getHasAnnouncement() {
        return hasAnnouncement;
    }

    public void setHasAnnouncement(Boolean hasAnnouncement) {
        this.hasAnnouncement = hasAnnouncement;
    }

    public Integer getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(Integer announcementId) {
        this.announcementId = announcementId;
    }

    @Override
    public String toString() {
        return "ExamExposer [state=" + state + ", stateInfo=" + stateInfo
                + ", examId=" + examId + ", creatTime=" + creatTime
                + ", signupStartTime=" + signupStartTime + ", signupEndTime="
                + signupEndTime + ", examStartTime=" + examStartTime
                + ", examEndTime=" + examEndTime + ", orgName=" + orgName + "]";
    }
}
