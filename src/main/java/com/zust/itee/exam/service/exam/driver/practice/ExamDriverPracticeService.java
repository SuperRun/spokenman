package com.zust.itee.exam.service.exam.driver.practice;

import java.util.List;

import com.zust.itee.exam.dto.base.PageBaseDto;
import com.zust.itee.exam.dto.exam.driver.ChangeQuestion;
import com.zust.itee.exam.dto.exam.driver.ExamingBaseInfo;
import com.zust.itee.exam.dto.exam.member.ExamExposer;
import com.zust.itee.exam.dto.exam.member.QuestionModel;
import com.zust.itee.exam.entity.Texam;
import com.zust.itee.exam.entity.Tquestion;
import com.zust.itee.exam.service.BaseService;

public interface ExamDriverPracticeService extends BaseService<Texam> {

    /**
     * @author sdy
     * @why 获取所有模拟考试列表
     */
    List<ExamExposer> getPracticeList(PageBaseDto pageBaseDto);

    /**
     * @author sdy
     * @why 获取模拟考试列表分页信息
     */
    PageBaseDto getPracticeListPageBaseDto(PageBaseDto pageBaseDto);

    /**
     * @author sdy
     * @why 获取模拟考试试卷
     */
    Integer getPracticePaperId(Integer examId) throws RuntimeException;

    /**
     * @author sdy
     * @why 生成驾驶员参加模拟考记录
     */
    Integer createExamPractice(Integer driverId, Integer examId)
            throws RuntimeException;

    boolean createExamPracticeResultList(List<Tquestion> tquestions,
            Integer examPracticeId) throws RuntimeException;

    /**
     * @author sdy
     * @why 模拟考试中保存题目答案
     */
    boolean savePracticeAnswer(ChangeQuestion changeQuestion,
            Integer examPracticeId) throws RuntimeException;

    /**
     * @author sdy
     * @why 驾驶员模拟考试时获取下一题
     */
    QuestionModel getPracticeQuestion(ChangeQuestion changeQuestion,
            Integer examPracticeId) throws RuntimeException;

    /**
     * @author sdy
     * @why 模拟考试切题后更新切题信息
     */
    ChangeQuestion updatePracticeChangeQuestion(ChangeQuestion changeQuestion,
            Integer examPracticeId, QuestionModel questionModel);

    /**
     * @author sdy
     * @why 获取模拟考试题目数
     */
    Long getPracticeQuestionSum(Integer examPracticeId) throws RuntimeException;

    /**
     * @author sdy
     * @why 获取模拟考试基本信息
     */
    ExamingBaseInfo getPracticeExamingBaseInfo(Integer examPracticeId);

    /**
     * @author sdy
     * @why 获取模拟考试结束时间
     */
    String getPracticeEndTime(Integer examPracticeId) throws RuntimeException;

    /**
     * @author sdy
     * @why 检查是否已经提交模拟考
     */
    boolean checkHaveSubmitInPractice(Integer examPracticeId)
            throws RuntimeException;

    /**
     * @author sdy
     * @why 模拟考交卷算总分
     */
    boolean countScoreInPracticeExam(Integer examPracticeId)
            throws RuntimeException;

    /**
     * @author sdy
     * @why 获取模拟考试错题信息
     */
    QuestionModel getPracticeErrorQuestion(ChangeQuestion changeQuestion,
            Integer examPracticeId) throws RuntimeException;

    /**
     * @author sdy
     * @why 获取交卷后的模拟考试切题信息
     */
    ChangeQuestion updatePracticeCompletedChangeQuestion(
            ChangeQuestion changeQuestion, Integer examPracticeId, QuestionModel questionModel)
            throws RuntimeException;

    /**
     * @author sdy
     * @why 获取模拟考试错题数目
     */
    Long getPracticeErrorSum(Integer examPracticeId) throws RuntimeException;

    /**
     * @author sdy
     * @why 模拟结束后返回考试信息
     */
    ExamingBaseInfo getPracticeResultInfo(Integer examPracticeId)
            throws RuntimeException;
}
