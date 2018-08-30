package com.zust.itee.exam.controller.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.zust.itee.exam.dto.exam.member.PaperBaseInfo;
import com.zust.itee.exam.dto.exam.member.PaperBasicInfoForExam;
import com.zust.itee.exam.dto.exam.member.PaperDesign;
import com.zust.itee.exam.dto.exam.member.PaperInfoForExam;
import com.zust.itee.exam.dto.exam.member.PaperQuestionType;
import com.zust.itee.exam.dto.exam.member.QuestionModel;
import com.zust.itee.exam.entity.Tquestion;
import com.zust.itee.exam.service.exam.PaperMemberService;

@Controller
@RequestMapping("paper/member")
public class PaperMemberController {

    @Autowired
    PaperMemberService paperService;

    /**
     * @author sdy
     * @why 跳转至试卷生成页面
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView createNewPaper(SessionInfo sessionInfo,
            @RequestParam(name = "examId", required = false) Integer examId,
            @RequestParam(name = "examType", required = false) Integer examType) {
        ModelAndView modelAndView = new ModelAndView("2/paper/createPaper");
        if (examId != null) {
            modelAndView.addObject("examId", examId);
        }
        // 创建新试卷，跳转到试卷生成页
        try {
            /*
			 * 获取题库相关信息
			 */
            List<PaperQuestionType> questionTypes = paperService
                    .getPaperDesignBaseInfo();
            modelAndView.addObject("questionTypes", questionTypes);
            // 获取必选题相关信息
            Map<String, Object> requiredQuestionsInfo = paperService
                    .getRequiredQuestionsInfo();
            Long requiredQuestionNum = (Long) requiredQuestionsInfo.get("num");
            Double requiredQuestionMarks = (Double) requiredQuestionsInfo
                    .get("marks");
            modelAndView.addObject("requiredQuestionNum", requiredQuestionNum);
            modelAndView.addObject("requiredQuestionMarks",
                    requiredQuestionMarks);
        } catch (Exception e) {
            return new ModelAndView("redirect:/404");
        }
        modelAndView.addObject("examType", examType);
        return modelAndView;
    }

    /**
     * @author sdy
     * @why 提交保存试卷设计基本信息
     */
    @RequestMapping(value = "/basicInfo", method = RequestMethod.POST)
    public ModelAndView savePaperBaseInfo(SessionInfo sessionInfo,
            @RequestParam(name = "examId", required = false) Integer examId,
            PaperBaseInfo paperBaseInfo) {
        ModelAndView modelAndView = new ModelAndView(
                "redirect:/exam/member/home");

        Integer memberId = sessionInfo.getUserId();
		/*
		 * 获取paperDesignList
		 */
        List<PaperDesign> paperDesigns = paperService
                .stringTransToPaperDesignList(paperBaseInfo
                        .getPaperDesignString());
		/*
		 * 保存考试试卷基本信息
		 */
        try {
            Integer paperId = paperService.savePaperBaseInfo(paperDesigns,
                    paperBaseInfo, memberId);
            if (examId != null) {
                // 存在考试信息，添加考试与试卷关联
                paperService.paperConnectExam(paperId, examId);
            }
        } catch (Exception e) {

            return new ModelAndView("redirect:/404");
        }
        return modelAndView;
    }

    /**
     * @author sdy
     * @why Ajax提交保存试卷设计基本信息
     */
    @RequestMapping(value = "/basicInfoAjax", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Object> savePaperBaseInfoAjax(SessionInfo sessionInfo,
            @RequestParam(name = "examId", required = false) Integer examId,
            PaperBaseInfo paperBaseInfo) {
        JsonResult<Object> jsonResult = new JsonResult<Object>();

        Integer memberId = sessionInfo.getUserId();

		/*
		 * 获取paperDesignList
		 */
        List<PaperDesign> paperDesigns = paperService
                .stringTransToPaperDesignList(paperBaseInfo
                        .getPaperDesignString());
		/*
		 * 保存考试试卷基本信息
		 */
        Integer paperId;
        try {
            paperId = paperService.savePaperBaseInfo(paperDesigns,
                    paperBaseInfo, memberId);
            if (examId != null) {
                // 存在考试信息，需要跟考试关联
                paperService.paperConnectExam(paperId, examId);
            }
        } catch (Exception e) {

            return new JsonResult<>(false, "保存失败!");
        }
        jsonResult.setData(paperId);
        jsonResult.setSuccess(true);
        return jsonResult;
    }

    /**
     * @author sdy
     * @why 生成试卷详情
     */
    @RequestMapping(value = "/{paperId}/create", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult<Object> createPaper(SessionInfo sessionInfo,
            @PathVariable("paperId") int paperId) {
        JsonResult<Object> jsonResult = new JsonResult<Object>();
        List<QuestionModel> questionModels = new ArrayList<QuestionModel>();
        try {
            List<Tquestion> questions = paperService
                    .createTquestionsAndShowQuestions(paperId);
            questionModels = paperService
                    .questionListTransToQuerstionModelList(questions);
        } catch (Exception e) {
            return new JsonResult<>(false, e.getMessage());
        }
        jsonResult.setSuccess(true);
        jsonResult.setData(questionModels);
        return jsonResult;
    }

    /**
     * @author sdy
     * @why 保存试卷详情
     */
    @RequestMapping(value = "/{paperId}/save", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Object> savePaperList(
            SessionInfo sessionInfo,
            @PathVariable("paperId") Integer paperId,
            @RequestParam(name = "questionsString", required = true) String questionsString) {
        JsonResult<Object> jsonResult = new JsonResult<Object>();
        try {
            List<Tquestion> tquestions = paperService
                    .getQuestionList(questionsString);
            paperService.savePaperList(tquestions, paperId);
        } catch (Exception e) {

            return new JsonResult<>(false, e.getMessage());
        }
        jsonResult.setSuccess(true);
        return jsonResult;
    }

    /**
     * @author sdy
     * @why 获取所有已有试卷的信息
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView getAllPapers(SessionInfo sessionInfo,
            PageBaseDto pageBaseDto) {
        ModelAndView modelAndView = new ModelAndView("2/paper/paperManagement");
        try {
            Integer memberOrgId = sessionInfo.getOrganizationId();
            pageBaseDto = PageBaseDto.ENSURENOTNULL(pageBaseDto);
            List<PaperBasicInfoForExam> paperList = paperService
                    .getPaperBasicInfoForExam(pageBaseDto, memberOrgId);
            modelAndView.addObject("papers", paperList);
            pageBaseDto = paperService
                    .getPaperBasicInfoPageBaseDtoForExam(pageBaseDto, memberOrgId);
            modelAndView.addObject("pageBaseDto", pageBaseDto);
        } catch (Exception e) {
            return new ModelAndView("redirect:/404");
        }
        return modelAndView;
    }

    /**
     * @author sdy
     * @why 编辑试卷样式
     */
    @RequestMapping(value = "/edit/{paperId}", method = RequestMethod.GET)
    public ModelAndView editPaper(@PathVariable("paperId") Integer paperId,
            SessionInfo sessionInfo,
            @RequestParam(name = "examType", required = false) Short examType) {
        ModelAndView modelAndView = new ModelAndView("2/paper/editPaper");
        try {
            List<PaperQuestionType> questionTypes = paperService
                    .getPaperDesignBaseInfoWithPaper(paperId);
            modelAndView.addObject("questionTypes", questionTypes);
            PaperBaseInfo paperBaseInfo = paperService
                    .getPaperBaseInfoForEditPaper(paperId);
            modelAndView.addObject("paper", paperBaseInfo);
            Map<String, Object> prePaperInfo = paperService
                    .getPrePaperInfo(questionTypes);
            Integer sumNum = (Integer) prePaperInfo.get("sumNum");
            Double sumScore = (Double) prePaperInfo.get("sumScore");
            modelAndView.addObject("sumNum", sumNum);
            modelAndView.addObject("sumScore", sumScore);
            modelAndView.addObject("paperId", paperId);
            modelAndView.addObject("examType", examType);
            // 获取必选题相关信息
            Map<String, Object> requiredQuestionsInfo = paperService
                    .getRequiredQuestionsInfo();
            Long requiredQuestionNum = (Long) requiredQuestionsInfo.get("num");
            Double requiredQuestionMarks = (Double) requiredQuestionsInfo
                    .get("marks");
            modelAndView.addObject("requiredQuestionNum", requiredQuestionNum);
            modelAndView.addObject("requiredQuestionMarks",
                    requiredQuestionMarks);
        } catch (Exception e) {
            return new ModelAndView("redirect:/404");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/{paperId}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResult<Object> deletePaper(
            @PathVariable("paperId") Integer paperId) {
        JsonResult<Object> jsonResult = new JsonResult<>();
        try {
            paperService.deletePaper(paperId);
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            return new JsonResult<>(false, e.getMessage());
        }
        return jsonResult;
    }

    /**
     * @author sdy
     * @why 发起考试时或编辑考试选取已有试卷，展示已有试卷基本信息列表
     */
    @RequestMapping(value = "/{examId}/select", method = RequestMethod.GET)
    public ModelAndView showPaperForExam(
            @PathVariable("examId") Integer examId, PageBaseDto pageBaseDto,
            SessionInfo sessionInfo) {
        ModelAndView modelAndView = new ModelAndView(
                "2/exam/member/existedPaper");
        pageBaseDto = PageBaseDto.ENSURENOTNULL(pageBaseDto);
        try {
            Integer memberOrgId = sessionInfo.getOrganizationId();
            //获取所有试卷
            List<PaperBasicInfoForExam> paperList = paperService
                    .getPaperBasicInfoForExam(pageBaseDto, memberOrgId);
            modelAndView.addObject("paperList", paperList);
            //获取分页信息
            pageBaseDto = paperService
                    .getPaperBasicInfoPageBaseDtoForExam(pageBaseDto, memberOrgId);
            modelAndView.addObject("pageBaseDto", pageBaseDto);
            //获取当前考试已选择的试卷id
            Integer paperId = paperService.getExamPaperId(examId);
            modelAndView.addObject("paperId", paperId);

            modelAndView.addObject("examId", examId);
        } catch (Exception e) {
            return new ModelAndView("redirect:/404");
        }
        return modelAndView;
    }

    /**
     * 考试试卷管理时获取试卷详情
     */
    @RequestMapping(value = "/{paperId}/detail", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult<Object> getPaperDetailInfo(
            @PathVariable("paperId") Integer paperId) {
        JsonResult<Object> jsonResult = new JsonResult<>();
        try {
            PaperInfoForExam paperInfoForExam = paperService
                    .getPaperDetailInfo(paperId);
            jsonResult.setData(paperInfoForExam);
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            return new JsonResult<>(false, "获取试卷详情失败");
        }
        return jsonResult;
    }

    /**
     * @author sdy
     * @why 发起考试选择已有试卷
     */
    @RequestMapping(value = "{examId}/select", method = RequestMethod.POST)
    public ModelAndView selectPaperForExam(
            @PathVariable("examId") Integer examId,
            @RequestParam(name = "paperId", required = true) Integer paperId) {
        ModelAndView modelAndView = new ModelAndView(
                "redirect:/exam/member/home");
        try {
            paperService.paperConnectExam(paperId, examId);
        } catch (Exception e) {
            return new ModelAndView("redirect:/404");
        }
        return modelAndView;
    }
}
