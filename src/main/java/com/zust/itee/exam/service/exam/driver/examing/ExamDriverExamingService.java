package com.zust.itee.exam.service.exam.driver.examing;

import java.util.List;
import java.util.Map;

import com.zust.itee.exam.dto.exam.driver.ChangeQuestion;
import com.zust.itee.exam.dto.exam.driver.ExamingBaseInfo;
import com.zust.itee.exam.dto.exam.member.QuestionModel;
import com.zust.itee.exam.entity.Tdriver;
import com.zust.itee.exam.entity.Texam;
import com.zust.itee.exam.entity.Tquestion;
import com.zust.itee.exam.service.BaseService;

public interface ExamDriverExamingService extends BaseService<Texam> {

    /**
     * @author sdy
     * @why 第一次点进正式考试时，创建考试结果list
     */
    boolean createExamResult(Integer examId, Integer driverId) throws RuntimeException;

    /**
     * @author sdy
     * @why 获取考试试卷id
     */
    Integer getPaperId(Integer examId);

    /**
     * @author sdy
     * @why 一人一卷正式考试时，临时组完卷再创建考试结果list
     */
    boolean createExamResultWithQuestions(List<Tquestion> questions,
            Integer examId, Integer driverId);

    /**
     * @author sdy
     * @why 点击考试时检查是否已经交卷
     */
    boolean checkHaveSubmit(Integer examId, Integer driverId);

    /**
     * @author sdy
     * @why 正式考试过程中提交一题答案
     */
    boolean saveAnswer(Integer driverId, Integer examId,
            ChangeQuestion changeQuestion) throws RuntimeException;

    /**
     * @param driverId ,examId,changeQuestion
     * @author sdy
     * @why 正式考试过程中切题
     */
    QuestionModel getQuestion(Integer driverId, Integer examId,
            ChangeQuestion changeQuestion) throws RuntimeException;

    /**
     * @author sdy
     * @why 切题后更新总题数
     */
    ChangeQuestion updateChangeQuestion(Integer driverId, Integer examId,
            ChangeQuestion changeQuestion, QuestionModel questionModel);

    /**
     * @author sdy
     * @why 获取正式考试题目数
     */
    Long getQuestionSum(Integer driverId, Integer examId);

    /**
     * @author sdy
     * @why 获取考试时基本信息
     */
    ExamingBaseInfo getExamingBaseInfo(Integer driverId, Integer examId);

    /**
     * @author sdy
     * @why 第一次进入正式考试时，获取考试相关时间参数
     */
    Map<String, Object> getexamDate(Integer examId);

    /**
     * @author sdy
     * @why 正式考试时计算总分
     */
    boolean countScoreInOfficialExam(Integer examId, Integer driverId)
            throws RuntimeException;

    /**
     * @param sfzNo 身份证号
     * @return 考试id
     * @throws RuntimeException
     * @author sdy 驾驶员考试登录
     */
    Integer examLogin(String sfzNo) throws RuntimeException;

    /**
     * 驾驶员考试中登录信息加入session
     */
    Tdriver driverLogin(String sfzNo);
}
