package com.zust.itee.exam.controller.exam;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zust.itee.dto.base.SessionInfo;
import com.zust.itee.exam.dto.JsonResult;
import com.zust.itee.exam.dto.base.PageBaseDto;
import com.zust.itee.exam.dto.exam.member.PaperQuestionSubject;
import com.zust.itee.exam.dto.exam.member.PaperQuestionType;
import com.zust.itee.exam.dto.exam.member.QuestionCreateModel;
import com.zust.itee.exam.dto.exam.member.QuestionModel;
import com.zust.itee.exam.service.exam.QuestionService;

@Controller
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    /**
     * @author sdy
     * @why 显示所有题目列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView showAllQuestions(
            PageBaseDto pageBaseDto,
            @RequestParam(name = "subjectIdStr", required = false) String subjectIdStr,
            @RequestParam(name = "typeIdStr", required = false) String typeIdStr,
            @RequestParam(name = "requiredStr", required = false) String requiredStr) {
        ModelAndView modelAndView = new ModelAndView(
                "2/question/questionManagement");
        pageBaseDto = PageBaseDto.ENSURENOTNULL(pageBaseDto);
        try {

            // 获取所有题目
            List<QuestionModel> questions = questionService.getAllQuestions(
                    pageBaseDto, subjectIdStr, typeIdStr, requiredStr);
            modelAndView.addObject("questions", questions);

            // 获取所有题目的分页信息
            pageBaseDto = questionService.getAllQuestionPageBaseDto(
                    pageBaseDto, subjectIdStr, typeIdStr, requiredStr);
            modelAndView.addObject("pageBaseDto", pageBaseDto);

            // 获取所有题目知识点信息
            List<PaperQuestionSubject> questionSubjects = questionService
                    .getAllQuestionSubject();
            modelAndView.addObject("questionSubjects", questionSubjects);
            // 获取所有题目题型信息
            List<PaperQuestionType> questionTypes = questionService
                    .getAllQuestionType();
            modelAndView.addObject("questionTypes", questionTypes);

			/*
             * 传递筛选信息
			 */
            if (subjectIdStr == null) {
                subjectIdStr = "-1;";
            }
            modelAndView.addObject("subjectIdStr", subjectIdStr);

            if (typeIdStr == null) {
                typeIdStr = "-1;";
            }
            modelAndView.addObject("typeIdStr", typeIdStr);

            if (requiredStr == null) {
                requiredStr = "-1;";
            }
            modelAndView.addObject("requiredStr", requiredStr);
        } catch (Exception e) {
            return new ModelAndView("redirect:/404");
        }
        return modelAndView;
    }

    /**
     * @author sdy
     * @why 点击题目显示题目完整信息
     */
    @RequestMapping(value = "/{questionId}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult<QuestionModel> getQuestionInfo(
            @PathVariable("questionId") Integer questionId) {
        JsonResult<QuestionModel> jsonResult = new JsonResult<QuestionModel>();
        try {
            QuestionModel questionModel = questionService
                    .getQuestionInfo(questionId);
            jsonResult.setData(questionModel);
        } catch (Exception e) {
            return new JsonResult<QuestionModel>(false, e.getMessage());
        }
        jsonResult.setSuccess(true);
        return jsonResult;
    }

    /**
     * @author sdy
     * @why 跳转至题目导入页面
     */
    @RequestMapping(value = "/import", method = RequestMethod.GET)
    public ModelAndView toQuestionImport(
            SessionInfo sessionInfo,
            @RequestParam(name = "questionId", required = false) Integer questionId,
            HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(
                "2/question/questionImport");
        try {
            // 获取批量导入题目excel
            String excelPath = questionService.getQuestionImportExcel(request);
            modelAndView.addObject("excelPath", excelPath);
            // 获取所有题目知识点信息
            List<PaperQuestionSubject> questionSubjects = questionService
                    .getAllQuestionSubject();
            modelAndView.addObject("questionSubjects", questionSubjects);
            // 获取所有题目题型信息
            List<PaperQuestionType> questionTypes = questionService
                    .getAllQuestionType();
            modelAndView.addObject("questionTypes", questionTypes);
        } catch (Exception e) {
            return new ModelAndView("redirect:/404");
        }
        return modelAndView;
    }

    /**
     * @author sdy
     * @why 单题导入
     */
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public ModelAndView questionImport(QuestionCreateModel questionModel,
            SessionInfo sessionInfo) {
        ModelAndView modelAndView = new ModelAndView("redirect:/question/list");
        try {
            Integer memberId = sessionInfo.getUserId();
            questionService.createOrUpdateQuestion(questionModel, memberId);
        } catch (Exception e) {
            return new ModelAndView("redirect:/404");
        }
        return modelAndView;
    }

    /**
     * @author sdy
     * @why excel批量导入题目
     */
    @RequestMapping(value = "/import/excel", method = RequestMethod.POST)
    public ModelAndView importQuestionsByExcel(
            SessionInfo sessionInfo,
            HttpServletRequest request,
            @RequestParam(name = "questionExcelUrl", required = true) String importExcel) {
        ModelAndView modelAndView = new ModelAndView("redirect:/question/list");
        Integer memberId = sessionInfo.getUserId();
        try {
            // 根据url获取文件
            String realPath = request.getSession().getServletContext()
                    .getRealPath("");
            File file = new File(realPath + "/" + importExcel);
            questionService.importQuestionsByExcel(file, memberId);
        } catch (Exception e) {
            return new ModelAndView("redirect:/404");
        }
        return modelAndView;
    }

    /**
     * @author sdy
     * @why 编辑题目
     */
    @RequestMapping(value = "/edit/{questionId}", method = RequestMethod.GET)
    public ModelAndView toEditQuestion(
            @PathVariable("questionId") Integer questionId,
            HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("2/question/questionEdit");
        try {
            modelAndView.addObject("questionId", questionId);
            // 获取题目信息
            QuestionModel questionModel = questionService
                    .getQuestionInfo(questionId);
            modelAndView.addObject("question", questionModel);
            // 获取批量导入题目excel
            String excelPath = questionService.getQuestionImportExcel(request);
            modelAndView.addObject("excelPath", excelPath);
            // 获取所有题目知识点信息
            List<PaperQuestionSubject> questionSubjects = questionService
                    .getAllQuestionSubject();
            modelAndView.addObject("questionSubjects", questionSubjects);
            // 获取所有题目题型信息
            List<PaperQuestionType> questionTypes = questionService
                    .getAllQuestionType();
            modelAndView.addObject("questionTypes", questionTypes);
        } catch (Exception e) {
            return new ModelAndView("redirect:/404");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{questionId}", method = RequestMethod.POST)
    public ModelAndView editQuestion(
            @PathVariable("questionId") Integer questionId,
            QuestionCreateModel questionCreateModel, SessionInfo sessionInfo) {
        ModelAndView modelAndView = new ModelAndView("redirect:/question/list");
        try {
            Integer memberId = sessionInfo.getUserId();
            questionService.editQuestion(memberId, questionId,
                    questionCreateModel);
        } catch (Exception e) {
            return new ModelAndView("redirect:/404");
        }
        return modelAndView;
    }

    /**
     * @author sdy
     * @why 删除题目
     */
    @RequestMapping(value = "/delete/{questionId}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResult<Object> deleteQuestion(
            @PathVariable("questionId") Integer questionId) {
        JsonResult<Object> jsonResult = new JsonResult<>();
        try {
            questionService.deleteQuestion(questionId);
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            return new JsonResult<>(false, "删除题目失败");
        }
        return jsonResult;
    }

    /**
     * @author sdy
     * @why 将一个题目设置成必选题
     */
    @RequestMapping(value = "/required/{questionId}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Object> setRequiredQuestion(
            @PathVariable("questionId") Integer questionId) {
        JsonResult<Object> jsonResult = new JsonResult<>();
        try {
            questionService.setRequiredQuestion(questionId);
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            return new JsonResult<>(false, "设置失败");
        }
        return jsonResult;
    }

    /**
     * @author sdy
     * @why 将必选题设置成非必选题
     */
    @RequestMapping(value = "/required/{questionId}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResult<Object> removeRequiredQuestion(
            @PathVariable("questionId") Integer questionId) {
        JsonResult<Object> jsonResult = new JsonResult<>();
        try {
            questionService.removeRequiredQuestion(questionId);
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            return new JsonResult<>(false, "移除失败");
        }
        return jsonResult;
    }
}
