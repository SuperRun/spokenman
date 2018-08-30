package com.zust.itee.exam.service.exam.member.finish;

import com.zust.itee.exam.dto.exam.member.DriverInfoInExamManagement;
import com.zust.itee.exam.dto.exam.member.ExamFinishInfo;
import com.zust.itee.exam.entity.TexamSignup;

public interface ExamMemberFinishService {

    /**
     * @author sdy
     * @why 获取已完成考试详情
     */
    ExamFinishInfo getFinishExamInfo(Integer examId);

    /**
     * @author sdy
     * @why 由实体类获取考试完成显示驾驶员信息
     */
    DriverInfoInExamManagement transTdriverToDriverInfoInFinishExam(TexamSignup texamSignup);
}
