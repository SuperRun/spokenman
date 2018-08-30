package com.zust.itee.exam.service.exam.driver.finish;

import com.zust.itee.exam.dto.exam.driver.ExamFinishDriverInfo;
import com.zust.itee.exam.entity.TexamSignup;

public interface ExamDriverFinishService {

    /**
     * @author sdy
     * @why 获取已完成考试相关信息
     */
    ExamFinishDriverInfo getExamFinishInfo(Integer driverId, Integer examId)
            throws RuntimeException;

    /**
     * @author sdy
     * @why 由报名信息获取驾驶员相关信息
     */
    ExamFinishDriverInfo transTexamSignupToDriverInfo(TexamSignup texamSignup)
            throws RuntimeException;
}
