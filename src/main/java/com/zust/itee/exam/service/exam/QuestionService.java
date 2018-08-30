package com.zust.itee.exam.service.exam;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zust.itee.exam.dto.base.PageBaseDto;
import com.zust.itee.exam.dto.exam.member.PaperQuestionSubject;
import com.zust.itee.exam.dto.exam.member.PaperQuestionType;
import com.zust.itee.exam.dto.exam.member.QuestionCreateModel;
import com.zust.itee.exam.dto.exam.member.QuestionModel;

public interface QuestionService {

    /**
     * 获取所有题目
     *
     * @param subjectIdStr 筛选的知识点id，知识点1；知识点2；……
     * @param typeIdStr 筛选的题型id，题型1；题型2；……
     * @param requiredStr 筛选的必选题
     */
    List<QuestionModel> getAllQuestions(PageBaseDto pageBaseDto,
            String subjectIdStr, String typeIdStr, String requiredStr) throws RuntimeException;

    /**
     * 获取所有题目分页信息
     *
     * @throws RuntimeException
     */
    PageBaseDto getAllQuestionPageBaseDto(PageBaseDto pageBaseDto,
            String subjectIdStr, String typeIdStr, String requiredStr) throws RuntimeException;

    /**
     * @author sdy
     * @why 获取所有已有题目题型
     */
    List<PaperQuestionType> getAllQuestionType() throws RuntimeException;

    /**
     * @author sdy
     * @why 获取所有已有题目知识点
     */
    List<PaperQuestionSubject> getAllQuestionSubject() throws RuntimeException;

    /**
     * @author sdy
     * @why 获取单个题目完整信息
     */
    QuestionModel getQuestionInfo(Integer questionId) throws RuntimeException;

    /**
     * @author sdy
     * @why 获取题目导入excel模板路径
     */
    String getQuestionImportExcel(HttpServletRequest request)
            throws RuntimeException;

    /**
     * @author sdy
     * @why excel批量导入题目
     */
    boolean importQuestionsByExcel(File file, Integer memberId);

    /**
     * @author sdy
     * @why 创建试题
     */
    boolean createOrUpdateQuestion(QuestionCreateModel questionModel,
            Integer memberId) throws RuntimeException;

    /**
     * 编辑试题
     */
    boolean editQuestion(Integer memberId, Integer questionId,
            QuestionCreateModel questionCreateModel) throws RuntimeException;

    /**
     * @author sdy
     * @why 删除试题
     */
    boolean deleteQuestion(Integer questionId) throws RuntimeException;

    /**
     * @author sdy
     * @why 获取所有必选题
     */
    List<QuestionModel> getRequiredQuestions(PageBaseDto pageBaseDto,
            Long subjectId, Long typeId);

    /**
     * @param pageBaseDto 原分页基本信息
     * @param subjectId 筛选知识点
     * @param typeId 筛选题型
     * @author sdy
     * @why 获取必选题分页信息
     */
    PageBaseDto getRequiredQuestionsPageBaseDto(PageBaseDto pageBaseDto,
            Long subjectId, Long typeId);

    /**
     * @author sdy
     * @why 将题目设置成必选题
     */
    boolean setRequiredQuestion(Integer questionId);

    /**
     * @param questionId 题目id
     * @author sdy
     * @why 将必选题设置成非必选题
     */
    boolean removeRequiredQuestion(Integer questionId);
}
