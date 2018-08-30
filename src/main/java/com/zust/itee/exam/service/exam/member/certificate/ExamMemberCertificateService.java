package com.zust.itee.exam.service.exam.member.certificate;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zust.itee.exam.dto.base.PageBaseDto;
import com.zust.itee.exam.dto.exam.member.DriverInfoInExamManagement;
import com.zust.itee.exam.entity.TexamSignup;

public interface ExamMemberCertificateService {

    /**
     * @author sdy
     * @why 获取某场考试中可以证书录入的驾驶员信息
     */
    List<DriverInfoInExamManagement> getCertificateDrivers(Integer examId,
            PageBaseDto pageBaseDto) throws RuntimeException;

    /**
     * @author sdy
     * @why 创建证书录入批量导入模板
     */
    String createDriverCertificateExcel(HttpServletRequest request,
            Integer examId);

    /**
     * @author sdy
     * @why 获取证书录入分页信息
     */
    PageBaseDto getCertificatePageBaseDto(Integer examId,
            PageBaseDto pageBaseDto);

    /**
     * @author sdy
     * @why 批量导入证书号
     */
    boolean certificateAllDrivers(File file, Integer examId) throws RuntimeException;

    /**
     * @author sdy
     * @why 为一位驾驶员输入证书信息
     */
    boolean certificateOneDriver(Integer examId, Integer driverId,
            String certificateNo, String certificatePhoto);

    /**
     * @author sdy
     * @why 提交证书，提交之后不能再修改证书信息
     */
    boolean submitCertificate(Integer examId) throws RuntimeException;

    /**
     * @author sdy
     * @why TexamSignup转化为dto
     */
    List<DriverInfoInExamManagement> transTexamSignupToSignupDriverInfo(
            List<TexamSignup> texamSignups);
}
