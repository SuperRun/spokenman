package com.zust.itee.exam.service.exam;

import java.util.List;
import java.util.Map;

import com.zust.itee.exam.dto.base.PageBaseDto;
import com.zust.itee.exam.dto.exam.member.CheckPaper;
import com.zust.itee.exam.dto.exam.member.PaperBaseInfo;
import com.zust.itee.exam.dto.exam.member.PaperBasicInfoForExam;
import com.zust.itee.exam.dto.exam.member.PaperDesign;
import com.zust.itee.exam.dto.exam.member.PaperInfoForExam;
import com.zust.itee.exam.dto.exam.member.PaperQuestionType;
import com.zust.itee.exam.dto.exam.member.QuestionModel;
import com.zust.itee.exam.entity.TexamResult;
import com.zust.itee.exam.entity.Tpaper;
import com.zust.itee.exam.entity.Tquestion;
import com.zust.itee.exam.service.BaseService;

/**
 * 组卷相关
 *
 * @author sdy
 */
public interface PaperMemberService extends BaseService<Tpaper> {

    /**
     * @author sdy
     * @why 组卷时需获取题库相关信息
     */
    List<PaperQuestionType> getPaperDesignBaseInfo() throws RuntimeException;

    /**
     * @author sdy
     * @why 组卷是获取必选题相关信息
     */
    Map<String, Object> getRequiredQuestionsInfo() throws RuntimeException;

    /**
     * @author sdy
     * @why 试卷样式编辑时获取题库相关信息
     */
    List<PaperQuestionType> getPaperDesignBaseInfoWithPaper(Integer paperId)
            throws RuntimeException;

    /**
     * @param paperDesigns 题型知识点设计
     * @param memberId 命题人id
     * @return 返回paperId
     * @author sdy
     * @why 为考试添加试卷组卷基本信息
     */
    int savePaperBaseInfo(List<PaperDesign> paperDesigns,
            PaperBaseInfo paperBaseInfo, Integer memberId)
            throws RuntimeException;

    /**
     * @return 返回试题列表
     * @author sdy
     * @why 根据试卷基本信息组卷
     */
    List<Tquestion> createTquestionsAndShowQuestions(Integer paperId)
            throws RuntimeException;

    /**
     * @author sdy
     * @why 根据前端获取到的questionsString转为questionList
     */
    List<Tquestion> getQuestionList(String questionsString)
            throws RuntimeException;

    /**
     * @author sdy
     * @why 正式考试时保存paperList
     */
    boolean savePaperList(List<Tquestion> tquestions, Integer paperId)
            throws RuntimeException;

    /**
     * @author sdy
     * @why 添加考试与试卷的关联
     */
    boolean paperConnectExam(Integer paperId, Integer examId)
            throws RuntimeException;

    /**
     * @author sdy
     * @why 发布考试选择已有所有试卷、试卷管理显示所有试卷
     */
    List<PaperInfoForExam> getAllPapers(PageBaseDto pageBaseDto)
            throws RuntimeException;

    /**
     * @author sdy
     * @why 获取显示试卷时分页信息
     */
    PageBaseDto getAllPapersPageBaseDto(PageBaseDto pageBaseDto) throws RuntimeException;

    /**
     * @author sdy
     * @why 正式考试阅卷给出试卷总分
     */
    boolean scorePaperForOfficialExam(Integer examId, Integer driverId) throws RuntimeException;

    /**
     * @author sdy
     * @why 正式考试查卷
     */
    List<CheckPaper> checkPaperDetail(Integer examId, Integer driverId) throws RuntimeException;

    /**
     * @author sdy
     * @why 发起考试时选取已有试卷，获取已有试卷基本信息列表
     */
    List<PaperBasicInfoForExam> getPaperBasicInfoForExam(
            PageBaseDto pageBaseDto, Integer memberOrgId)
            throws RuntimeException;

    /**
     * @author sdy
     * @why 发起考试时选取已有试卷，获取已有试卷基本信息列表分页信息
     */
    PageBaseDto getPaperBasicInfoPageBaseDtoForExam(PageBaseDto pageBaseDto,
            Integer memberOrgId) throws RuntimeException;

    /**
     * @author sdy
     * @why 删除试卷
     */
    boolean deletePaper(Integer paperId) throws RuntimeException;

    /**
     * @author sdy
     * @why 获取试卷详细信息
     */
    PaperInfoForExam getPaperDetailInfo(Integer paperId)
            throws RuntimeException;

    /**
     * @author sdy
     * @why 获取编辑试卷基本信息
     */
    PaperBaseInfo getPaperBaseInfoForEditPaper(Integer paperId)
            throws RuntimeException;

    /**
     * @author sdy
     * @why 获取试卷总分总题数
     */
    Map<String, Object> getPrePaperInfo(List<PaperQuestionType> questionTypes)
            throws RuntimeException;

    /**
     * 获取当前考试已选择的试卷
     */
    Integer getExamPaperId(Integer examId) throws RuntimeException;

	/*
     * TODO
	 */

    /**
     * @author sdy
     * @why 由试卷题目列表转成题目列表dto
     */
    List<QuestionModel> questionListTransToQuerstionModelList(
            List<Tquestion> tquestions);

    /**
     * @author sdy
     * @why 题目结果转成查卷dto
     */
    List<CheckPaper> examResultListTransToCheckPaperList(
            List<TexamResult> texamResults);

    /**
     * @param paperDesignString typeId&subjectId&num;typeId&subjectId&num;……
     * @author sdy
     * @why 将前端传来的paperDesignString转换为paperDesignList
     */
    List<PaperDesign> stringTransToPaperDesignList(String paperDesignString);

    /**
     * @author sdy
     * @why 试卷列表转换为选择已有试卷列表基本信息dto
     */
    List<PaperBasicInfoForExam> transTpaperToPaperBasicInfoForExams(
            List<Tpaper> paperList) throws RuntimeException;
}
