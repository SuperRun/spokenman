package com.zust.itee.exam.service.exam.member.signup;

import java.util.List;

import com.zust.itee.exam.dto.base.PageBaseDto;
import com.zust.itee.exam.dto.exam.PageBaseDtoForSignup;
import com.zust.itee.exam.dto.exam.member.DriverInfoInExamManagement;
import com.zust.itee.exam.dto.exam.member.SignupOrganization;
import com.zust.itee.exam.entity.Texam;
import com.zust.itee.exam.entity.TexamSignup;
import com.zust.itee.exam.service.BaseService;

public interface ExamMemberSignupService extends BaseService<Texam> {

    /**
     * @author sdy
     * @why 获取报名某场考试的某考生办已报名的所有考生
     */
    List<DriverInfoInExamManagement> getSignupDrivers(Integer examId,
            PageBaseDto pageBaseDto, Integer orgId) throws RuntimeException;

    /**
     * @author sdy
     * @why 获取报名某考试某考生办所有已报名考生分页信息
     */
    PageBaseDto getSignupDriversPageBaseDto(Integer examId,
            PageBaseDto pageBaseDto, Integer orgId) throws RuntimeException;

    /**
     * @author sdy
     * @why 批量报名时获取按考生办排列的考生
     */
    List<SignupOrganization> getSignupInfo(Integer memberId, Integer examId);

    /**
     * @author sdy
     * @why 执行批量报名
     */
    boolean signupImport(String signupDriversString, Integer examId);

    /**
     * 获取该考生办所有未报名的考生
     *
     * @param signupStatus 状态1；状态2；……
     */
    List<DriverInfoInExamManagement> getHavenotSignedupDrivers(Integer examId,
            PageBaseDtoForSignup pageBaseDtoForSignup, Integer orgId,
            String signupStatus) throws RuntimeException;

    /**
     * 获取该考生办所有未报名的考生分页信息
     */
    PageBaseDtoForSignup getHavenotSignupDriversPageBaseDto(Integer examId,
            PageBaseDtoForSignup pageBaseDtoForSignup, Integer orgId,
            String signupStatus) throws RuntimeException;

    /**
     * 获取当前选中考生办名
     */
    String getCompanyName(Integer orgId) throws RuntimeException;

    /**
     * @author sdy
     * @why TexamSignup转化为dto
     */
    List<DriverInfoInExamManagement> transTexamSignupToSignupDriverInfo(
            List<TexamSignup> texamSignups);
}
