package com.zust.itee.exam.service.exam.member.create;

import com.zust.itee.exam.dto.exam.member.OrganizeExam;
import com.zust.itee.exam.entity.Texam;
import com.zust.itee.exam.service.BaseService;

public interface ExamMemberCreateService extends BaseService<Texam> {

    /**
     * @author sdy
     * @why 工作人员编辑考试之前获取原考试信息
     */
    OrganizeExam getExamInfo(Integer examId) throws RuntimeException;

    /**
     * @author sdy
     * @why 工作人员组织一场考试
     */
    int organizeExam(int memberId, OrganizeExam organizeExam)
            throws RuntimeException;

    /**
     * @author sdy
     * @why 工作人员取消考试
     */
    boolean deleteExam(int memberId, int examId) throws RuntimeException;

    /**
     * @author sdy
     * @why 获取考试关联试卷id，为编辑试卷准备
     */
    Integer getPaperId(Integer examId) throws RuntimeException;

    /**
     * 关联考试与通知
     */
    boolean connectExamAndAnnouncement(Integer examId, Integer annId) throws RuntimeException;
}
