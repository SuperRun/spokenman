package com.zust.itee.exam.service.exam.member.score;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zust.itee.exam.dto.base.PageBaseDto;
import com.zust.itee.exam.dto.exam.member.DriverInfoInExamManagement;
import com.zust.itee.exam.entity.Texam;
import com.zust.itee.exam.entity.TexamSignup;
import com.zust.itee.exam.service.BaseService;

public interface ExamMemberScoreService extends BaseService<Texam> {

    /**
     * @author sdy
     * @why 获取某场考试中可以成绩录入的驾驶员信息
     */
    List<DriverInfoInExamManagement> getScoreDrivers(Integer examId,
            PageBaseDto pageBaseDto) throws RuntimeException;

    /**
     * @return excel下载路径
     * @author sdy
     * @why 生成某场考试批量导入成绩excel
     */
    String createScoreDriversExcel(HttpServletRequest request, Integer examId)
            throws RuntimeException;

    /**
     * @author sdy
     * @why 获取某场考试中可以成绩录入的驾驶员分页相关信息
     */
    PageBaseDto getScorePageBaseDto(Integer examId, PageBaseDto pageBaseDto)
            throws RuntimeException;

    /**
     * @author sdy
     * @why 工作人员给一位驾驶员录入成绩
     */
    boolean scoreOneDriver(Integer examId, Integer driverId, Double score) throws RuntimeException;

    /**
     * @author sdy
     * @why excel批量导入驾驶员成绩
     */
    boolean scoreAllDrivers(File file, Integer examId) throws RuntimeException;

    /**
     * @author sdy
     * @why 提交某场考试成绩录入
     */
    boolean submitScore(Integer examId);

    /**
     * @author sdy
     * @why TexamSignup转化为dto
     */
    List<DriverInfoInExamManagement> transTexamSignupToSignupDriverInfo(
            List<TexamSignup> texamSignups);
}
