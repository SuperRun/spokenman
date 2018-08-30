package com.zust.itee.exam.service.exam.driver.signup;

import java.util.List;

import com.zust.itee.exam.dto.base.PageBaseDto;
import com.zust.itee.exam.dto.exam.member.ExamExposer;
import com.zust.itee.exam.entity.Texam;
import com.zust.itee.exam.service.BaseService;

public interface ExamDriverSignupService extends BaseService<Texam> {

    /**
     * @author sdy
     * @why 获取考试列表（驾驶员报名选择）
     */
    List<ExamExposer> getExamHome(PageBaseDto pageBaseDto, int driverId) throws RuntimeException;

    /**
     * @author sdy
     * @why 获取考试列表（驾驶员报名选择)页码相关信息
     */
    PageBaseDto getExamPageBaseDto(PageBaseDto pageBaseDto);

    /**
     * @author sdy
     * @why 驾驶员报名考试
     */
    boolean signup(int examId, int driverId) throws RuntimeException;

    /**
     * @author sdy
     * @why 驾驶员取消考试报名
     */
    boolean signupCancel(int examId, int driverId, Short memberOrgType)
            throws RuntimeException;
}
